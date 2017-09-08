package org.sos.sixbox.folder.service.impl;

import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lodour on 2017/9/3 23:52.
 */
@Service
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;
    private final UserService userService;

    @Autowired
    public FolderServiceImpl(FolderRepository folderRepository, FileRepository fileRepository, UserService userService) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
        this.userService = userService;
    }

    /**
     * 创建文件夹
     *
     * @param folderEntity 待创建文件夹实体
     * @return 写入ID的文件夹实体
     */
    @Override
    public FolderEntity createFolder(FolderEntity folderEntity) {
        folderEntity.setCreateTime(Utils.getCurrentTimestamp());
        return folderRepository.save(folderEntity);
    }

    /**
     * 初始化系统文件夹
     */
    @Override
    public void initSystemFolder() {
        FolderEntity root = folderRepository.getRootFolder();
        if (root != null) {
            deleteFolder(root.getId());
        }
        root = new FolderEntity();
        root.setName("/");
        createFolder(root);
    }

    /**
     * 初始化用户文件夹
     *
     * @param userId 用户ID
     */
    @Override
    public void initUserFolder(int userId) {
        // 获取用户实体
        UserEntity user = userService.getById(userId);

        // 创建用户根目录
        FolderEntity userRoot = folderRepository.getUserRootFolder(user.getUsername(), user.getId());
        if (userRoot == null) {
            userRoot = new FolderEntity();
            userRoot.setName(user.getUsername());
            userRoot.setOwnerId(user.getId());
            userRoot = createFolder(userRoot);
            moveFolderToFolder(userRoot.getId(), folderRepository.getRootFolder().getId());
        }

        // 创建用户垃圾箱
        if (folderRepository.getUserTrashFolder(userId) == null) {
            FolderEntity userTrash = new FolderEntity();
            userTrash.setName(".trash");
            userTrash.setOwnerId(user.getId());
            userTrash = createFolder(userTrash);
            moveFolderToFolder(userTrash.getId(), userRoot.getId());
        }
    }

    /**
     * 移动文件夹
     *
     * @param srcId 被移动文件夹的ID
     * @param dstId 移入文件夹的ID
     */
    @Override
    public void moveFolderToFolder(String srcId, String dstId) {
        FolderEntity src = folderRepository.findById(srcId);
        FolderEntity dst = folderRepository.findById(dstId);
        FolderEntity srcParent = folderRepository.findById(src.getParent());

        // Remove src from srcParent.children
        if (srcParent != null) {
            List<String> srcParentChildren = srcParent.getChildren();
            if (srcParentChildren != null)
                srcParentChildren.remove(srcId);
            srcParent.setChildren(srcParentChildren);
            folderRepository.save(srcParent);
        } else {
            src.setParent(dstId);
        }

        // Move src to dst.children
        List<String> dstChildren = dst.getChildren();
        if (dstChildren == null) {
            dstChildren = new ArrayList<>();
        }
        dstChildren.add(srcId);
        dst.setChildren(dstChildren);

        // Update src.parent
        src.setParent(dstId);

        // Save to MongoDB
        folderRepository.save(src);
        folderRepository.save(dst);
    }

    /**
     * 文件夹移入垃圾箱
     *
     * @param id 文件夹ID
     */
    @Override
    public void moveFolderToTrash(String id) {
        // Get src
        FolderEntity src = folderRepository.findById(id);
        // Get .trash
        FolderEntity userTrash = folderRepository.getUserTrashFolder(src.getOwnerId());
        // Move to .trash
        moveFolderToFolder(id, userTrash.getId());
    }

    /**
     * 删除文件夹
     *
     * @param id 文件夹ID
     */
    @Override
    public void deleteFolder(String id) {
        FolderEntity root = folderRepository.findById(id);
        if (root.getChildren() != null) {
            for (String child : root.getChildren()) {
                deleteFolder(child);
            }
        }
        if (root.getFiles() != null) {
            for (String child : root.getFiles()) {
                fileRepository.delete(child);
            }
        }
        FolderEntity parent = folderRepository.findOne(root.getParent());
        List<String> parentChildren = parent.getChildren();
        parentChildren.remove(root);
        parent.setChildren(parentChildren);
        folderRepository.save(parent);

        folderRepository.delete(root);
    }

    /**
     * 获取所有子文件夹
     *
     * @param id 父文件夹ID
     * @return 子文件夹列表
     */
    @Override
    public List<FolderEntity> getSubFolders(String id) {
        FolderEntity folder = folderRepository.findById(id);
        if (folder.getChildren() == null) {
            return new ArrayList<>();
        }
        return folder.getChildren().stream().map(folderRepository::findById).collect(Collectors.toList());
    }

    /**
     * 获取文件夹内所有文件
     *
     * @param id 文件夹ID
     * @return 文件列表
     */
    @Override
    public List<FileEntity> getFiles(String id) {
        FolderEntity folder = folderRepository.findById(id);
        if (folder.getFiles() == null) {
            return new ArrayList<>();
        }
        return folder.getFiles().stream().map(fileRepository::findById).collect(Collectors.toList());
    }
}

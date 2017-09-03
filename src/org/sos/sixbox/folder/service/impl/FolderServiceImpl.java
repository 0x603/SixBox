package org.sos.sixbox.folder.service.impl;

import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        FolderEntity root = new FolderEntity();
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
        FolderEntity userRoot = new FolderEntity();
        userRoot.setName(user.getUsername());
        userRoot.setOwnerId(user.getId());
        userRoot.setParent(folderRepository.getRootFolder().getId());
        userRoot = createFolder(userRoot);

        // 创建用户垃圾箱
        FolderEntity userTrash = new FolderEntity();
        userTrash.setName(".trash");
        userTrash.setOwnerId(user.getId());
        userTrash.setParent(userRoot.getId());
        createFolder(userTrash);
    }

    /**
     * 移动文件夹
     *
     * @param srcId 被移动文件夹的ID
     * @param dstId 移入文件夹的ID
     */
    @Override
    public void moveFolder(String srcId, String dstId) {
        FolderEntity src = folderRepository.findById(srcId);
        FolderEntity dst = folderRepository.findById(dstId);
        FolderEntity srcParent = folderRepository.findById(src.getParent());

        // Remove src from srcParent.children
        List<String> srcParentChildren = srcParent.getChildren();
        srcParentChildren.remove(srcId);
        srcParent.setChildren(srcParentChildren);

        // Move src to dst.children
        List<String> dstChildren = dst.getChildren();
        dstChildren.add(srcId);
        dst.setChildren(dstChildren);

        // Update src.parent
        src.setParent(dstId);
    }

    /**
     * 文件夹移入垃圾箱
     *
     * @param id 文件夹ID
     */
    @Override
    public void moveToTrash(String id) {
        // Get src
        FolderEntity src = folderRepository.findById(id);
        // Get .trash
        FolderEntity userTrash = folderRepository.getUserTrashFolder(src.getOwnerId());
        // Move to .trash
        moveFolder(id, userTrash.getId());
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
        folderRepository.delete(root);
    }
}

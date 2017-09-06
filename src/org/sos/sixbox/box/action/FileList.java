package org.sos.sixbox.box.action;

import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 查询登录用户的文件列表
 */
@Controller
public class FileList extends ActionVariableSupport {
    private final UserService userService;
    private final FolderRepository folderRepository;
    private final FolderService folderService;


    @Autowired
    public FileList(UserService userService, FolderRepository folderRepository, FolderService folderService) {
        this.userService = userService;
        this.folderRepository = folderRepository;
        this.folderService = folderService;
    }

    public String execute() throws IOException {
        // 确定用户
        int userId = ((UserEntity) httpSession.get("userEntity")).getId();
        UserEntity user = userService.getById(userId);

        // 确定位置
        String folderId = httpServletRequest.getParameter("pwd");
        System.out.println("1: " + folderId);
        if (folderId == null || folderId.isEmpty()) {
            folderId = folderRepository.getUserRootFolder(user.getUsername(), user.getId()).getId();
            System.out.println("2: " + folderId);
        }
        FolderEntity pwd =
                folderId == null
                        ? folderRepository.getUserRootFolder(user.getUsername(), user.getId())
                        : folderRepository.findById(folderId);
        System.out.println("3: " + pwd);

        // 获取子文件夹
        List<FolderEntity> folderEntityList = folderService.getSubFolders(pwd.getId());
        httpServletRequest.setAttribute("folders", folderEntityList);

        // 获取子文件
        List<FileEntity> fileEntityList = folderService.getFiles(pwd.getId());
        System.out.println(fileEntityList);
        httpServletRequest.setAttribute("files", fileEntityList);

        return SUCCESS;
    }
}

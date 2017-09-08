package org.sos.sixbox.box.action;

import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 恢复文件
 */
@Controller
public class Recover extends ActionVariableSupport {

    private final FolderService folderService;
    private final FolderRepository folderRepository;

    @Autowired
    public Recover(FolderService folderService, FolderRepository folderRepository) {
        this.folderService = folderService;
        this.folderRepository = folderRepository;
    }

    public String execute() throws IOException {
        String fid = httpServletRequest.getParameter("fid");
        String type = httpServletRequest.getParameter("type");

        UserEntity userEntity = (UserEntity) httpSession.get("userEntity");
        FolderEntity userRoot = folderRepository.getUserRootFolder(userEntity.getUsername(), userEntity.getId());

        if ("file".equals(type)) {
            // TODO: move file to ~/
        } else if ("folder".equals(type)) {
            folderService.moveFolderToFolder(fid, userRoot.getId());
        }
        return SUCCESS;
    }
}

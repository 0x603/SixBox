package org.sos.sixbox.box.action;

import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 创建文件夹
 */
@Controller
public class CreateFolder extends ActionVariableSupport {

    private final FolderRepository folderRepository;
    private final FolderService folderService;

    @Autowired
    public CreateFolder(FolderRepository folderRepository, FolderService folderService) {
        this.folderRepository = folderRepository;
        this.folderService = folderService;
    }

    public String execute() throws IOException {
        UserEntity user = (UserEntity) httpSession.get("userEntity");

        String fid = httpServletRequest.getParameter("fid");
        if (fid == null || fid.isEmpty() || fid.equals("null")) {
            fid = folderRepository.getUserRootFolder(user.getUsername(), user.getId()).getId();
        }
        System.out.println(fid);
        String name = httpServletRequest.getParameter("name");
        if (name == null || name.isEmpty() || name.equals("null")) {
            name = "New Folder";
            List<FolderEntity> subFolders = folderService.getSubFolders(fid);
            int cnt = 0;
            String tmp;
            while (true) {
                boolean existed = false;
                tmp = cnt == 0 ? name : name + " (" + String.valueOf(cnt) + ")";
                for (FolderEntity subFolder : subFolders) {
                    if (subFolder.getName().equals(tmp)) {
                        existed = true;
                        break;
                    }
                }
                if (existed) {
                    cnt += 1;
                } else {
                    break;
                }
            }
            name = tmp;
        }

        FolderEntity folderEntity = new FolderEntity();
        folderEntity.setName(name);
        folderEntity.setOwnerId(user.getId());
        folderEntity.setParent(fid);
        folderEntity = folderService.createFolder(folderEntity);
        System.out.println(folderEntity.getId());

        folderService.moveFolderToFolder(folderEntity.getId(), fid);

        httpServletResponse.sendRedirect("/box/home.jsp?pwd=" + fid);

        return SUCCESS;
    }
}

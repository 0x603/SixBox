package org.sos.sixbox.box.action;

import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 彻底删除文件
 * <p>
 * Usage:
 * 登录状态下，POST待删除文件实体的ID(fid)
 */
@Controller
public class Delete extends ActionVariableSupport {

    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;
    private final FolderService folderService;

    @Autowired
    public Delete(FileRepository fileRepository, FolderRepository folderRepository, FolderService folderService) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
        this.folderService = folderService;
    }

    public String execute() throws IOException {
        String fid = httpServletRequest.getParameter("fid");
        String type = httpServletRequest.getParameter("type");
        System.out.println(fid + ", " + type);

        if ("file".equals(type)) {
            fileRepository.delete(fid);
        } else if ("folder".equals(type)) {
            folderService.deleteFolder(fid);
        }
        System.out.println("DONE");
        return SUCCESS;
    }
}

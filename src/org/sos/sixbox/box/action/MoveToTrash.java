package org.sos.sixbox.box.action;

import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 移入回收站
 */
@Controller
public class MoveToTrash extends ActionVariableSupport {

    private final FolderService folderService;

    @Autowired
    public MoveToTrash(FolderService folderService) {
        this.folderService = folderService;
    }

    public String execute() throws IOException {
        String fid = httpServletRequest.getParameter("fid");
        String type = httpServletRequest.getParameter("type");

        if ("file".equals(type)) {
            // TODO: move file to trash
        } else if ("folder".equals(type)) {
            folderService.moveFolderToTrash(fid);
        }
        return SUCCESS;
    }
}

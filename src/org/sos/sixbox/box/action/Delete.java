package org.sos.sixbox.box.action;

import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.folder.repository.FolderRepository;
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

    @Autowired
    public Delete(FileRepository fileRepository, FolderRepository folderRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
    }

    public String execute() throws IOException {
        String fid = httpServletRequest.getParameter("fid");
        String type = httpServletRequest.getParameter("type");

        if ("file".equals(type)) {
            fileRepository.delete(fid);
        } else if ("folder".equals(type)) {
            folderRepository.delete(fid);
        }
        return SUCCESS;
    }
}

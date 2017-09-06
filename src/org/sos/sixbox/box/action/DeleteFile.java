package org.sos.sixbox.box.action;

import org.sos.sixbox.box.repository.FileRepository;
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
public class DeleteFile extends ActionVariableSupport {

    private final FileRepository fileRepository;

    @Autowired
    public DeleteFile(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String execute() throws IOException {
        String fid = httpServletRequest.getParameter("fid");
        fileRepository.deleteById(fid);
        return SUCCESS;
    }
}

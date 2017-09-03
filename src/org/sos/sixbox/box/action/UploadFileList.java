package org.sos.sixbox.box.action;

import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.box.service.BoxService;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 查询登录用户所有上传的文件
 *
 * Usage:
 * 登录状态下，GET后在jsp页面中从request获取fileEntityList
 */
@Controller
public class UploadFileList extends ActionVariableSupport {
    private final BoxService boxService;
    private final UserService userService;
    private final FileRepository fileRepository;
    private File file;
    private String fileContentType;
    private String fileFileName;


    @Autowired
    public UploadFileList(BoxService boxService, UserService userService, FileRepository fileRepository) {
        this.boxService = boxService;
        this.userService = userService;
        this.fileRepository = fileRepository;
    }

    public String execute() throws IOException {
        int userId = ((UserEntity) httpSession.get("userEntity")).getId();
        List<FileEntity> fileEntityList = fileRepository.findAllByOwnerId(userId);
        httpServletRequest.setAttribute("fileEntityList", fileEntityList);
        return SUCCESS;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
}

package org.sos.sixbox.box.action;

import org.sos.sixbox.box.service.BoxService;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lodour on 2017/9/3 16:42.
 */
@Controller
public class UploadFile extends ActionVariableSupport {
    private final BoxService boxService;
    private File file;
    private String fileContentType;
    private String fileFileName;


    @Autowired
    public UploadFile(BoxService boxService) {
        this.boxService = boxService;
    }

    public String execute() throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setOwnerId(((UserEntity) httpSession.get("userEntity")).getId());
        fileEntity.setFilename(fileFileName);
        fileEntity.setContentType(fileContentType);
        boxService.upload(fileEntity, file);
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

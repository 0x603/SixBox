package org.sos.sixbox.box.action;

import org.sos.sixbox.box.service.BoxService;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 上传单个文件
 * <p>
 * Usage:
 * 登录状态下，提交类似如下表单
 * http://paste.ubuntu.com/25458597/
 */
@Controller
public class UploadFile extends ActionVariableSupport {
    private final BoxService boxService;
    private final FolderRepository folderRepository;
    private File[] file;
    private String[] fileContentType;
    private String[] fileFileName;


    @Autowired
    public UploadFile(BoxService boxService, FolderRepository folderRepository) {
        this.boxService = boxService;
        this.folderRepository = folderRepository;
    }

    public String execute() throws IOException {
        UserEntity user = (UserEntity) httpSession.get("userEntity");
        String pwd = httpServletRequest.getParameter("pwd");
        if (pwd == null || pwd.isEmpty()) {
            pwd = folderRepository.getUserRootFolder(user.getUsername(), user.getId()).getId();
        }

        for (int i = 0; i < file.length; i++) {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setOwnerId(user.getId());
            fileEntity.setFilename(fileFileName[i]);
            fileEntity.setContentType(fileContentType[i]);
            fileEntity = boxService.upload(fileEntity, file[i]);

            System.out.println("1 " + pwd);
            FolderEntity folder = folderRepository.findById(pwd);
            System.out.println("2 " + folder);
            List<String> files = (folder.getFiles() == null) ? new ArrayList<>() : folder.getFiles();
            files.add(fileEntity.getId());
            folder.setFiles(files);
            folderRepository.save(folder);
        }
        return SUCCESS;
    }

    public void setFile(File[] file) {
        this.file = file;
    }

    public void setFileContentType(String[] fileContentType) {
        this.fileContentType = fileContentType;
    }

    public void setFileFileName(String[] fileFileName) {
        this.fileFileName = fileFileName;
    }
}

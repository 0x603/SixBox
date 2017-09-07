package org.sos.sixbox.box.action;

import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 文件夹重命名
 */
@Controller
public class Rename extends ActionVariableSupport {

    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;

    @Autowired
    public Rename(FolderRepository folderRepository, FileRepository fileRepository) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
    }

    public String execute() throws IOException {
        String fid = httpServletRequest.getParameter("fid");
        String name = httpServletRequest.getParameter("name");
        String type = httpServletRequest.getParameter("type");

        if ("file".equals(type)) {
            FileEntity fileEntity = fileRepository.findById(fid);
            fileEntity.setFilename(name);
            fileRepository.save(fileEntity);
        } else if ("folder".equals(type)) {
            FolderEntity folderEntity = folderRepository.findById(fid);
            folderEntity.setName(name);
            folderRepository.save(folderEntity);
        }
        return SUCCESS;
    }
}

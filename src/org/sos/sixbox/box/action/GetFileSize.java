package org.sos.sixbox.box.action;

import com.mongodb.gridfs.GridFSDBFile;
import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;

import java.io.IOException;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 获取文件大小
 */
@Controller
public class GetFileSize extends ActionVariableSupport {

    private final FileRepository fileRepository;
    private final GridFsOperations gridFsOperations;

    private String fid;

    @Autowired
    public GetFileSize(FileRepository fileRepository, GridFsOperations gridFsOperations) {
        this.fileRepository = fileRepository;
        this.gridFsOperations = gridFsOperations;
    }

    public String execute() throws IOException {
        String fileId = fileRepository.findOne(fid).getFileId();
        GridFSDBFile file = gridFsOperations.find(query(where("_id").is(fileId))).get(0);
        httpServletRequest.setAttribute("size", format(file.getLength()));
        return SUCCESS;
    }

    private String format(double size) {
        if (size < (1 << 10)) return String.format("%.2f", size) + " B";
        if (size < (1 << 20)) return String.format("%.2f", size / (1 << 10)) + " KB";
        if (size < (1 << 30)) return String.format("%.2f", size / (1 << 20)) + " MB";
        if (size < (1 << 40)) return String.format("%.2f", size / (1 << 30)) + " GB";
        return "NaN";
    }


    public void setFid(String fid) {
        this.fid = fid;
    }
}

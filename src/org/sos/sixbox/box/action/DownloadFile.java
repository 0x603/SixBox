package org.sos.sixbox.box.action;

import com.mongodb.gridfs.GridFSDBFile;
import org.sos.sixbox.box.service.BoxService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 下载文件
 */
@Controller
public class DownloadFile extends ActionVariableSupport {

    private final BoxService boxService;
    private InputStream inputStream;
    private String fid;
    private String filename;


    @Autowired
    public DownloadFile(BoxService boxService) {
        this.boxService = boxService;
    }

    public String execute() throws IOException {
        String fid = httpServletRequest.getParameter("fid");
        GridFSDBFile gridFSDBFile = boxService.download(fid);
        inputStream = gridFSDBFile.getInputStream();
        filename = gridFSDBFile.getFilename();
        return SUCCESS;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFilename() {
        return filename;
    }
}

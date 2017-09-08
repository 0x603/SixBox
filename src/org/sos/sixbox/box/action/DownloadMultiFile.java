package org.sos.sixbox.box.action;

import com.mongodb.gridfs.GridFSDBFile;
import org.sos.sixbox.box.service.BoxService;
import org.sos.sixbox.utils.ZipFileUtil;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 下载文件
 */
@Controller
public class DownloadMultiFile extends ActionVariableSupport {

    private final BoxService boxService;
    private InputStream inputStream;
    private String fid;
    private String filename;


    @Autowired
    public DownloadMultiFile(BoxService boxService) {
        this.boxService = boxService;
    }

    public String execute() throws IOException {
        String[] fids = fid.split(",");
        File[] files = new File[fids.length];
        String username = (String) httpSession.get("username");
        System.out.println("fids: " + Arrays.toString(fids));

        for (int i = 0; i < files.length; i++) {
            GridFSDBFile gridFSDBFile = boxService.download(fids[i]);
            files[i] = new File(gridFSDBFile.getFilename());
            gridFSDBFile.writeTo(files[i]);
        }
        filename = "/tmp/download.zip";
        ZipFileUtil.compressFiles2Zip(files, filename);

        inputStream = new FileInputStream(new File(filename));
        filename = "download.zip";
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

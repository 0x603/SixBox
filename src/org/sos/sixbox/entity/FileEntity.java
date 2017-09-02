package org.sos.sixbox.entity;

import com.mongodb.gridfs.GridFSFile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;


/**
 * Created by Lodour on 2017/9/1 20:39.
 */
@Document(collection = "file")
public class FileEntity {
    @Id
    private String id;
    private GridFSFile file; // TODO: 改为文件ID
    private UserEntity owner; // TODO: 改为用户ID
    private String filename;
    private String contentType;
    private Timestamp uploadTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GridFSFile getFile() {
        return file;
    }

    public void setFile(GridFSFile file) {
        this.file = file;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}

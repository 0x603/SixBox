package org.sos.sixbox.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * Created by Lodour on 2017/9/1 20:39.
 */
@Document(collection = "share")
public class ShareEntity {
    @Id
    private String id;
    private int sharedBy;
    private boolean isPublic;
    private String code;
    private String caption;
    private Date createTime;
    private String[] folders;
    private String[] files;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(int sharedBy) {
        this.sharedBy = sharedBy;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getFolders() {
        return folders;
    }

    public void setFolders(String[] folders) {
        this.folders = folders;
    }

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}

package org.sos.sixbox.box.action;

import org.sos.sixbox.entity.ShareEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.share.repository.ShareRepository;
import org.sos.sixbox.utils.Utils;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 共享
 */
@Controller
public class Share extends ActionVariableSupport {

    private final ShareRepository shareRepository;

    private String sharedFolders;
    private String sharedFiles;
    private String caption;

    // FIXME: 分享文件存在问题

    @Autowired
    public Share(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    public String execute() throws IOException {
        UserEntity userEntity = (UserEntity) httpSession.get("userEntity");

        ShareEntity shareEntity = new ShareEntity();
        shareEntity.setSharedBy(userEntity.getId());
        shareEntity.setFolders(sharedFolders.split(","));
        shareEntity.setFiles(sharedFiles.split(","));
        shareEntity.setCreateTime(Utils.getCurrentTimestamp());
        shareEntity.setCaption(caption);
        shareEntity = shareRepository.save(shareEntity);

        httpServletRequest.setAttribute("shareId", shareEntity.getId());

        return SUCCESS;
    }

    public void setSharedFolders(String sharedFolders) {
        this.sharedFolders = sharedFolders;
    }

    public void setSharedFiles(String sharedFiles) {
        this.sharedFiles = sharedFiles;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}

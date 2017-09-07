package org.sos.sixbox.box.action;

import org.sos.sixbox.entity.ShareEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.share.repository.ShareRepository;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 共享
 */
@Controller
public class ShareList extends ActionVariableSupport {

    private final ShareRepository shareRepository;

    private String shareId;

    @Autowired
    public ShareList(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    public String execute() throws IOException {
        UserEntity userEntity = (UserEntity) httpSession.get("userEntity");
        List<ShareEntity> shareEntityList = shareRepository.findAllBySharedBy(userEntity.getId());
        httpServletRequest.setAttribute("shareList", shareEntityList);

        return SUCCESS;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }
}

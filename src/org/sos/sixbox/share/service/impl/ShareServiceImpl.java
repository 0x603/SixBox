package org.sos.sixbox.share.service.impl;

import org.sos.sixbox.share.repository.ShareRepository;
import org.sos.sixbox.share.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lodour on 2017/9/7 13:36.
 */
@Service
public class ShareServiceImpl implements ShareService {
    private final ShareRepository shareRepository;

    @Autowired
    public ShareServiceImpl(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }
}

package org.sos.sixbox.share.repository;

import org.sos.sixbox.entity.ShareEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Lodour on 2017/9/1 21:39.
 * 共享仓库
 */
public interface ShareRepository extends MongoRepository<ShareEntity, String> {

    List<ShareEntity> findAllBySharedBy(int sharedBy);
}

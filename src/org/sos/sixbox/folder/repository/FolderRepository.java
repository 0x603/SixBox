package org.sos.sixbox.folder.repository;

import org.sos.sixbox.entity.FolderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by Lodour on 2017/9/1 21:39.
 * 文件夹仓库
 */
public interface FolderRepository extends MongoRepository<FolderEntity, String> {
    FolderEntity findById(String id);

    @Query("{'name': '/'}")
    FolderEntity getRootFolder();

    @Query("{'name': '.trash', 'ownerId': ?0}")
    FolderEntity getUserTrashFolder(int userId);
}

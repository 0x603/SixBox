package org.sos.sixbox.box.repository;

import org.sos.sixbox.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Lodour on 2017/9/1 21:39.
 * 文件仓库
 */
public interface FileRepository extends MongoRepository<FileEntity, String> {
    List<FileEntity> findAllByOwnerId(int id);

    void deleteAllByOwnerId(int id);

    void deleteById(String id);
}

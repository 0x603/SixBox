package org.sos.sixbox.file.repository;

import org.sos.sixbox.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Lodour on 2017/9/1 21:39.
 * 文件仓库
 */
public interface FileRepository extends MongoRepository<FileEntity, String> {
}

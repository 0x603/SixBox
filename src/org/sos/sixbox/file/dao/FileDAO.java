package org.sos.sixbox.file.dao;

import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;

/**
 * Created by zhangsj
 * 数据接口
 */
public interface FileDAO {

    /**
     * 存储文件
     *
     * @param file
     * @param filename
     */
    boolean store(Resource file, String filename);

    /**
     * 删除文件
     *
     * @param filename
     */
    void delete(String filename);

    /**
     * 读取文件
     *
     * @param filename
     */
     GridFsResource read(String filename);
}

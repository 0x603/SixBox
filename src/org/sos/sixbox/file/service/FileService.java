package org.sos.sixbox.file.service;

import com.mongodb.DBObject;
import org.sos.sixbox.entity.FileEntity;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Lodour on 2017/9/3 00:10.
 * 文件服务接口
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param fileEntity 文件实体
     * @param file       待上传文件
     * @param metaData   文件元数据
     * @throws FileNotFoundException 文件不存在
     */
    void upload(FileEntity fileEntity, File file, DBObject metaData) throws FileNotFoundException;

    /**
     * 上传文件(无元数据)
     *
     * @param fileEntity 文件实体
     * @param file       待上传文件
     * @throws FileNotFoundException 文件不存在
     */
    void upload(FileEntity fileEntity, File file) throws FileNotFoundException;
}

package org.sos.sixbox.box.service;

import com.mongodb.DBObject;
import org.sos.sixbox.entity.FileEntity;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Lodour on 2017/9/3 00:10.
 * 文件服务接口
 * 封装与GridFS相关的操作，仅与FileEntity相关的操作则在FileRepository中
 */
public interface BoxService {
    /**
     * 上传文件
     *
     * @param fileEntity 文件实体
     * @param file       待上传文件
     * @param metaData   文件元数据
     * @throws FileNotFoundException 文件不存在
     */
    FileEntity upload(FileEntity fileEntity, File file, DBObject metaData) throws FileNotFoundException;

    /**
     * 上传文件(无元数据)
     *
     * @param fileEntity 文件实体
     * @param file       待上传文件
     * @throws FileNotFoundException 文件不存在
     */
    FileEntity upload(FileEntity fileEntity, File file) throws FileNotFoundException;
}

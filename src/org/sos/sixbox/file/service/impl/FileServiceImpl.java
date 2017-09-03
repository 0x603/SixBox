package org.sos.sixbox.file.service.impl;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSFile;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.file.repository.FileRepository;
import org.sos.sixbox.file.service.FileService;
import org.sos.sixbox.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Lodour on 2017/9/3 00:13.
 * 文件服务
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final GridFsOperations gridFsOperations;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, GridFsOperations gridFsOperations) {
        this.fileRepository = fileRepository;
        this.gridFsOperations = gridFsOperations;
    }

    /**
     * 上传文件
     *
     * @param fileEntity 文件实体
     * @param file       待上传文件
     * @param metaData   文件元数据
     * @throws FileNotFoundException 文件不存在
     */
    @Override
    public void upload(FileEntity fileEntity, File file, DBObject metaData) throws FileNotFoundException {
        // Upload to GridFS
        FileInputStream fileStream = new FileInputStream(file);
        GridFSFile uploadFile = gridFsOperations.store(fileStream, fileEntity.getFilename(), metaData);

        // Save entity to MongoDB
        fileEntity.setFileId(uploadFile.getId().toString());
        fileEntity.setUploadTime(Utils.getCurrentTimestamp());
        fileEntity.setFilename(file.getName());
        fileRepository.save(fileEntity);
    }

    /**
     * 上传文件
     *
     * @param fileEntity 文件实体
     * @param file       待上传文件
     * @throws FileNotFoundException 文件不存在
     */
    @Override
    public void upload(FileEntity fileEntity, File file) throws FileNotFoundException {
        upload(fileEntity, file, null);
    }
}

package org.sos.sixbox.folder.service;

import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.FolderEntity;

import java.util.List;

/**
 * Created by Lodour on 2017/9/3 23:50.
 * 文件夹仓库接口
 */
public interface FolderService {

    /**
     * 创建文件夹
     *
     * @param folderEntity 待创建文件夹实体
     * @return 写入ID的文件夹实体
     */
    FolderEntity createFolder(FolderEntity folderEntity);

    /**
     * 初始化系统文件夹
     */
    void initSystemFolder();

    /**
     * 初始化用户文件夹
     *
     * @param userId 用户ID
     */
    void initUserFolder(int userId);

    /**
     * 移动文件夹
     *
     * @param srcId 被移动文件夹的ID
     * @param dstId 移入文件夹的ID
     */
    void moveFolderToFolder(String srcId, String dstId);

    /**
     * 文件夹移入垃圾箱
     *
     * @param id 文件夹ID
     */
    void moveFolderToTrash(String id);

    /**
     * 删除文件夹
     *
     * @param id 文件夹ID
     */
    void deleteFolder(String id);

    /**
     * 获取所有子文件夹
     *
     * @param id 父文件夹ID
     * @return 子文件夹列表
     */
    List<FolderEntity> getSubFolders(String id);

    /**
     * 获取文件夹内所有文件
     *
     * @param id 文件夹ID
     * @return 文件列表
     */
    List<FileEntity> getFiles(String id);
}

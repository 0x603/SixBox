package org.sos.sixbox.box.action;

import org.sos.sixbox.box.repository.FileRepository;
import org.sos.sixbox.entity.FileEntity;
import org.sos.sixbox.entity.FolderEntity;
import org.sos.sixbox.entity.ShareEntity;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.folder.repository.FolderRepository;
import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.share.repository.ShareRepository;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lodour on 2017/9/3 16:42.
 * 分享详情
 */
@Controller
public class ShareDetail extends ActionVariableSupport {
    private final ShareRepository shareRepository;
    private final FolderRepository folderRepository;
    private final FolderService folderService;
    private final FileRepository fileRepository;
    private final UserService userService;

    private String shareId;


    @Autowired
    public ShareDetail(ShareRepository shareRepository, FolderRepository folderRepository, FolderService folderService, FileRepository fileRepository, UserService userService) {
        this.shareRepository = shareRepository;
        this.folderRepository = folderRepository;
        this.folderService = folderService;
        this.fileRepository = fileRepository;
        this.userService = userService;
    }

    public String execute() throws IOException {
        System.out.println("shareId: " + shareId);

        ShareEntity shareEntity = shareRepository.findOne(shareId);


        List<FolderEntity> folders = Arrays.stream(shareEntity.getFolders()).map(folderRepository::findOne).collect(Collectors.toList());
        List<FileEntity> files = Arrays.stream(shareEntity.getFiles()).map(fileRepository::findOne).collect(Collectors.toList());

        httpServletRequest.setAttribute("folders", folders);
        httpServletRequest.setAttribute("files", files);
        httpServletRequest.setAttribute("shareEntity", shareEntity);

        UserEntity userEntity = userService.getById(shareEntity.getSharedBy());
        httpServletRequest.setAttribute("sharedBy", userEntity);
        System.out.println(userEntity);
        return SUCCESS;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

}

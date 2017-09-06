package org.sos.sixbox.user.action;

import com.opensymphony.xwork2.ModelDriven;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.folder.service.FolderService;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Lodour on 2017/8/18 15:45.
 * 注册用户
 */
@Controller
public class UserRegister extends ActionVariableSupport implements ModelDriven<UserEntity> {

    // 注入的用户服务接口
    private final UserService userService;
    private final FolderService folderService;

    // 与前端绑定的用户实体
    private UserEntity userEntity;

    @Autowired
    public UserRegister(UserService userService, FolderService folderService) {
        this.userService = userService;
        this.folderService = folderService;
        userEntity = new UserEntity();
    }

    public String execute() throws Exception {
        try {
            String ip = httpServletRequest.getRemoteAddr();
            userService.createUser(userEntity, ip);
            folderService.initUserFolder(userService.getByUsername(userEntity.getUsername()).getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public UserEntity getModel() {
        return userEntity;
    }
}

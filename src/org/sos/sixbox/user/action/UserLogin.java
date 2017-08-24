package org.sos.sixbox.user.action;

import com.opensymphony.xwork2.ModelDriven;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Lodour on 2017/8/18 15:45.
 * 用户登录
 */
@Controller
public class UserLogin extends ActionVariableSupport implements ModelDriven<UserEntity> {

    // 注入的用户服务接口
    private final UserService userService;

    // 与前端绑定的用户实体
    private UserEntity userEntity;

    @Autowired
    public UserLogin(UserService userService) {
        this.userService = userService;
        userEntity = new UserEntity();
    }

    public String execute() throws Exception {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        String ip = httpServletRequest.getRemoteAddr();
        if (userService.loginUser(username, password, ip)) {
            httpSession.put("username", username);
            return SUCCESS;
        }
        return ERROR;
    }

    @Override
    public UserEntity getModel() {
        return userEntity;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}

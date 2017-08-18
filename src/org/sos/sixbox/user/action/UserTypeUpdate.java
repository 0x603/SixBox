package org.sos.sixbox.user.action;

import org.sos.sixbox.constant.UserType;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Lodour on 2017/8/18 22:15.
 * 修改用户类型
 */
@Controller
public class UserTypeUpdate extends ActionVariableSupport {

    private final UserService userService;

    @Autowired
    public UserTypeUpdate(UserService userService) {
        this.userService = userService;
    }

    public String execute() {
        String username = httpServletRequest.getParameter("username");
        int userType = Integer.parseInt(httpServletRequest.getParameter("userType"));
        if (UserType.isValidUserType(userType)) {
            UserEntity userEntity = userService.getByUsername(username);
            userEntity.setUserType(userType);
            userService.updateUser(userEntity);
            return SUCCESS;
        }
        return ERROR;
    }
}

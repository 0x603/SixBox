package org.sos.sixbox.user.action;

import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Lodour on 2017/8/18 22:04.
 * 重置密码
 */
@Controller
public class PasswordReset extends ActionVariableSupport {
    private final UserService userService;

    @Autowired
    public PasswordReset(UserService userService) {
        this.userService = userService;
    }

    public String execute() {
        String username = (String) httpSession.get("username");
        UserEntity userEntity = userService.getByUsername(username);
        if (userEntity.getPassword().equals(httpServletRequest.getParameter("oldPassword"))) {
            userEntity.setPassword(httpServletRequest.getParameter("newPassword"));
            userService.updateUser(userEntity);
            httpServletRequest.setAttribute("result", true);
            return SUCCESS;
        } else {
            httpServletRequest.setAttribute("result", false);
            return ERROR;
        }
    }
}

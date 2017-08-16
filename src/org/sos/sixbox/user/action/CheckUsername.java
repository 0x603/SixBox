package org.sos.sixbox.user.action;

import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Lodour on 2017/8/16 12:45.
 * 检测用户名是否存在
 */
@Controller
public class CheckUsername extends ActionVariableSupport {
    private final UserService userService;

    @Autowired
    public CheckUsername(UserService userService) {
        this.userService = userService;
    }

    public String execute() throws Exception {
        String username = httpServletRequest.getParameter("username");
        httpServletRequest.setAttribute("result", userService.chkUsername(username));
        return SUCCESS;
    }
}

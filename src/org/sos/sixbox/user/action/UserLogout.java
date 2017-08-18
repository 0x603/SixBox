package org.sos.sixbox.user.action;

import org.sos.sixbox.utils.action.ActionVariableSupport;
import org.springframework.stereotype.Controller;

/**
 * Created by Lodour on 2017/8/18 22:13.
 * 用户注销
 */
@Controller
public class UserLogout extends ActionVariableSupport {
    public String execute() {
        httpSession.put("username", null);
        return SUCCESS;
    }
}

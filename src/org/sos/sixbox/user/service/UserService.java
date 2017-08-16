package org.sos.sixbox.user.service;

/**
 * Created by Lodour on 2017/8/16 11:36.
 * 用户服务接口
 */
public interface UserService {
    /**
     * 检测用户名是否存在
     *
     * @param username 用户名
     * @return 是否已存在
     */
    boolean chkUsername(String username);
}

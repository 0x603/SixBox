package org.sos.sixbox.user.service.impl;

import org.sos.sixbox.user.dao.UserDAO;
import org.sos.sixbox.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

/**
 * Created by Lodour on 2017/8/16 12:17.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 检测用户名是否存在
     *
     * @param username 用户名
     * @return 是否已存在
     */
    @Override
    public boolean chkUsername(String username) {
        try {
            userDAO.getByUsername(username);
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }
}

package org.sos.sixbox.user.service.impl;

import org.sos.sixbox.constant.UserType;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.user.dao.UserDAO;
import org.sos.sixbox.user.service.UserService;
import org.sos.sixbox.utils.Utils;
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

    /**
     * 创建新用户
     *
     * @param userEntity 待创建用户实体
     * @param ip         注册IP
     */
    @Override
    public void createUser(UserEntity userEntity, String ip) {
        userEntity.setUserType(UserType.USER);
        userEntity.setCreateIp(ip);
        userEntity.setCreateTime(Utils.getCurrentTimestamp());
        userDAO.create(userEntity);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param ip       登录IP
     * @return 登录结果
     */
    @Override
    public boolean loginUser(String username, String password, String ip) {
        if (!userDAO.checkUsernameAndPassword(username, password)) {
            return false;
        }
        userDAO.updateLastLogin(username, ip);
        return true;
    }

    /**
     * 根据用户名获取用户实体
     *
     * @param username 用户名
     * @return 用户实体
     */
    @Override
    public UserEntity getByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    /**
     * 更新用户实体
     *
     * @param userEntity 用户实体
     */
    @Override
    public void updateUser(UserEntity userEntity) {
        userDAO.update(userEntity);
    }

    /**
     * 根据id获取用户实体
     *
     * @param id 用户id
     * @return 用户实体
     */
    @Override
    public UserEntity getById(int id) {
        return userDAO.getById(id);
    }
}

package org.sos.sixbox.user.service;

import org.sos.sixbox.entity.UserEntity;

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

    /**
     * 创建新用户
     *
     * @param userEntity 待创建用户实体
     * @param ip         注册IP
     */
    void createUser(UserEntity userEntity, String ip);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param ip       登录IP
     * @return 登录结果
     */
    boolean loginUser(String username, String password, String ip);

    /**
     * 根据用户名获取用户实体
     *
     * @param username 用户名
     * @return 用户实体
     */
    UserEntity getByUsername(String username);

    /**
     * 更新用户实体
     *
     * @param userEntity 用户实体
     */
    void updateUser(UserEntity userEntity);

    /**
     * 根据id获取用户实体
     *
     * @param id 用户id
     * @return 用户实体
     */
    UserEntity getById(int id);
}

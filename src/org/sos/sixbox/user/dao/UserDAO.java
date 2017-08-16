package org.sos.sixbox.user.dao;

import org.sos.sixbox.entity.UserEntity;

/**
 * Created by Lodour on 2017/8/16 10:09.
 * 用户数据接口
 */
public interface UserDAO {
    /**
     * 创建用户
     *
     * @param userEntity 待创建用户实体
     */
    void create(UserEntity userEntity);

    /**
     * 更新用户
     *
     * @param userEntity 更新的用户实体
     */
    void update(UserEntity userEntity);

    /**
     * 根据id获取用户
     *
     * @param id 用户ID
     * @return 用户实体
     */
    UserEntity getById(int id);

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户实体
     */
    UserEntity getByUsername(String username);

    /**
     * 获取所有用户实体
     *
     * @return 所有用户实体
     */
    UserEntity[] getAllUserEntities();
}

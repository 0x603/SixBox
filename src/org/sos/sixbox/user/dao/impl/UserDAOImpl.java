package org.sos.sixbox.user.dao.impl;

import org.hibernate.SessionFactory;
import org.sos.sixbox.entity.UserEntity;
import org.sos.sixbox.user.dao.UserDAO;
import org.sos.sixbox.utils.Utils;
import org.sos.sixbox.utils.dao.DAOSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lodour on 2017/8/16 11:23.
 */
@Repository
@Transactional
public class UserDAOImpl extends DAOSupport implements UserDAO {

    protected UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * 创建用户
     *
     * @param userEntity 待创建用户实体
     */
    @Override
    public void create(UserEntity userEntity) {
        userEntity.setCreateTime(Utils.getCurrentTimestamp());
        getSession().save(userEntity);
    }

    /**
     * 更新用户
     *
     * @param userEntity 更新的用户实体
     */
    @Override
    public void update(UserEntity userEntity) {
        getSession().update(userEntity);
    }

    /**
     * 根据id获取用户
     *
     * @param id 用户ID
     * @return 用户实体
     */
    @Override
    public UserEntity getById(int id) {
        return (UserEntity) getSession()
                .createQuery("from UserEntity u where u.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户实体
     */
    @Override
    public UserEntity getByUsername(String username) {
        return (UserEntity) getSession()
                .createQuery("from UserEntity u where u.username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }

    /**
     * 获取所有用户实体
     *
     * @return 所有用户实体
     */
    @Override
    public UserEntity[] getAllUserEntities() {
        return (UserEntity[]) getSession()
                .createQuery("from UserEntity")
                .getResultList().toArray();
    }
}

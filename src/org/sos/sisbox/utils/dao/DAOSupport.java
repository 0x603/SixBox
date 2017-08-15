package org.sos.sisbox.utils.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Lodour on 2017/8/15 14:03.
 * DAO助手
 */
public class DAOSupport {
    private SessionFactory sessionFactory;

    protected DAOSupport(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
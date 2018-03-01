package cn.imaq.order.data.impl;

import cn.imaq.order.data.UserDAO;
import cn.imaq.order.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM User WHERE username = ?0", User.class);
        query.setParameter(0, username);
        User result = query.uniqueResult();
        session.close();
        return result;
    }
}

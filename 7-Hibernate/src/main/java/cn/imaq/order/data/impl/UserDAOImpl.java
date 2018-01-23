package cn.imaq.order.data.impl;

import cn.imaq.order.data.HibernateUtil;
import cn.imaq.order.data.UserDAO;
import cn.imaq.order.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.naming.NamingException;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static volatile UserDAOImpl instance;

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            synchronized (UserDAOImpl.class) {
                if (instance == null) {
                    instance = new UserDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public User getUserByUsername(String username) throws SQLException, NamingException {
        Session session = HibernateUtil.getSession();
        Query<User> query = session.createQuery("FROM User WHERE username = ?0", User.class);
        query.setParameter(0, username);
        User result = query.uniqueResult();
        session.close();
        return result;
    }
}

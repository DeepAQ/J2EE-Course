package cn.imaq.order.data.impl;

import cn.imaq.order.data.UserDAO;
import cn.imaq.order.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDAOBean implements UserDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public User getUserByUsername(String username) {
        User result = (User) em.createNativeQuery("SELECT * FROM user WHERE username = ?", User.class)
                .setParameter(1, username)
                .getSingleResult();
        em.clear();
        return result;
    }
}

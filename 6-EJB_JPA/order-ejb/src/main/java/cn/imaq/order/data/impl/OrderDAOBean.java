package cn.imaq.order.data.impl;

import cn.imaq.order.data.OrderDAO;
import cn.imaq.order.model.Order;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class OrderDAOBean implements OrderDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Order> getOrdersByUserId(Integer userId, Integer page, Integer pageCount, Boolean oosOnly) throws SQLException, NamingException {
        Query query;
        if (oosOnly) {
            query = em.createNativeQuery("SELECT * FROM orders WHERE userid = ? AND oos = 1 ORDER BY time DESC LIMIT ?, ?", Order.class);
        } else {
            query = em.createNativeQuery("SELECT * FROM orders WHERE userid = ? ORDER BY time DESC LIMIT ?, ?", Order.class);
        }
        query.setParameter(1, userId);
        query.setParameter(2, (page - 1) * pageCount);
        query.setParameter(3, pageCount);
        List<Order> result = query.getResultList();
        em.clear();
        return result;
    }

    @Override
    public Integer getOrderCountByUserId(Integer userId) throws SQLException, NamingException {
        int count = em.createNativeQuery("SELECT * FROM orders WHERE userid = ?")
                .setParameter(1, userId)
                .getResultList().size();
        em.clear();
        return count;
    }

    @Override
    public Integer getOutOfStockCountByUserId(Integer userId) throws SQLException, NamingException {
        int count = em.createNativeQuery("SELECT * FROM orders WHERE userid = ? AND oos = 1")
                .setParameter(1, userId)
                .getResultList().size();
        em.clear();
        return count;
    }
}

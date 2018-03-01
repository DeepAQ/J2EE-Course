package cn.imaq.order.data.impl;

import cn.imaq.order.data.OrderDAO;
import cn.imaq.order.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getOrdersByUserId(Integer userId, Integer page, Integer pageCount, Boolean oosOnly) {
        Session session = sessionFactory.openSession();
        Query<Order> query;
        if (oosOnly) {
            query = session.createQuery("FROM Order WHERE userid = ?0 AND oos = 1 ORDER BY time DESC", Order.class);
        } else {
            query = session.createQuery("FROM Order WHERE userid = ?0 ORDER BY time DESC", Order.class);
        }
        query.setParameter(0, userId);
        query.setFirstResult((page - 1) * pageCount);
        query.setMaxResults(pageCount);
        List<Order> result = query.list();
        session.close();
        return result;
    }

    @Override
    public Integer getOrderCountByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT count(*) FROM Order WHERE userid = ?0");
        query.setParameter(0, userId);
        Integer result = ((Long) query.uniqueResult()).intValue();
        session.close();
        return result;
    }

    @Override
    public Integer getOutOfStockCountByUserId(Integer userId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT count(*) FROM Order WHERE userid = ?0 AND oos = 1");
        query.setParameter(0, userId);
        Integer result = ((Long) query.uniqueResult()).intValue();
        session.close();
        return result;
    }
}

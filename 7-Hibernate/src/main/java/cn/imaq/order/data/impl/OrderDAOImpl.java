package cn.imaq.order.data.impl;

import cn.imaq.order.data.HibernateUtil;
import cn.imaq.order.data.OrderDAO;
import cn.imaq.order.model.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static volatile OrderDAOImpl instance;

    public static OrderDAOImpl getInstance() {
        if (instance == null) {
            synchronized (OrderDAOImpl.class) {
                if (instance == null) {
                    instance = new OrderDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId, Integer page, Integer pageCount, Boolean oosOnly) throws SQLException, NamingException {
        Session session = HibernateUtil.getSession();
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
    public Integer getOrderCountByUserId(Integer userId) throws SQLException, NamingException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("SELECT count(*) FROM Order WHERE userid = ?0");
        query.setParameter(0, userId);
        Integer result = ((Long) query.uniqueResult()).intValue();
        session.close();
        return result;
    }

    @Override
    public Integer getOutOfStockCountByUserId(Integer userId) throws SQLException, NamingException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("SELECT count(*) FROM Order WHERE userid = ?0 AND oos = 1");
        query.setParameter(0, userId);
        Integer result = ((Long) query.uniqueResult()).intValue();
        session.close();
        return result;
    }
}

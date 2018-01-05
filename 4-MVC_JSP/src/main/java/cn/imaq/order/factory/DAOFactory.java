package cn.imaq.order.factory;

import cn.imaq.order.data.OrderDAO;
import cn.imaq.order.data.UserDAO;
import cn.imaq.order.data.impl.OrderDAOImpl;
import cn.imaq.order.data.impl.UserDAOImpl;

public class DAOFactory {
    public static UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    public static OrderDAO getOrderDAO() {
        return OrderDAOImpl.getInstance();
    }
}

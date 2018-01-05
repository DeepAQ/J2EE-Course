package cn.imaq.order.factory;

import cn.imaq.order.service.OrderService;
import cn.imaq.order.service.UserService;
import cn.imaq.order.service.impl.OrderServiceImpl;
import cn.imaq.order.service.impl.UserServiceImpl;

public class ServiceFactory {
    public static UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public static OrderService getOrderService() {
        return OrderServiceImpl.getInstance();
    }
}

package cn.imaq.order.data;

import cn.imaq.order.model.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrdersByUserId(Integer userId, Integer page, Integer pageCount, Boolean oosOnly) throws Exception;

    Integer getOrderCountByUserId(Integer userId) throws Exception;

    Integer getOutOfStockCountByUserId(Integer userId) throws Exception;
}

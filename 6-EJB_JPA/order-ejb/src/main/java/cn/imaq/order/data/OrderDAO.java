package cn.imaq.order.data;

import cn.imaq.order.model.Order;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderDAO {
    List<Order> getOrdersByUserId(Integer userId, Integer page, Integer pageCount, Boolean oosOnly) throws Exception;

    Integer getOrderCountByUserId(Integer userId) throws Exception;

    Integer getOutOfStockCountByUserId(Integer userId) throws Exception;
}

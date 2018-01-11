package cn.imaq.order.model.dto;

import cn.imaq.order.model.Order;

import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable {
    private int orderCount;

    private int outOfStockCount;

    private int pageCount;

    private List<Order> orders;

    public OrderDTO(int orderCount, int outOfStockCount, int pageCount, List<Order> orders) {
        this.orderCount = orderCount;
        this.outOfStockCount = outOfStockCount;
        this.pageCount = pageCount;
        this.orders = orders;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int getOutOfStockCount() {
        return outOfStockCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<Order> getOrders() {
        return orders;
    }
}

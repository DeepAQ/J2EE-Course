package cn.imaq.order.service;

import cn.imaq.order.model.ResultMessage;
import cn.imaq.order.model.dto.OrderDTO;

import javax.ejb.Remote;

@Remote
public interface OrderService {
    ResultMessage<OrderDTO> getOrderInfo(int userId, int page, boolean oosOnly);
}

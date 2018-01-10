package cn.imaq.order.service;

import cn.imaq.order.model.ResultMessage;
import cn.imaq.order.model.dto.OrderDTO;

public interface OrderService {
    ResultMessage<OrderDTO> getOrderInfo(int userId, int page, boolean oosOnly);
}

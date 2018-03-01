package cn.imaq.order.service.impl;

import cn.imaq.order.data.OrderDAO;
import cn.imaq.order.model.ResultMessage;
import cn.imaq.order.model.dto.OrderDTO;
import cn.imaq.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final int ORDER_PER_PAGE = 5;

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public ResultMessage<OrderDTO> getOrderInfo(int userId, int page, boolean oosOnly) {
        try {
            int totalCount = orderDAO.getOrderCountByUserId(userId);
            int outOfStockCount = orderDAO.getOutOfStockCountByUserId(userId);
            int pageNum = ((oosOnly ? outOfStockCount : totalCount) + ORDER_PER_PAGE - 1) / ORDER_PER_PAGE;
            return ResultMessage.success(new OrderDTO(
                    totalCount, outOfStockCount, pageNum,
                    orderDAO.getOrdersByUserId(userId, page, ORDER_PER_PAGE, oosOnly)
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.failure(e.toString());
        }
    }
}

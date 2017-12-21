package cn.imaq.order.servlet;

import cn.imaq.order.data.OrderDAO;
import cn.imaq.order.model.Order;
import cn.imaq.order.util.UTF8Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class OrderServlet extends HttpServlet {
    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        UTF8Writer.write(resp, "<!doctype html><html><head><title>My Orders</title></head><body>" +
                "<h2>订单查询系统</h2>");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userid") == null) {
            UTF8Writer.write(resp, "<p>当前未登录，<a href=\"/login\">点击登录</a></p>");
        } else {
            UTF8Writer.write(resp, "<p>当前用户：<strong>" +
                    session.getAttribute("username") +
                    "</strong> <a href=\"/logout\">退出登录</a></p>"
            );
            try {
                Integer userId = (Integer) session.getAttribute("userid");
                List<Order> orders = orderDAO.getOrdersByUserId(userId);
                UTF8Writer.write(resp, "<p>共有 " + orders.size() + " 个订单</p>");
                Integer outOfStockCount = orderDAO.getOutOfStockCountByUserId(userId);
                if (outOfStockCount > 0) {
                    UTF8Writer.write(resp, "<p><strong>注意！" + outOfStockCount + " 个订单存在缺货！</strong></p>");
                }
                StringBuilder orderTable = new StringBuilder();
                for (Order order : orders) {
                    orderTable.append("<tr><td>")
                            .append(order.getTime()).append("</td><td>")
                            .append(order.getName()).append("</td><td>")
                            .append(order.getAmount()).append("</td><td>")
                            .append(order.getPrice()).append("</td><td>")
                            .append(order.isOutOfStock() ? "<span style=\"color:red;\">缺货</span>" : "").append("</td></tr>");
                }
                UTF8Writer.write(resp, "<table border=\"1\"><thead>" +
                        "<tr><th>订单时间</th><th>商品名称</th><th>数量</th><th>总价</th><th>备注</th>" +
                        "</thead><tbody>" + orderTable.toString() + "</tbody></table>");
            } catch (Exception e) {
                e.printStackTrace();
                UTF8Writer.write(resp, "查询错误：" + e);
            }
        }
        UTF8Writer.write(resp, "</body></html>");
    }
}

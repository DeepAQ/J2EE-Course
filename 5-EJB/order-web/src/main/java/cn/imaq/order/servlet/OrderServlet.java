package cn.imaq.order.servlet;

import cn.imaq.order.factory.EJBFactory;
import cn.imaq.order.listener.UserCountListener;
import cn.imaq.order.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = EJBFactory.getEJB("ejb:/j2ee-order-ejb-1.0-SNAPSHOT//OrderServiceBean!cn.imaq.order.service.OrderService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        req.setAttribute("userCount", UserCountListener.getUserCounts());
        if (session.getAttribute("userid") != null) {
            int userId = (int) session.getAttribute("userid");
            int page = req.getParameter("page") != null ? Integer.valueOf(req.getParameter("page")) : 1;
            boolean oosOnly = req.getParameter("oos") != null;
            req.setAttribute("currentPage", page);
            req.setAttribute("oosOnly", oosOnly);
            req.setAttribute("result", orderService.getOrderInfo(userId, page, oosOnly));
        }
        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
    }
}

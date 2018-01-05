package cn.imaq.order.servlet;

import cn.imaq.order.factory.ServiceFactory;
import cn.imaq.order.listener.UserCountListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class OrderServlet extends HttpServlet {
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
            req.setAttribute("result", ServiceFactory.getOrderService().getOrderInfo(userId, page, oosOnly));
        }
        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
    }
}

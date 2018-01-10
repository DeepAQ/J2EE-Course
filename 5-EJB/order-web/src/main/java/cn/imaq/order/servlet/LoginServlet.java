package cn.imaq.order.servlet;

import cn.imaq.order.factory.EJBFactory;
import cn.imaq.order.model.ResultMessage;
import cn.imaq.order.model.User;
import cn.imaq.order.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = EJBFactory.getEJB("ejb:/j2ee-order-ejb-1.0-SNAPSHOT//UserServiceBean!cn.imaq.order.service.UserService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ResultMessage<User> loginResult = userService.login(username, password);
        if (loginResult.isSuccess()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("userid", loginResult.getResult().getId());
            session.setAttribute("username", loginResult.getResult().getUsername());
            resp.sendRedirect("/");
        } else {
            req.setAttribute("msg", loginResult.getMessage());
            req.getRequestDispatcher("/jsp/login_error.jsp").forward(req, resp);
        }
    }
}

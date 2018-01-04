package cn.imaq.order.servlet;

import cn.imaq.order.data.UserDAO;
import cn.imaq.order.model.User;
import cn.imaq.order.util.UTF8Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UTF8Writer.write(resp,
                "<!doctype html><html><head><title>订单查询系统</title></head><body>" +
                        "<h2>用户登录</h2>" +
                        "<form method=\"post\">" +
                        "用户名: <input name=\"username\"><br>" +
                        "密码: <input type=\"password\" name=\"password\"><br>" +
                        "<button type=\"submit\">登录</button>" +
                        "</form></body></html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null) {
                if (password.equals(user.getPassword())) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userid", user.getId());
                    session.setAttribute("username", user.getUsername());
                    resp.sendRedirect("/");
                } else {
                    showError(resp, "密码错误");
                }
            } else {
                showError(resp, "用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError(resp, e.toString());
        }
    }

    private void showError(HttpServletResponse resp, String msg) throws IOException {
        UTF8Writer.write(resp,
                "<!doctype html><html><head><title>Login</title></head><body>" +
                        "<h2>登录失败</h2><p>" + msg + " <a href=\"/login\">返回</a></p>" +
                        "</body></html>"
        );
    }
}

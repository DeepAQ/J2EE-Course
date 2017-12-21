package cn.imaq.order.servlet;

import cn.imaq.order.data.UserDAO;
import cn.imaq.order.model.User;

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
        resp.setContentType("text/html; charset=utf-8");
        resp.getOutputStream().print(
                "<!doctype html><html><head><title>Login</title></head><body>" +
                        "<h2>Login</h2>" +
                        "<form method=\"post\">" +
                        "Username: <input name=\"username\"><br>" +
                        "Password: <input type=\"password\" name=\"password\"><br>" +
                        "<button type=\"submit\">Login</button>" +
                        "</form>" +
                        "</body></html>"
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
                    showError(resp, "Password incorrect");
                }
            } else {
                showError(resp, "User does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError(resp, e.toString());
        }
    }

    private void showError(HttpServletResponse resp, String msg) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getOutputStream().print(
                "<!doctype html><html><head><title>Login</title></head><body>" +
                        "<h2>Login failed</h2><p>" + msg + " <a href=\"/login\">Go back</a></p>" +
                        "</body></html>"
        );
    }
}

package cn.imaq.order.servlet;

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
        resp.setContentType("text/html; charset=utf-8");
        resp.getOutputStream().print("<!doctype html><html><head><title>My Orders</title></head><body>" +
                "<h2>My Orders</h2>");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userid") == null) {
            resp.getOutputStream().print("<p>You have not logged in. <a href=\"/login\">Go to login</a></p>");
        } else {
            resp.getOutputStream().print("<p>Logged in as <strong>" + session.getAttribute("username") + "</strong> <a href=\"/logout\">Logout</a></p>");
        }
        resp.getOutputStream().print("</body></html>");
    }
}

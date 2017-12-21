package cn.imaq.order.servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getOutputStream().print(
                "<!doctype html><html><head><title>Login</title></head><body>" +
                        "<h3>Login</h3>" +
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
            Context context = new InitialContext();
            DataSource ds = ((DataSource) context.lookup("java:comp/env/jdbc/mydb"));
            Connection conn = ds.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String realPass = rs.getString("password");
                if (realPass.equals(password)) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userid", rs.getInt("id"));
                } else {
                    showError(resp, "Password incorrect");
                }
            } else {
                showError(resp, "User does not exist");
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            showError(resp, e.toString());
        }
    }

    private void showError(HttpServletResponse resp, String msg) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getOutputStream().print(
                "<!doctype html><html><head><title>Login</title></head><body>" +
                        "<p>Login failed: " + msg + " <a href=\"/login\">Go back</a></p>" +
                        "</body></html>"
        );
    }
}

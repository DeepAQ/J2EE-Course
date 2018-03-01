package cn.imaq.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String doGet(HttpSession session) {
        if (session != null) {
            session.removeAttribute("userid");
            session.removeAttribute("username");
        }
        return "redirect:/";
    }
}

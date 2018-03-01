package cn.imaq.order.controller;

import cn.imaq.order.model.ResultMessage;
import cn.imaq.order.model.User;
import cn.imaq.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String doGet() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView doPost(@RequestParam String username, @RequestParam String password, HttpSession session) {
        ResultMessage<User> loginResult = userService.login(username, password);
        if (loginResult.isSuccess()) {
            session.setAttribute("userid", loginResult.getResult().getId());
            session.setAttribute("username", loginResult.getResult().getUsername());
            return new ModelAndView("redirect:/");
        } else {
            ModelAndView modelAndView = new ModelAndView("login_error");
            modelAndView.addObject("msg", loginResult.getMessage());
            return modelAndView;
        }
    }
}

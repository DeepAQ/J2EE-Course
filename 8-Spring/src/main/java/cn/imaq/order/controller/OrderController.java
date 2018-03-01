package cn.imaq.order.controller;

import cn.imaq.order.listener.UserCountListener;
import cn.imaq.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ModelAndView doGet(HttpSession session, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int oos) {
        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("userCount", UserCountListener.getUserCounts());
        if (session.getAttribute("userid") != null) {
            int userId = (int) session.getAttribute("userid");
            boolean oosOnly = (oos > 0);
            modelAndView.addObject("currentPage", page);
            modelAndView.addObject("oosOnly", oosOnly);
            modelAndView.addObject("result", orderService.getOrderInfo(userId, page, oosOnly));
        }
        return modelAndView;
    }
}

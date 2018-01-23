package cn.imaq.order.listener;

import cn.imaq.order.model.dto.UserCountDTO;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class UserCountListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    private static AtomicInteger loginCount = new AtomicInteger(0);

    public static UserCountDTO getUserCounts() {
        return new UserCountDTO(onlineCount.get(), loginCount.get());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("sessionCreated");
        onlineCount.getAndIncrement();
        se.getSession().setMaxInactiveInterval(60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("sessionDestroyed");
        onlineCount.getAndDecrement();
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if (se.getName().equals("userid")) {
            System.out.println("userLogin");
            loginCount.getAndIncrement();
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if (se.getName().equals("userid")) {
            System.out.println("userLogout");
            loginCount.getAndDecrement();
        }
    }
}

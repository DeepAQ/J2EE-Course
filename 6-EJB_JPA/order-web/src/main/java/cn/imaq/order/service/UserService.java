package cn.imaq.order.service;

import cn.imaq.order.model.ResultMessage;
import cn.imaq.order.model.User;

public interface UserService {
    ResultMessage<User> login(String username, String password);
}

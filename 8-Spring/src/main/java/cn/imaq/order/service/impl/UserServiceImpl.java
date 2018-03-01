package cn.imaq.order.service.impl;

import cn.imaq.order.data.UserDAO;
import cn.imaq.order.model.ResultMessage;
import cn.imaq.order.model.User;
import cn.imaq.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;

    @Autowired
    private UserDAO userDAO;

    @Override
    public ResultMessage<User> login(String username, String password) {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null) {
                if (password.equals(user.getPassword())) {
                    return ResultMessage.success(user);
                } else {
                    return ResultMessage.failure("密码错误");
                }
            } else {
                return ResultMessage.failure("用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.failure(e.toString());
        }
    }
}

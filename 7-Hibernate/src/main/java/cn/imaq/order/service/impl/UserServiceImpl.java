package cn.imaq.order.service.impl;

import cn.imaq.order.factory.DAOFactory;
import cn.imaq.order.model.ResultMessage;
import cn.imaq.order.model.User;
import cn.imaq.order.service.UserService;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public ResultMessage<User> login(String username, String password) {
        try {
            User user = DAOFactory.getUserDAO().getUserByUsername(username);
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

package cn.imaq.order.data;

import cn.imaq.order.model.User;

public interface UserDAO {
    User getUserByUsername(String username) throws Exception;
}

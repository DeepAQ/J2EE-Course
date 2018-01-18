package cn.imaq.order.data;

import cn.imaq.order.model.User;

import javax.ejb.Local;

@Local
public interface UserDAO {
    User getUserByUsername(String username) throws Exception;
}

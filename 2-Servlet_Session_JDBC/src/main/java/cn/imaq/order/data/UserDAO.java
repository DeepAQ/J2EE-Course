package cn.imaq.order.data;

import cn.imaq.order.model.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User getUserByUsername(String username) throws SQLException, NamingException {
        Connection conn = MyDataSource.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM user WHERE username = ?");
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        User result = null;
        if (rs.next()) {
            result = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
            );
        }
        rs.close();
        pst.close();
        return result;
    }
}

package cn.imaq.order.data;

import cn.imaq.order.model.Order;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDAO {
    public List<Order> getOrdersByUserId(Integer userId) throws SQLException, NamingException {
        Connection conn = MyDataSource.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM orders WHERE userid = ?");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        List<Order> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new Order(
                    rs.getInt("id"),
                    rs.getInt("userid"),
                    rs.getString("time"),
                    rs.getString("name"),
                    rs.getInt("amount"),
                    rs.getDouble("price"),
                    rs.getBoolean("oos")
            ));
        }
        rs.close();
        pst.close();
        return Collections.unmodifiableList(result);
    }

    public Integer getOutOfStockCountByUserId(Integer userId) throws SQLException, NamingException {
        Connection conn = MyDataSource.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT count(1) FROM orders WHERE userid = ? AND oos = 1");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        rs.close();
        pst.close();
        return result;
    }
}

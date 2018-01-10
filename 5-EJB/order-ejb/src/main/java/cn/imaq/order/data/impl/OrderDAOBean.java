package cn.imaq.order.data.impl;

import cn.imaq.order.data.MyDataSource;
import cn.imaq.order.data.OrderDAO;
import cn.imaq.order.model.Order;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class OrderDAOBean implements OrderDAO {
    @Override
    public List<Order> getOrdersByUserId(Integer userId, Integer page, Integer pageCount, Boolean oosOnly) throws SQLException, NamingException {
        Connection conn = MyDataSource.getConnection();
        PreparedStatement pst;
        if (oosOnly) {
            pst = conn.prepareStatement("SELECT * FROM orders WHERE userid = ? AND oos = 1 ORDER BY time DESC LIMIT ?, ?");
        } else {
            pst = conn.prepareStatement("SELECT * FROM orders WHERE userid = ? ORDER BY time DESC LIMIT ?, ?");
        }
        pst.setInt(1, userId);
        pst.setInt(2, (page - 1) * pageCount);
        pst.setInt(3, pageCount);
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
        conn.close();
        return Collections.unmodifiableList(result);
    }

    @Override
    public Integer getOrderCountByUserId(Integer userId) throws SQLException, NamingException {
        Connection conn = MyDataSource.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT count(1) FROM orders WHERE userid = ?");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        rs.close();
        pst.close();
        conn.close();
        return result;
    }

    @Override
    public Integer getOutOfStockCountByUserId(Integer userId) throws SQLException, NamingException {
        Connection conn = MyDataSource.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT count(1) FROM orders WHERE userid = ? AND oos = 1");
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();
        rs.next();
        Integer result = rs.getInt(1);
        rs.close();
        pst.close();
        conn.close();
        return result;
    }
}

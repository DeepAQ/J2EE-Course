package cn.imaq.order.data;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyDataSource {
    public static Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        return ((DataSource) context.lookup("java:comp/env/jdbc/mydb")).getConnection();
    }
}

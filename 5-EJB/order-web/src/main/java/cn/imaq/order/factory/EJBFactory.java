package cn.imaq.order.factory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBFactory {
    public static <T> T getEJB(String jndiPath) {
        Properties jndiProps = new Properties();
        jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            Context context = new InitialContext(jndiProps);
            return (T) context.lookup(jndiPath);
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

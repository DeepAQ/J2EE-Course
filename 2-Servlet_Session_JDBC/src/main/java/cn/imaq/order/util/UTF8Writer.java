package cn.imaq.order.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UTF8Writer {
    public static void write(HttpServletResponse resp, String text) throws IOException {
        resp.getOutputStream().write(text.getBytes("utf-8"));
//        resp.getOutputStream().flush();
    }
}

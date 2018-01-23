package cn.imaq.order.tag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class CheckSessionHandler extends BodyTagSupport {
    @Override
    public int doStartTag() throws JspException {
        if (pageContext.getSession().getAttribute("userid") == null) {
            try {
                ((HttpServletResponse) pageContext.getResponse()).sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }
}

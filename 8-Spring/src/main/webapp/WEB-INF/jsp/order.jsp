<%@ page import="cn.imaq.order.model.Order" %>
<%@ page import="cn.imaq.order.model.ResultMessage" %>
<%@ page import="cn.imaq.order.model.dto.OrderDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="MyTag" uri="/WEB-INF/mytag.tld" %>
<MyTag:checkSession/>
<!doctype html>
<html>
<head>
    <title>订单查询系统</title>
</head>
<body>
<h2>订单查询系统</h2>
<jsp:useBean id="userCount" type="cn.imaq.order.model.dto.UserCountDTO" scope="request"/>
<p>
    在线人数：
    总人数 <% out.print(userCount.getOnlineCount()); %>，
    已登录人数 <% out.print(userCount.getLoginCount()); %>，
    游客人数 <% out.print(userCount.getOnlineCount() - userCount.getLoginCount()); %>
</p>
<%
    if (session.getAttribute("userid") == null) {
%>
<p>当前未登录，<a href="/login">点击登录</a></p>
<%
} else {
%>
<p>
    当前用户：<strong><% out.print(session.getAttribute("username")); %></strong>
    <a href="/logout">退出登录</a>
</p>
<jsp:useBean id="oosOnly" type="java.lang.Boolean" scope="request"/>
<%
    ResultMessage<OrderDTO> result = (ResultMessage<OrderDTO>) request.getAttribute("result");
    if (result.isSuccess()) {
        if (result.getResult().getOutOfStockCount() > 0) {
%>
<p>
    <strong>注意！<% out.print(result.getResult().getOutOfStockCount()); %> 个订单存在缺货！</strong>
    <%
        if (oosOnly) {
    %>
    <a href="?">显示所有订单</a>
    <%
        } else {
    %>
    <a href="?oos=1">仅显示缺货订单</a>
    <%
        }
    %>
</p>
<%
    }
%>
<table border="1">
    <thead>
    <tr>
        <th>订单时间</th>
        <th>商品名称</th>
        <th>数量</th>
        <th>总价</th>
        <th>备注</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Order item : result.getResult().getOrders()) {
            pageContext.setAttribute("order", item);
    %>
    <jsp:useBean id="order" type="cn.imaq.order.model.Order" scope="page"/>
    <tr>
        <td>
            <jsp:getProperty name="order" property="time"/>
        </td>
        <td>
            <jsp:getProperty name="order" property="name"/>
        </td>
        <td>
            <jsp:getProperty name="order" property="amount"/>
        </td>
        <td>
            <jsp:getProperty name="order" property="price"/>
        </td>
        <td>
            <%
                if (order.isOutOfStock()) {
            %>
            <span style="color:red;">缺货</span>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<p>
    翻页：
    <jsp:useBean id="currentPage" type="java.lang.Integer" scope="request"/>
    <%
        for (int i = 1; i <= result.getResult().getPageCount(); i++) {
            if (i == currentPage) {
                out.print(i + " ");
            } else {
                out.print("<a href=\"?page=" + i + (oosOnly ? "&oos=1" : "") + "\">" + i + "</a> ");
            }
        }
    %>
</p>
<%
} else {
%>
<p>查询错误：</p>
<%
        }
    }
%>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>订单查询系统</title>
</head>
<body>
<h2>登录失败</h2>
<p>
    <% out.print(request.getAttribute("msg")); %>
    <a href="/login">返回</a>
</p>
</body>
</html>
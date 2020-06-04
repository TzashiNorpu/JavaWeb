<%--
  Created by IntelliJ IDEA.
  User: FUDIAN
  Date: 2020/6/4
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //    html jpg 等资源不能通过这种方式去进行权限检查
    Object user = session.getAttribute("user");
    if (user == null) {
        request.getRequestDispatcher("/login.jsp").forward(request,response);
        return;
    }
%>
我是a.jsp
</body>
</html>

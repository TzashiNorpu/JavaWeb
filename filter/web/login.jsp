<%--
  Created by IntelliJ IDEA.
  User: FUDIAN
  Date: 2020/6/4
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
这是登录页面<br>
<form action="http://localhost:8080/filter/loginServlet" method="post">
    用户名：<input type="text" name="username"/> <br>
    密 码：<input type="password" name="password"/> <br>
    <input type="submit" />
</form>
</body>
</html>

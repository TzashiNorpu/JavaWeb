<%--
  Created by IntelliJ IDEA.
  User: FUDIAN
  Date: 2020/5/27
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%--<base href="http://localhost:8080/bookstore01/ "/>--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +"/" +
    request.getContextPath() ;
%>
<%--<%=basePath%>--%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>

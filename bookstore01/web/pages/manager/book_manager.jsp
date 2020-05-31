<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@ include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteClass").click(function () {
                return confirm("你确定要删除 " + $(this).parent().parent().find("td:first").text() + " 吗?")
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                    <%--                <td><a href="/manage/bookServlet?action=get&id=${book.id}&method=update">修改</a></td>--%>
                <td><a href="/manage/bookServlet?action=get&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteClass" href="/manage/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <%--            <td><a href="/pages/manager/book_edit.jsp?method=add">添加图书</a></td>--%>
            <td><a href="/pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>
    <div id="page_nav">
        <c:if test="${requestScope.page.pageNo>1}">
            <a href="manage/bookServlet?action=page&pageNo=1">首页</a>
            <a href="manage/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>
        <%--页码输出的开始--%>
        <c:choose>
            <%--情况 1：如果总页码小于等于 5 的情况，页码的范围是：1-总页码--%>
            <c:when test="${ requestScope.page.pageTotal <= 5 }">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            <%--情况 2：总页码大于 5 的情况--%>
            <c:when test="${requestScope.page.pageTotal > 5}">
                <c:choose>
                    <%--小情况 1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5.--%>
                    <c:when test="${requestScope.page.pageNo <= 3}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="5"/>
                    </c:when>
                    <%--小情况 2：当前页码为最后 3 个，8，9，10，页码范围是：总页码减 4 - 总页码--%>
                    <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                        <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                        <c:set var="end" value="${requestScope.page.pageTotal}"/>
                    </c:when>
                    <%--小情况 3：4，5，6，7，页码范围是：当前页码减 2 - 当前页码加 2--%>
                    <c:otherwise>
                        <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                        <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <c:if test="${i == requestScope.page.pageNo}">
                【${i}】
            </c:if>
            <c:if test="${i != requestScope.page.pageNo}">
                <a href="manage/bookServlet?action=page&pageNo=${i}">${i}</a>
            </c:if>
        </c:forEach>
        <%--        【${ requestScope.page.pageNo }】--%>
        <%--        <a href="#">3</a>--%>
        <%--        <a href="#">5</a>--%>


        <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
            <a href="manage/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
            <a href="manage/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>

        共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
        到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
        <input id="search" type="button" value="确定">
        <script type="text/javascript">
            $(function () {
                $("#search").click(function () {
                    var pageNo = $("#pn_input").val();
                    location.href = "${pageScope.basePath}manage/bookServlet?action=page&pageNo=" + pageNo;
                })
            })
        </script>
    </div>
</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
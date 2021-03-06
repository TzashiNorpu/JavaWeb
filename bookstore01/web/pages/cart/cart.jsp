<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@ include file="/pages/common/header.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteItem").click(function () {
                confirm("你确定要删除 " + $(this).parent().parent().find("td:first").text() + " 吗?")
            });
            $("#deleteCart").click(function () {
                confirm("你确定要清空购物车吗?")
            });
            // // 失去焦点事件
            // $(".updateCount").blur(function () {
            //     confirm("你确定要删除 " + $(this).parent().parent().find("td:first").text() + " 吗?")
            // })

            // 内容发生改变事件
            $(".updateCount").change(function () {
                //
                var bookName = $(this).parent().parent().find("td:first").text();
                // 属性而非方法
                var bookCount = this.value;
                var bookId = $(this).attr("bookId")
                <%--// alert("onChange事件触发!!!" + "bookName=" + bookName + ",bookCount=" + bookCount);--%>
                var isUpdate = confirm("你确定要修改 " + bookName + " 的商品数量为 "+ bookCount+ " 吗?");
                if (isUpdate){
                    location.href="${basePath}cartServlet?action=updateCount&count="+bookCount+"&id="+bookId;
                }else{
                    // defaultValue为表单项目默认的值，也即修改之前的值
                    this.value = this.defaultValue;
                }
            });
        });
    </script>
</div>

<div id="main">

    <table>

        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
        <tr>
            <td colspan="5"><a href="index.jsp">当前购物车为空</a></td>
        </tr>
        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">

        </c:if>
        <c:forEach items="${sessionScope.cart.items}" var="item">
        <tr>
            <td>${item.value.name}</td>
            <td>
<%--                    ${item.value.count}--%>
            <input class="updateCount" bookId="${item.value.id}" type="text" value="${item.value.count}" style="width: 50px">
            </td>
            <td>${item.value.price}</td>
            <td>${item.value.totalPrice}</td>
            <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
        </tr>
        </c:forEach>
<%--        <tr>--%>
<%--            <td>母猪的产后护理</td>--%>
<%--            <td>1</td>--%>
<%--            <td>10.00</td>--%>
<%--            <td>10.00</td>--%>
<%--            <td><a href="#">删除</a></td>--%>
<%--        </tr>--%>

<%--        <tr>--%>
<%--            <td>百年孤独</td>--%>
<%--            <td>1</td>--%>
<%--            <td>20.00</td>--%>
<%--            <td>20.00</td>--%>
<%--            <td><a href="#">删除</a></td>--%>
<%--        </tr>--%>
    </table>
    <c:if test="${not empty sessionScope.cart.items}">
    <div class="cart_info">
        <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
        <span class="cart_span"><a id="deleteCart" href="cartServlet?action=clearCart">清空购物车</a></span>
        <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
    </div>
    </c:if>
</div>

<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>
package com.tz.web;

import com.tz.pojo.Cart;
import com.tz.pojo.Order;
import com.tz.pojo.User;
import com.tz.service.OrderService;
import com.tz.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 先获取 Cart 购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 获取 Userid
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }
        Integer userId = loginUser.getId();
        // 调用 orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);
        // req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        request.getSession().setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }

    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void showMyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 Userid
        User loginUser = (User) request.getSession().getAttribute("user");
        List<Order> orders = orderService.showMyOrder(loginUser.getId());
        request.getSession().setAttribute("orders",orders);
        response.sendRedirect(request.getContextPath()+"/pages/order/order.jsp");
    }

    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

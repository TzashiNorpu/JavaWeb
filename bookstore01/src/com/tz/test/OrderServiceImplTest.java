package com.tz.test;

import com.tz.pojo.Book;
import com.tz.pojo.Cart;
import com.tz.pojo.CartItem;
import com.tz.pojo.Order;
import com.tz.service.OrderService;
import com.tz.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );
    }

    @Test
    public void showMyOrder() {
        int userID = 1;
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.showMyOrder(userID);
        System.out.println(orders);
    }
}
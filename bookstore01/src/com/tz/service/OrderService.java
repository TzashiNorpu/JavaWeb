package com.tz.service;

import com.tz.pojo.Cart;
import com.tz.pojo.Order;
import com.tz.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> showAllOrders();

    public boolean sendOrder(String orderId);

    public OrderItem showOrderDetail(String orderId);

    public List<Order> showMyOrder(Integer userid);

    public boolean receiveOrder(String orderId);
}

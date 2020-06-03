package com.tz.dao;

import com.tz.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryOrders();
    public boolean changeOrderStatus(String OrderId, Integer status);
    public List<Order> queryOrderByUserId(int UserId);
}

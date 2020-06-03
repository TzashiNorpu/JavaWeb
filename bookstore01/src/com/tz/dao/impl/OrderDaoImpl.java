package com.tz.dao.impl;

import com.tz.dao.OrderDao;
import com.tz.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`total_money`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        return null;
    }

    @Override
    public boolean changeOrderStatus(String OrderId, Integer status) {
        return false;
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        String sql = "select `order_id` orderId,`create_time` createTime,`total_money` price,`status`," +
                "`user_id` userId from t_order where user_id = ?";
        return queryForList(Order.class,sql,userId);
    }
}

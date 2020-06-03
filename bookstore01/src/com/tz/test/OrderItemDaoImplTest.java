package com.tz.test;

import com.tz.dao.OrderItemDao;
import com.tz.dao.impl.OrderItemDaoImpl;
import com.tz.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "java 从入门到精通", 1, new BigDecimal(100), new
                BigDecimal(100), "1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null, "javaScript 从入门到精通", 2, new
                BigDecimal(100), new BigDecimal(200), "1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Netty 入门", 1, new BigDecimal(100), new
                BigDecimal(100), "1234567891"));
    }
}
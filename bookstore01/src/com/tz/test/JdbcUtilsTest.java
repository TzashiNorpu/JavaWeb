package com.tz.test;


import com.tz.pojo.User;
import com.tz.utils.JdbcUtils;
import org.junit.Test;

public class JdbcUtilsTest {
    @Test
    public void getConnection() {
        for (int i = 0; i < 100; i++) {
            System.out.println(JdbcUtils.getConnection());
        }
    }


}
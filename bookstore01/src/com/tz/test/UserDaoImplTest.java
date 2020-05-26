package com.tz.test;

import com.tz.dao.impl.UserDaoImpl;
import com.tz.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    @Test
    public void queryUserByUsername() {
        UserDaoImpl test = new UserDaoImpl();
        User admin = test.queryUserByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
    }

    @Test
    public void saveUser() {
        UserDaoImpl test = new UserDaoImpl();
        User tz = new User( null,"Tzashi", "ttr3", "tz@qq.com");
        test.saveUser(tz);
    }
}
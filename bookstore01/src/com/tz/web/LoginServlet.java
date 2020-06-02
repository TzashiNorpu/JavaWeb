package com.tz.web;

import com.tz.pojo.User;
import com.tz.service.UserService;
import com.tz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Deprecated
public class LoginServlet extends HttpServlet {
    private UserService us = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "   " + password);
        User user = us.login(new User(null, username, password, null));
        request.setAttribute("username",username);
        if (user == null) {
            request.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

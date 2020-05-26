package com.tz.web;

import com.tz.pojo.User;
import com.tz.service.UserService;
import com.tz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
//        System.out.println(username);
        if (userService.existsUsername(username)) {
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        } else {
            userService.registUser(new User(null,username, password, email));
            request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

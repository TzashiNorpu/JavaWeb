package com.tz.web;

import com.tz.pojo.User;
import com.tz.service.UserService;
import com.tz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
@Deprecated
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String code = request.getParameter("code");
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        request.setAttribute("username",username);
        request.setAttribute("email",email);
//        System.out.println(username);
        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                request.setAttribute("msg","用户名已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                userService.registUser(new User(null,username, password, email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("msg", "验证码错误！！");
            System.out.println("验证码[" + code + "]错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

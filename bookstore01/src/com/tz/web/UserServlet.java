package com.tz.web;

import com.google.gson.Gson;
import com.tz.pojo.User;
import com.tz.service.UserService;
import com.tz.service.impl.UserServiceImpl;
import com.tz.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        System.out.println(action);
//        if (action.equals("login")) {
//            System.out.println("++++++");
//            login(request, response);
//        } else if (action.equals("regist")) {
//            System.out.println("------");
//            login(request, response);
//        }
////        本类中反射:不同模块的servlet都是这个逻辑，因此将这个反射的逻辑进行提取到BaseServlet中
//        try {
//            Method m = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//            m.invoke(this, request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private UserService us = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "   " + password);
        User user = us.login(new User(null, username, password, null));

        request.setAttribute("username", username);
        User user1 = WebUtils.copyParamsToBean(request.getParameterMap(), new User());

        if (user == null) {
            request.setAttribute("msg", "用户名或密码错误");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void ajxExistUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        // 调用 userService.xistUsername();
        boolean existUsername = us.existsUsername(username);
        // 把返回的结果封装成为 map 对象
        System.out.println(username);
        System.out.println(existUsername);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String code = request.getParameter("code");
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        request.setAttribute("username", username);
        request.setAttribute("email", email);
//        System.out.println(username);
        if (token != null && token.equalsIgnoreCase(code)) {
            if (us.existsUsername(username)) {
                request.setAttribute("msg", "用户名已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                User user = new User(null, username, password, email);
                us.registUser(user);
                request.getSession().setAttribute("user",user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "验证码错误！！");
            System.out.println("验证码[" + code + "]错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }
}

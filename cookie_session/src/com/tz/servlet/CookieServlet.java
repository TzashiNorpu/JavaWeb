package com.tz.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {
    protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("key1", "value1");
        Cookie cookie2 = new Cookie("key2", "value2");
        Cookie cookie3 = new Cookie("key3", "value3");
        // Set-Cookie
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);
        response.getWriter().write("cookie创建成功");
    }

    protected void getCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // cookie 在request headers 请求头中，浏览器自动完成
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie :
                cookies) {
            response.getWriter().write(cookie.getName() + "===" + cookie.getValue() + ";");
        }
    }

    protected void updateCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("key1", "newValue1");
        response.addCookie(cookie1);
        response.getWriter().write("cookie修改成功");
        // cookie value 不支持中文 空格 括号 ... 可以用 base64先进行编码
        Cookie[] cookies = request.getCookies();
        Cookie desCookie = null;
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals("key2")) {
                desCookie = cookie;
                break;
            }
        }
        if (desCookie != null) {
            desCookie.setValue("newValue2");
            response.addCookie(desCookie);
            response.getWriter().write("cookie修改成功");
        }
    }

    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie defualtValue = new Cookie("defaultValue", "defaultValue");
        defualtValue.setMaxAge(-1);
        response.addCookie(defualtValue);
    }

    protected void deleteNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie desCookie = null;
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals("key2")) {
                desCookie = cookie;
                break;
            }
        }
        desCookie.setMaxAge(0);
        response.addCookie(desCookie);
    }

    protected void life3600(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("life3600", "life3600");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }

    protected void testPath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 只有在浏览器访问 http://ip:port/contentPath/abc/xxx 路径时才可以在浏览器上查看到这个 cookie，也只有满足这种路径的请求浏览器才会把这个 cookie 带上
        Cookie cookie = new Cookie("path1", "path1");
        cookie.setPath(request.getContextPath() + "/abc");
        response.addCookie(cookie);
    }
}

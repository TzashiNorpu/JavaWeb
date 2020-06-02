package com.tz.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet {
    protected void createOrGetSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean aNew = session.isNew();
        response.getWriter().write("seesion id = "+ session.getId()+"<br/>");
        response.getWriter().write("seesion 是否新建："+ aNew+"<br/>");
    }


    protected void setAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("key1","value1");
        response.getWriter().write("seesion 中保存了数据");
    }


    protected void getAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object attribute = request.getSession().getAttribute("key1");
        response.getWriter().write("从Session中获取出key1的数据是：" + attribute);
    }


    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maxInactiveInterval = request.getSession().getMaxInactiveInterval();
        response.getWriter().write("Session的默认超时时长为：" + maxInactiveInterval + " 秒 ");
    }


    protected void life3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3);
    }


    protected void deleteNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
    }

}

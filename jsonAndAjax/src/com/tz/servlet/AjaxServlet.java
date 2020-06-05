package com.tz.servlet;

import com.google.gson.Gson;
import com.tz.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends BaseServlet {
    protected void jsAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person(1, "扎西笑嘻嘻");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write(s);
    }

    protected void jQueryGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person(3, "扎西笑嘻嘻");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        response.getWriter().write(s);
    }

    protected void jQueryAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person(2, "扎西笑嘻嘻");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        response.getWriter().write(s);
    }

    protected void jQueryPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person(4, "扎西笑嘻嘻");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        response.getWriter().write(s);
    }

    protected void jQueryGetJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person(5, "扎西笑嘻嘻");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        response.getWriter().write(s);
    }

    protected void jQuerySerialize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person(6, "扎西笑嘻嘻");
        System.out.println("用户名："+request.getParameter("username"));
        System.out.println("密码："+request.getParameter("password"));
        System.out.println("single："+request.getParameter("single"));
        String[] multiples = request.getParameterValues("multiple");
        for (String mul :
                multiples) {
            System.out.println("multiple："+ mul);
        }
        String[] checks = request.getParameterValues("check");
        for (String check :
                checks) {
            System.out.println("check："+ check);
        }
        System.out.println("radio："+request.getParameter("radio"));

        Gson gson = new Gson();
        String s = gson.toJson(person);
        response.getWriter().write(s);
    }



}

package com.tz.web;

import com.tz.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        try {
            chain.doFilter(req, resp);
            JdbcUtils.commitAndClose();// 提交事务
        } catch (Exception e) {
            try {
                JdbcUtils.rollbackAndClose();//回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
            // 把异常抛出给Tomcat
            throw new RuntimeException(e);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

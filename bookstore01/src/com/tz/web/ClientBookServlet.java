package com.tz.web;

import com.tz.pojo.Book;
import com.tz.pojo.Page;
import com.tz.service.BookService;
import com.tz.service.impl.BookServiceImpl;
import com.tz.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象

        Page<Book> page = bookService.page(pageNo, pageSize);
        //3 保存 Page 对象到 Request 域中
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int minPrice = WebUtils.parseInt(req.getParameter("min"), 0);
        int maxPrice = WebUtils.parseInt(req.getParameter("max"), 9999);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        StringBuilder url = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min") != null) {
            url.append("&min=").append(minPrice);
        }
        if (req.getParameter("max") != null) {
            url.append("&max=").append(maxPrice);
        }
        Page<Book> page = bookService.page(pageNo, pageSize,minPrice,maxPrice);
        //3 保存 Page 对象到 Request 域中
        page.setUrl(url.toString());
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}

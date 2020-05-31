package com.tz.web;

import com.tz.dao.BookDao;
import com.tz.dao.impl.BookDaoImpl;
import com.tz.pojo.Book;
import com.tz.pojo.Page;
import com.tz.service.BookService;
import com.tz.service.impl.BookServiceImpl;
import com.tz.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = WebUtils.copyParamsToBean(req.getParameterMap(), new Book());
        bookService.addBook(book);
        // 不能用转发，不然浏览器刷新时会重新提交增加图书的请求
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
//        添加了一项后总页数增加时返回更新后的总页数，添加一项后总页数不发生变化返回的是当前的最大页数
        resp.sendRedirect(req.getContextPath() + "/manage/bookServlet?action=page&pageNo=" + (++pageNo));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            bookService.deleteBookById(Integer.parseInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        resp.sendRedirect(req.getContextPath() + "/manage/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(Integer.parseInt(id));
        req.setAttribute("book", book);
//        这里不用加pageNo，req中此时已经有pageNo了
//        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamsToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        resp.sendRedirect(req.getContextPath() + "/manage/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //1 通过 BookService 查询全部图书
        List<Book> books = bookService.queryBooks();
//        for (int i = 0; i < books.size(); i++) {
//            System.out.println("哈哈"+books.get(i));
//        }
//        for (Book queryBook : books) {
//            System.out.println("哈哈"+queryBook);
//        }
        //2 把全部图书保存到 Request 域中
        req.setAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}

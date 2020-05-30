package com.tz.web;

import com.tz.dao.BookDao;
import com.tz.dao.impl.BookDaoImpl;
import com.tz.pojo.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    BookDao bookService = new BookDaoImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
}

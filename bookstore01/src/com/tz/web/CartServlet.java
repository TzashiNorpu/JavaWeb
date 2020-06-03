package com.tz.web;

import com.tz.dao.BookDao;
import com.tz.pojo.Book;
import com.tz.pojo.Cart;
import com.tz.pojo.CartItem;
import com.tz.service.BookService;
import com.tz.service.impl.BookServiceImpl;
import com.tz.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为 CartItem 商品项
        // Session实现购物车，不和数据库打交道，因此不用和Dao交互
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 调用 Cart.addItem(CartItem);添加商品项
        // cart只能new一次，每个请求过来都new的话会有多个购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头 Referer 的值：" + req.getHeader("Referer"));
        // 重定向回原来商品所在的地址页面
        // 在第几页添加的商品，添加完后还跳回第几页，不能统一跳回首页
        // 也即在哪个url下添加的商品，添加完后还跳回到这个url下
        resp.sendRedirect(req.getHeader("Referer"));
        req.getSession().setAttribute("lastAddBookName",cartItem.getName());
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int newCount = WebUtils.parseInt(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id,newCount);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}

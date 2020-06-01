package com.tz.dao;

import com.tz.pojo.Book;
import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();
    public Integer queryForPageTotalCount(int minPrice,int maxPrice);
    public List<Book> queryForPageItems(int begin, int pageSize);
    public List<Book> queryForPageItems(int begin, int pageSize,int minPrice,int maxPrice);
}

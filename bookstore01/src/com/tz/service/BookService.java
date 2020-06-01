package com.tz.service;

import com.tz.pojo.Book;
import com.tz.pojo.Page;

import java.util.List;

public interface BookService {
        public void addBook(Book book);
        public void deleteBookById(Integer id);
        public void updateBook(Book book);
        public Book queryBookById(Integer id);
        public List<Book> queryBooks();

        public Page<Book> page(int pageNo, int pageSize);
        public Page<Book> page(int pageNo, int pageSize,int minPrice,int maxPrice);
}

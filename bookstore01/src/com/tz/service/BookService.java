package com.tz.service;

import com.tz.pojo.Book;

import java.util.List;

public interface BookService {
        public void addBook(Book book);
        public void deleteBookById(Integer id);
        public void updateBook(Book book);
        public Book queryBookById(Integer id);
        public List<Book> queryBooks();
}

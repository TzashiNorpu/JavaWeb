package com.tz.test;

import com.tz.pojo.Book;
import com.tz.service.BookService;
import com.tz.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceImplTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"国哥在手，天下我有！", "1125", new BigDecimal(1000000),
                100000000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"社会我国哥，人狠话不多！", "1125", new BigDecimal(999999),
                10, 111110, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(23));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }
}
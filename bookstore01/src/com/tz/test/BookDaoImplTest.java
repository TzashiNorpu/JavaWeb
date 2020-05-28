package com.tz.test;

import com.tz.dao.BookDao;
import com.tz.dao.impl.BookDaoImpl;
import com.tz.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoImplTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"国哥为什么这么帅！", "191125", new
                BigDecimal(9999),1100000,0,null
        ));
    }
    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }
    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(22,"大家都可以这么帅！", "国哥", new
                BigDecimal(9999),1100000,0,null
        ));
    }
    @Test
    public void queryBookById() {
        System.out.println( bookDao.queryBookById(22) );
    }
    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
}

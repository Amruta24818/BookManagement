package com.library.service;

import com.library.dao.BookRepository;
import com.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean deleteById(int bookId) {
        bookRepository.deleteById(bookId);
        Book book = findBookById(bookId);
        if(book != null){
            return true;
        }
        return false;
    }

    @Override
    public Book findBookById(int bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public Book findBookByName(String bookName) {
        return bookRepository.findByName(bookName);
    }
}

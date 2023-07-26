package com.library.service;

import com.library.dao.BookRepository;
import com.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean deleteById(int bookId) {
        bookRepository.deleteById(bookId);
        Book book = findBookById(bookId);
        if(book != null){
            return false;
        }
        return true;
    }

    @Override
    public Book findBookById(int bookId) {
        try{
            return (Book) bookRepository.findById(bookId).get();
        }catch(Exception e) {
            return null;
        }
    }

    @Override
    public Book findBookByName(String bookName) {
        return bookRepository.findByName(bookName);
    }
}

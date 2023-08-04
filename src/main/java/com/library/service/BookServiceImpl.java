package com.library.service;

import com.library.dao.BookRepository;
import com.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        Book copy = findBookByName(book.getName());
        if(copy!=null)
            return null;
        return bookRepository.save(book);
    }

    @Override
    public boolean deleteById(int bookId) {
        bookRepository.deleteById(bookId);
        Book book = findBookById(bookId);
        return book==null;
    }

    @Override
    public Book findBookById(int bookId) {
        try{
            Optional<Book> book = bookRepository.findById(bookId);
            return book.orElse(null);
        }catch(Exception e) {
            return null;
        }
    }

    @Override
    public Book findBookByName(String bookName) {
        return bookRepository.findByName(bookName);
    }
}

package com.library.bookmanagement.unit;

import com.library.dao.BookRepository;

import com.library.model.Book;
import com.library.service.BookServiceImpl;
import com.library.service.IBookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestBookService {

    private IBookService bookService = new BookServiceImpl();


    @Test
    public void PositiveAddBook(){
        BookRepository bookRepository  = mock(BookRepository.class);
        when(bookRepository.save(any())).thenAnswer(book->{
            return book.getArguments()[0];
        });
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);
        assertEquals(book,bookService.addBook(book));
    }
}

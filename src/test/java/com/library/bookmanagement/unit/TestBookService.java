package com.library.bookmanagement.unit;


import com.library.bookmanagement.dao.BookRepository;
import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.service.BookServiceImpl;
import com.library.bookmanagement.service.IBookService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TestBookService {

    private final BookRepository bookRepository = mock(BookRepository.class);
    private final IBookService bookService = new BookServiceImpl(bookRepository);


    @Test
    void PositiveAddBook() {

        when(bookRepository.save(any())).thenAnswer(book -> book.getArguments()[0]);
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);
        assertEquals(book, bookService.addBook(book));
    }

    @Test
    void PositiveDeleteById() {
        when(bookRepository.findById(any())).thenAnswer(book -> book.getArguments()[0]);

        doNothing().when(bookRepository).deleteById(anyInt());

        boolean result = bookService.deleteById(1);
        assertTrue(result);
    }

    @Test
    void PositiveFindBookById() {
        when(bookRepository.findById(any())).thenReturn(Optional.of(new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);
        Book result = bookService.findBookById(1);
        assertEquals(book.getBookId(), result.getBookId());
        assertEquals(book.getName(), result.getName());
        assertEquals(book.getAuthor(), result.getAuthor());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals(book.getIsbn(), result.getIsbn());
    }

    @Test
    void PositiveFindBookByName() {
        when(bookRepository.findByName(any())).thenReturn(new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);
        Book result = bookService.findBookByName(book.getName());
        assertEquals(book.getBookId(), result.getBookId());
        assertEquals(book.getName(), result.getName());
        assertEquals(book.getAuthor(), result.getAuthor());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals(book.getIsbn(), result.getIsbn());
    }

    @Test
    void NegativeAddBook() {
        when(bookRepository.findByName(any())).thenReturn(null);
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);
        assert(null == bookService.addBook(book));
    }

    @Test
    void NegativeDeleteById() {
        when(bookRepository.findById(any())).thenReturn(null);
        doNothing().when(bookRepository).deleteById(anyInt());
        assertTrue(bookService.deleteById(1));
    }

    @Test
    void NegativeFindBookById() {
        when(bookRepository.findById(any())).thenReturn(null);
        assert(null == bookService.findBookById(1));
    }

    @Test
    void NegativeFindBookByName() {
        when(bookRepository.findByName(any())).thenReturn(null);
        assert(null == bookService.findBookByName("let us c"));
    }
}

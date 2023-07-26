package com.library.bookmanagement.unit;

import com.library.dao.BookRepository;

import com.library.model.Book;
import com.library.model.User;
import com.library.model.UserRole;
import com.library.service.BookServiceImpl;
import com.library.service.IBookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class TestBookService {

    private BookRepository bookRepository = mock(BookRepository.class);
    private IBookService bookService = new BookServiceImpl(bookRepository);


    @Test
    public void PositiveAddBook() {

        when(bookRepository.save(any())).thenAnswer(book -> {
            return book.getArguments()[0];
        });
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);
        assertEquals(book, bookService.addBook(book));
    }

    @Test
    public void PositiveDeleteById() {
        when(bookRepository.findById(any())).thenAnswer(book -> {
            return book.getArguments()[0];
        });

        doNothing().when(bookRepository).deleteById(anyInt());

        Boolean result = bookService.deleteById(1);
        assertTrue(result);
    }

    @Test
    public void PositiveFindBookById() {
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
    public void PositiveFindBookByName() {
        when(bookRepository.findByName(any())).thenReturn(new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);
        Book result = bookService.findBookByName(book.getName());
        assertEquals(book.getBookId(), result.getBookId());
        assertEquals(book.getName(), result.getName());
        assertEquals(book.getAuthor(), result.getAuthor());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals(book.getIsbn(), result.getIsbn());
    }
}

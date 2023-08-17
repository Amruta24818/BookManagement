package com.library.bookmanagement.service;

import com.library.bookmanagement.model.Book;

public interface IBookService {
    Book addBook(Book book);

    boolean deleteById(int bookId);

    Book findBookById(int bookId);

    Book findBookByName(String bookName);

}

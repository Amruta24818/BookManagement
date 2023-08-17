package com.library.service;

import com.library.model.Book;

public interface IBookService {
    Book addBook(Book book);

    boolean deleteById(int bookId);

    Book findBookById(int bookId);

    Book findBookByName(String bookName);

}

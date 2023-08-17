package com.library.bookmanagement.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(unique = true)
    private String name;

    @Column
    private String author;

    @Column
    private double price;

    @Column
    private long isbn;

    public Book() {
        super();
    }

    public Book(Integer bookId, String name, String author, double price, long isbn) {
        super();
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public long getIsbn() {
        return isbn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 && isbn == book.isbn && Objects.equals(bookId, book.bookId) && name.equals(book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, name, author, price, isbn);
    }
}
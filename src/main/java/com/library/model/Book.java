package com.library.model;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	
	@Column(unique = true)
	@NonNull
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

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", subject=" + author + ", price=" + price + ", isbn="
				+ isbn + "]";
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
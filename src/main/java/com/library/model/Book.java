package com.library.model;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	
	@Column(unique = true)
	@NonNull
	private String name;
	
	@Column
	private String subject;
	
	@Column
	private double price;
	
	@Column
	private long isbn;
	
	public Book() {
		super();
	}

	public Book(Integer bookId, String name, String subject, double price, long isbn) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.subject = subject;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
		return "Book [bookId=" + bookId + ", name=" + name + ", subject=" + subject + ", price=" + price + ", isbn="
				+ isbn + "]";
	}
		
	
}
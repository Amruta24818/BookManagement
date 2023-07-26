package com.library.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.mapping.ToOne;

@Entity
public class IssueRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer issueRecordId;
	
	@Column
	private LocalDate returnDate;
	
	@Column
	@CreationTimestamp
	private LocalDate issueDate;
	
	@Column
	private Integer Amount;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book bookId;

	public IssueRecord( LocalDate returnDate, LocalDate issueDate,
					   Integer amount, User userId, Book bookId) {
		super();
		this.returnDate = returnDate;
		this.issueDate = issueDate;
		Amount = amount;
		this.userId = userId;
		this.bookId = bookId;
	}

	public IssueRecord(Integer issueRecordId, LocalDate returnDate, LocalDate issueDate,
			Integer amount, User userId, Book bookId) {
		super();
		this.issueRecordId = issueRecordId;
		this.returnDate = returnDate;
		this.issueDate = issueDate;
		Amount = amount;
		this.userId = userId;
		this.bookId = bookId;
	}

	public IssueRecord() {
		super();
	}

	public Integer getIssueRecordId() {
		return issueRecordId;
	}

	public void setIssueRecordId(Integer issueRecordId) {
		this.issueRecordId = issueRecordId;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Book getBookId() {
		return bookId;
	}

	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "IssueRecord [issueRecordId=" + issueRecordId + ", returnDate="
				+ returnDate + ", issueDate=" + issueDate + ", Amount=" + Amount + ", userId=" + userId + ", bookId="
				+ bookId + "]";
	}
	
	

}

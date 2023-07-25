package com.library.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class IssueRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer issueRecordId;
	
	@Column
	@CreationTimestamp
	private LocalDate returnDate;
	
	@Column
	@CreationTimestamp
	private LocalDate issueDate;
	
	@Column
	private Integer Amount;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User userId;
	
	@OneToOne
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

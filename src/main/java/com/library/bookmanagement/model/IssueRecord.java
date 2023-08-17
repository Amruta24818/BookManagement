package com.library.bookmanagement.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Objects;

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

    public IssueRecord(Integer issueRecordId, LocalDate returnDate, LocalDate issueDate, Integer amount, User userId, Book bookId) {
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

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public User getUserId() {
        return userId;
    }

    public Book getBookId() {
        return bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueRecord that = (IssueRecord) o;
        return Objects.equals(issueRecordId, that.issueRecordId) && Objects.equals(returnDate, that.returnDate) && Objects.equals(issueDate, that.issueDate) && Objects.equals(Amount, that.Amount) && Objects.equals(userId, that.userId) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueRecordId, returnDate, issueDate, Amount, userId, bookId);
    }
}

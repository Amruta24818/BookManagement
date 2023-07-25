package com.library.dto;

public class AssignBookDto {

    private int userId;
    private String bookName;

    public AssignBookDto(int userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
    }

    public int getUserId() {
        return userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}

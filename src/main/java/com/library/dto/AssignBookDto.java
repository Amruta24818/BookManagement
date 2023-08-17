package com.library.dto;

import com.library.model.User;

public class AssignBookDto {

    private User user;
    private String bookName;

    public AssignBookDto(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

}

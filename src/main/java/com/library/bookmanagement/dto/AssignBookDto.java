package com.library.bookmanagement.dto;

import com.library.bookmanagement.model.User;

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


    public String getBookName() {
        return bookName;
    }


}

package com.library.bookmanagement.dto;

public class LoginDto {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

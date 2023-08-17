package com.library.bookmanagement.service;

import com.library.bookmanagement.dto.LoginDto;
import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;

import java.util.List;

public interface IUserService {
    User registerUser(User user);

    User editProfile(User user);

    User findByEmail(String email);

    List<User> getAllUsers();

    User login(LoginDto loginDto);

    User editPassword(User user);

    User findByUserId(int userId);

    List<IssueRecord> getFineReport(int userId);

}

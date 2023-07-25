package com.library.service;

import com.library.dto.LoginDto;
import com.library.model.User;

import java.util.List;

public interface IUserService {
    User registerUser(User user);

    User editProfile(User user);

    User findByEmail(String email);

    List<User> getAllUsers();

    User login(LoginDto loginDto);

    User editPassword(User user);

    User findByUserId(int userId);

}

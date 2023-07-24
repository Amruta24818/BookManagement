package com.library.controller;

import com.library.model.User;
import com.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
		user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        System.out.println(user);
        // Sending a generic response which consists of status and data
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
}

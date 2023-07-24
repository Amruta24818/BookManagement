package com.library.controller;

import com.library.dto.LoginDto;
import com.library.model.User;
import com.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/user")
public class CommonController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // todo: uncomment this line to encode the password
        //user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        // Sending a generic response which consists of status and data
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> signinUser(@RequestBody LoginDto loginDto) {
        User user = userService.login(loginDto);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<User> editProfile(@RequestBody User user){
        return  new ResponseEntity<>(userService.editProfile(user), HttpStatus.ACCEPTED);
    }
}

package com.library.bookmanagement.controller;

import com.library.bookmanagement.dto.LoginDto;
import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;
import com.library.bookmanagement.model.UserRole;
import com.library.bookmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CommonController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        user.setRole(UserRole.USER);
        // Sending a generic response which consists of status and data
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> signinUser(@RequestBody LoginDto loginDto) {
        User user = userService.login(loginDto);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<User> editProfile(@RequestBody User user){
        return  new ResponseEntity<>(userService.editProfile(user), HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit-password")
    public ResponseEntity<User> editPassword(@RequestBody User user){
        return  new ResponseEntity<>(userService.editPassword(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-fine/{userId}")
    public ResponseEntity<List<IssueRecord>> getAllFineRecords(@PathVariable int userId){
        return new ResponseEntity<>(userService.getFineReport(userId), HttpStatus.CREATED);
    }
}

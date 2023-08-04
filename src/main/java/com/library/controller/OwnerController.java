package com.library.controller;

import com.library.model.IssueRecord;
import com.library.model.User;
import com.library.model.UserRole;
import com.library.service.IIssueRecordService;
import com.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private IIssueRecordService issueRecordService;

    @Autowired
    private IUserService userService;

    @GetMapping("/get-all-issuerecord")
    public ResponseEntity<List<IssueRecord>> getAllIssueRecord(){
        return new ResponseEntity<>(issueRecordService.getAllIssueRecord(), HttpStatus.CREATED);
    }

    @PostMapping("/appoint-librarian")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        user.setRole(UserRole.LIBRARIAN);
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/get-fine-report")
    public ResponseEntity<List<IssueRecord>> getAllFineRecords(){
        return new ResponseEntity<>(issueRecordService.getAllFineRecord(), HttpStatus.CREATED);
    }
}

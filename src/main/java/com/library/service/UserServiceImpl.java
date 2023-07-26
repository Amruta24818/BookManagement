package com.library.service;

import com.library.dao.IssueRecordRepository;
import com.library.dao.UserRepository;
import com.library.dto.LoginDto;
import com.library.model.IssueRecord;
import com.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    public UserServiceImpl(UserRepository userRepository, IssueRecordRepository issueRecordRepository) {
        this.userRepository = userRepository;
        this.issueRecordRepository = issueRecordRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editProfile(User user) {
        User userFromDb = findByEmail(user.getEmail());
        userFromDb.setMobNo(user.getMobNo());
        return userRepository.save(userFromDb);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User editPassword(User user) {
        User userFromDb = findByEmail(user.getEmail());
        userFromDb.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userRepository.save(userFromDb);
        return userFromDb;
    }

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user != null && (new String(Base64.getDecoder().decode(user.getPassword().getBytes())).equals(loginDto.getPassword()))) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUserId(int userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<IssueRecord> getFineReport(int userId) {
        List<IssueRecord> list = issueRecordRepository.findAll();
        List<IssueRecord> record = list.stream()
                .filter(records -> records.getAmount() > 0 && records.getUserId().getUserId()
                        .equals(userId))
                .collect(Collectors.toList());
        return record;
    }
}

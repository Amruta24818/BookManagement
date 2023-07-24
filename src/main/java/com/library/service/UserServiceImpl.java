package com.library.service;

import com.library.dao.UserRepository;
import com.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editProfile(User user) {
        User userFromDb =  findByEmail(user.getEmail());
        userFromDb.setMobNo(user.getMobNo());
        userRepository.save(userFromDb);
        return null;
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

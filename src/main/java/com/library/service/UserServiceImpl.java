package com.library.service;

import com.library.dao.UserRepository;
import com.library.dto.LoginDto;
import com.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editProfile(User user) {
        User userFromDb = findByEmail(user.getEmail());
        userFromDb.setMobNo(user.getMobNo());
        userRepository.save(userFromDb);
        return null;
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
}

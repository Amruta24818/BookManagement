package com.library.bookmanagement.unit;

import com.library.dao.IssueRecordRepository;
import com.library.dao.UserRepository;
import com.library.dto.LoginDto;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.User;
import com.library.model.UserRole;
import com.library.service.IUserService;
import com.library.service.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUserService {

    private UserRepository userRepository = mock(UserRepository.class);
    private IssueRecordRepository issueRecordRepository = mock(IssueRecordRepository.class);

    private IUserService userService = new UserServiceImpl(userRepository,issueRecordRepository);

    @Test
    public void PositiveRegisterUser(){

        when(userRepository.save(any())).thenAnswer(user -> {
            return user.getArguments()[0];
        });
        User user = new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER);
        assertEquals(user, userService.registerUser(user));
    }

    @Test
    public void PositiveEditProfile(){
        when(userRepository.findByEmail(any())).thenReturn(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER));
        when(userRepository.save(any())).thenAnswer(user -> {
            return user.getArguments()[0];
        });
        User user = new User(1, "Shubham", "shubham@gmail.com", "shubham", "9690096419", UserRole.USER);
        User newUser = userService.editProfile(user);
        assertEquals(user.getUserId(), newUser.getUserId());
        assertEquals(user.getPassword(), newUser.getPassword());
        assertEquals(user.getEmail(), newUser.getEmail());
        assertEquals(user.getMobNo(),newUser.getMobNo());
        assertEquals(user.getName(),newUser.getName());
        assertEquals(user.getRole(),newUser.getRole());

    }

    @Test
    public void PositiveFindByEmail(){
        when(userRepository.findByEmail(any())).thenReturn(new User(1, "Shubham", "shubham@gmail.com", "shubham", "9690096419", UserRole.USER));
        User user = new User(1, "Shubham", "shubham@gmail.com", "shubham", "9690096419", UserRole.USER);
        User result = userService.findByEmail(user.getEmail());
        assertEquals(user.getUserId(), result.getUserId());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getMobNo(),result.getMobNo());
        assertEquals(user.getName(),result.getName());
        assertEquals(user.getRole(),result.getRole());
    }

    @Test
    public void PositiveGetAllUsers(){
        when(userRepository.findAll()).
                thenReturn(List.of(new User(1, "Shubham", "shubham@gmail.com", "shubham", "9690096419", UserRole.USER),
                        new User(2, "Amruta", "amruta@gmail.com", "amruta", "9527378754", UserRole.USER)));
        List<User> list = List.of(new User(1, "Shubham", "shubham@gmail.com", "shubham", "9690096419", UserRole.USER),
                new User(2, "Amruta", "amruta@gmail.com", "amruta", "9527378754", UserRole.USER));

        List<User> users = userService.getAllUsers();
        assert(list.equals(users));
    }

    @Test
    public void PositiveEditPassword(){
        when(userRepository.findByEmail(any())).thenReturn(new User(1, "Shubham", "shubham@gmail.com", "shubham", "9690096419", UserRole.USER));
        when(userRepository.save(any())).thenAnswer(user -> {
            return user.getArguments()[0];
        });
        User user = new User(1, "Shubham", "shubham@gmail.com", "shubham", "9690096419", UserRole.USER);
        User newUser = userService.editPassword(user);
        assertEquals(user.getUserId(), newUser.getUserId());
        assertEquals(user.getPassword(), new String(Base64.getDecoder().decode(newUser.getPassword())));
        assertEquals(user.getEmail(), newUser.getEmail());
        assertEquals(user.getMobNo(),newUser.getMobNo());
        assertEquals(user.getName(),newUser.getName());
        assertEquals(user.getRole(),newUser.getRole());
    }

    @Test
    public void PositiveLogin(){
        when(userRepository.findByEmail(any())).thenReturn(new User(1, "Shubham", "shubham@gmail.com",  new String(Base64.getEncoder().encodeToString("shubham".getBytes())), "9690096419", UserRole.USER));
        LoginDto loginDto = new LoginDto("shubham@gmail.com","shubham");
        User user = userService.login(loginDto);
        assertEquals(loginDto.getEmail(), user.getEmail());
        assertEquals(loginDto.getPassword(), new String(Base64.getDecoder().decode(user.getPassword().getBytes())));
    }

    @Test
    public void PositiveFindByUserId(){
        when(userRepository.findById(any())).thenReturn(Optional.of(new User(1, "Shubham", "shubham@gmail.com",  new String(Base64.getEncoder().encodeToString("shubham".getBytes())), "9690096419", UserRole.USER)));
        User user = new User(1, "Shubham", "shubham@gmail.com",  "shubham", "9690096419", UserRole.USER);
        User result = userRepository.findById(1).get();
        assertEquals(user.getUserId(), result.getUserId());
        assertEquals(user.getPassword(),new String(Base64.getDecoder().decode(result.getPassword().getBytes())));
        assertEquals(user.getRole(), result.getRole());
        assertEquals(user.getMobNo(), result.getMobNo());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void PositiveGetFineReport(){
        when(issueRecordRepository.findAll()).
                thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10,
                        new User(1, "Shubham", "shubham@gmail.com",
                                new String(Base64.getEncoder().encodeToString("shubham".getBytes())),
                                "9690096419", UserRole.USER),
                        new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)),
                        new IssueRecord(2, LocalDate.now(), LocalDate.now(), 10,
                                new User(2, "Amruta", "amruta@gmail.com",
                                        new String(Base64.getEncoder().encodeToString("amruta".getBytes())),
                                        "9527378754", UserRole.USER),
                                new Book(2, "Head first c", "David", 562, 1258789))
                        ));

        List<IssueRecord> list = List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10,
                        new User(1, "Shubham", "shubham@gmail.com",
                                new String(Base64.getEncoder().encodeToString("shubham".getBytes())),
                                "9690096419", UserRole.USER),
                        new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));

        List<IssueRecord> record = userService.getFineReport(1);
        assert(list.equals(record));

    }
}

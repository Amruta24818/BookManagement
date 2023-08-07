package com.library.bookmanagement.E2E;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.bookmanagement.controller.CommonController;
import com.library.bookmanagement.dto.LoginDto;
import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;
import com.library.bookmanagement.model.UserRole;
import com.library.bookmanagement.service.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ComponentScan(basePackages ="com.library.bookmanagement")
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {CommonController.class})
@ExtendWith(SpringExtension.class)
public class TestCommonController {

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    final ObjectMapper mapper = new ObjectMapper();

    @Test
    void PositiveRegisterUser() {
        User users = new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER);
        when(userService.registerUser(any())).thenAnswer(user -> user.getArguments()[0]);

        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                            .contentType("application/json;charset=TUF-8").accept("*/*")
                            .content(mapper.writeValueAsString(users)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();

            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
            assertEquals(users.getUserId(), json.getInt("userId"));
            assertEquals(users.getName(), json.getString("name"));
            assertEquals(users.getEmail(), json.getString("email"));
            assertEquals(users.getMobNo(), json.getString("mobNo"));
            assertEquals(users.getPassword(),new String(Base64.getDecoder().decode(json.getString("password").getBytes())));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PositiveSigninUser(){
        when(userService.login(any())).thenAnswer(user -> user.getArguments()[0]);
        User users = new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER);
        LoginDto loginDto = new LoginDto("shubham@gmail.com", "shubham");
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/sign-in")
                            .contentType("application/json;charset=TUF-8").accept("*/*")
                            .content(mapper.writeValueAsString(loginDto)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
            assertEquals(users.getUserId(), json.getInt("userId"));
            assertEquals(users.getName(), json.getString("name"));
            assertEquals(users.getEmail(), json.getString("email"));
            assertEquals(users.getMobNo(), json.getString("mobNo"));
            assertEquals(users.getPassword(),new String(Base64.getDecoder().decode(json.getString("password").getBytes())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PositiveEditProfile(){
        when(userService.editProfile(any())).thenAnswer(user -> user.getArguments()[0]);
        User user = new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER);
        try{
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/user/edit-profile")
                            .contentType("application/json;charset=TUF-8").accept("*/*")
                            .content(mapper.writeValueAsString(user)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
            assertEquals(user.getUserId(), json.getInt("userId"));
            assertEquals(user.getName(), json.getString("name"));
            assertEquals(user.getEmail(), json.getString("email"));
            assertEquals(user.getMobNo(), json.getString("mobNo"));
            assertEquals(user.getPassword(),new String(Base64.getDecoder().decode(json.getString("password").getBytes())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PositiveEditPassword(){
        when(userService.editPassword(any())).thenAnswer(user -> user.getArguments()[0]);
        User user = new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER);

        try{
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/user/edit-password")
                            .contentType("application/json;charset=TUF-8").accept("*/*")
                            .content(mapper.writeValueAsString(user)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
            assertEquals(user.getUserId(), json.getInt("userId"));
            assertEquals(user.getName(), json.getString("name"));
            assertEquals(user.getEmail(), json.getString("email"));
            assertEquals(user.getMobNo(), json.getString("mobNo"));
            assertEquals(user.getPassword(),new String(Base64.getDecoder().decode(json.getString("password").getBytes())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PositiveGetAllFineRecords(){
        when(userService.getFineReport(anyInt())).thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(null, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/owner/get-all-fine/{userId}")
                            .contentType("application/json;charset=UTF-8").accept("*/*")
                            .content(mapper.writeValueAsString(list.get(0).getUserId().getUserId())))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
            JSONArray jsonResult = new JSONArray(result.getResponse().getContentAsString());
            List<IssueRecord> listData = new ArrayList<>();

            JsonNode jsonArray = mapper.readTree(String.valueOf(jsonResult));

            for (JsonNode element : jsonArray) {
                IssueRecord object = mapper.treeToValue(element, IssueRecord.class);
                listData.add(object);
            }

            assert (list.equals(listData));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

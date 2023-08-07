package com.library.bookmanagement.E2E;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.bookmanagement.controller.OwnerController;
import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;
import com.library.bookmanagement.model.UserRole;
import com.library.bookmanagement.service.IssueRecordServiceImpl;
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
import static org.mockito.Mockito.when;
@ComponentScan(basePackages ="com.library.bookmanagement")
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {OwnerController.class})
@ExtendWith(SpringExtension.class)
class TestOwnerController {

//    @InjectMocks
    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private IssueRecordServiceImpl issueRecordService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    final ObjectMapper mapper = new ObjectMapper();

    @Test
    void PositiveRegisterUser() {
        User users = new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER);
        when(userService.registerUser(any())).thenAnswer(user -> user.getArguments()[0]);

        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/owner/appoint-librarian")
                    .contentType("application/json;charset=UTF-8").accept("*/*")
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
    void PositiveGetAllIssueRecord(){
        when(issueRecordService.getAllIssueRecord()).thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(null, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/owner/get-all-issuerecord")
                            .contentType("application/json;charset=UTF-8").accept("*/*"))
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

    @Test
    void PositiveGetAllFineRecords(){
        when(issueRecordService.getAllFineRecord()).thenReturn(List.of(new IssueRecord(null, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(null, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        try{
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/owner/get-fine-report")
                            .contentType("application/json;charset=UTF-8").accept("*/*"))
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

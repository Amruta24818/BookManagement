package com.library.bookmanagement.E2E;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.bookmanagement.controller.LibrarianController;
import com.library.bookmanagement.dto.AssignBookDto;
import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;
import com.library.bookmanagement.model.UserRole;
import com.library.bookmanagement.service.IBookService;
import com.library.bookmanagement.service.IssueRecordServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ComponentScan(basePackages ="com.library.bookmanagement")
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {LibrarianController.class})
@ExtendWith(SpringExtension.class)
public class TestLibrarianController {

    @MockBean
    private IBookService bookService;

    @MockBean
    private IssueRecordServiceImpl issueRecordService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    final ObjectMapper mapper = new ObjectMapper();

    @Test
    void PositveAddBook(){
        when(bookService.addBook(any())).thenAnswer(book -> book.getArguments()[0]);
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);

        try{
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/book/add-book")
                            .contentType("application/json;charset=UTF-8").accept("*/*")
                            .content(mapper.writeValueAsString(book)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();

            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
            assertEquals(book.getBookId(),json.getInt("bookId"));
            assertEquals(book.getName(),json.getString("name"));
            assertEquals(book.getAuthor(),json.getString("author"));
            assertEquals(book.getPrice(),json.getInt("price"));
            assertEquals(book.getIsbn(),json.getInt("isbn"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PositiveDeleteBookById(){
        when(bookService.deleteById(anyInt())).thenReturn(true);
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/book/delete-book/{bookId}")
                            .contentType("application/json;charset=UTF-8").accept("*/*")
                            .content(mapper.writeValueAsString(1)))
                    .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andReturn();
            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
//            assertTrue(json.getBoolean("true"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PositiveFindBook(){
        when(bookService.findBookByName(any())).thenAnswer(book -> book.getArguments()[0]);
        Book book = new Book(1, "let us c", "Yashvant kanetkar", 256, 125896);
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/book/find-book/{bookName}")
                    .contentType("application/json;charset=UTF-8").accept("*/*")
                    .content(mapper.writeValueAsString(book.getName())))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
            assertEquals(book.getBookId(),json.getInt("bookId"));
            assertEquals(book.getName(),json.getString("name"));
            assertEquals(book.getAuthor(),json.getString("author"));
            assertEquals(book.getPrice(),json.getInt("price"));
            assertEquals(book.getIsbn(),json.getInt("isbn"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PositiveAssignBook(){
        when(issueRecordService.assignBook(any())).thenAnswer(issueRecord -> issueRecord.getArguments()[0]);
        AssignBookDto bookDto = new AssignBookDto(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), "let us c");
        IssueRecord issueRecord = new IssueRecord(1,null, LocalDate.now(), 0, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        try{
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/book/assign-book")
                            .contentType("application/json;charset=UTF-8").accept("*/*")
                            .content(mapper.writeValueAsString(bookDto)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
            assertEquals(issueRecord,json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void PositiveReturnBook(){
        when(issueRecordService.returnBook(any())).thenAnswer(issueRecord -> issueRecord.getArguments()[0]);
        AssignBookDto bookDto = new AssignBookDto(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), "let us c");
        IssueRecord issueRecord = new IssueRecord(1,null, LocalDate.now(), 0, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        try{
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/book/return-book")
                            .contentType("application/json;charset=UTF-8").accept("*/*")
                            .content(mapper.writeValueAsString(bookDto)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();
            JSONObject json = new JSONObject(result.getResponse().getContentAsString());
            assertEquals(issueRecord,json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

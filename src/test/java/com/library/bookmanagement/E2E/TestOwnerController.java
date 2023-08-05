package com.library.bookmanagement.E2E;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.bookmanagement.BookManagementApplication;
import com.library.controller.OwnerController;
import com.library.model.User;
import com.library.model.UserRole;
import com.library.service.IUserService;
import com.library.service.UserServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Base64;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class TestOwnerController {

    @MockBean
    private IUserService userService = mock(UserServiceImpl.class);

    @MockBean
    private OwnerController ownerController = new OwnerController(userService);

    @Autowired
    private MockMvc mockMvc;

    final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(TestOwnerController.class).build();
    }

    @Test
    void PositiveRegisterUser() {
        User users = new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123",UserRole.USER);
        when(userService.registerUser(any())).thenAnswer(user -> user.getArguments()[0]);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/owner/appoint-librarian")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(users)))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$User").value(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123",UserRole.LIBRARIAN)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$name").value("Shubham"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$email").value("shubham@gamil.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$password").value(Base64.getEncoder().encodeToString("shubham".getBytes())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$mobNo").value("789654123"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$role").value(UserRole.USER));
    }


}

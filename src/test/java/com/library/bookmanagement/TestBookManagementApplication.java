package com.library.bookmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestBookManagementApplication {

    @Test
    void mainApp() {
        String[] args = new String[]{"sdsd", "asd"};
        BookManagementApplication.main(args);
        assert (true);
    }

}
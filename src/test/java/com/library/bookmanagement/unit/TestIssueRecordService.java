package com.library.bookmanagement.unit;

import com.library.dao.BookRepository;
import com.library.dao.IssueRecordRepository;
import com.library.dao.UserRepository;
import com.library.dto.AssignBookDto;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.User;
import com.library.model.UserRole;
import com.library.service.IIssueRecordService;
import com.library.service.IssueRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestIssueRecordService {

    private BookRepository bookRepository = mock(BookRepository.class);
    private UserRepository userRepository = mock(UserRepository.class);
    private IssueRecordRepository issueRecordRepository = mock(IssueRecordRepository.class);

    private IIssueRecordService issueRecordService = new IssueRecordServiceImpl(bookRepository, userRepository,issueRecordRepository);

    @Test
    public void PositiveAssignBook(){
        when(userRepository.findById(any())).thenReturn(Optional.of( new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER)));
        when(bookRepository.findByName(any())).thenReturn(new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        when(issueRecordRepository.save(any())).thenAnswer(issueRecord -> {
            return issueRecord.getArguments()[0];
        });

        IssueRecord issueRecord = new IssueRecord(null, LocalDate.now(), 0,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        AssignBookDto bookDto = new AssignBookDto(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), issueRecord.getBookId().getName());
        assertEquals(issueRecord, issueRecordService.assignBook(bookDto));
    }

    @Test
    public void PositiveReturnBook(){
        when(issueRecordRepository.findAllIssueRecordByUserId(any())).thenReturn(List.of(new IssueRecord(1, null, LocalDate.now(), 0,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        IssueRecord issueRecord = new IssueRecord(1,LocalDate.now(), LocalDate.now(), 0,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        when(issueRecordRepository.save(any())).thenAnswer(issueRecords -> {
            return issueRecords.getArguments()[0];
        });
        AssignBookDto bookDto = new AssignBookDto(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), issueRecord.getBookId().getName());
        assertEquals(issueRecord, issueRecordService.returnBook(bookDto));
    }

    @Test
    public void PositiveFindUserInIssueRecords(){
        when(issueRecordRepository.findAllIssueRecordByUserId(any())).thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        List<IssueRecord> records = issueRecordService.findUserInIssueRecords(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER));
        assert(list.equals(records));
    }

    @Test
    public void PositiveGetAllIssueRecord(){
        when(issueRecordRepository.findAll()).thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        List record = issueRecordService.getAllIssueRecord();
        assert(list.equals(record));
    }

    @Test
    public void PositiveGetAllFineRecord(){
        when(issueRecordRepository.findAll()).thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10,  new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        List<IssueRecord> records = issueRecordService.getAllFineRecord();
        assert(list.equals(records));
    }
}

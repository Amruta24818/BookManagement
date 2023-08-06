package com.library.bookmanagement.unit;


import com.library.bookmanagement.dao.BookRepository;
import com.library.bookmanagement.dao.IssueRecordRepository;
import com.library.bookmanagement.dao.UserRepository;
import com.library.bookmanagement.dto.AssignBookDto;
import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;
import com.library.bookmanagement.model.UserRole;
import com.library.bookmanagement.service.IIssueRecordService;
import com.library.bookmanagement.service.IssueRecordServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestIssueRecordService {

    private final BookRepository bookRepository = mock(BookRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final IssueRecordRepository issueRecordRepository = mock(IssueRecordRepository.class);

    private final IIssueRecordService issueRecordService = new IssueRecordServiceImpl(bookRepository, userRepository, issueRecordRepository);

    @Test
    void PositiveAssignBook() {
        when(userRepository.findById(any())).thenReturn(Optional.of(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER)));
        when(bookRepository.findByName(any())).thenReturn(new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        when(issueRecordRepository.save(any())).thenAnswer(issueRecord -> issueRecord.getArguments()[0]);

        IssueRecord issueRecord = new IssueRecord(1,null, LocalDate.now(), 0, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        AssignBookDto bookDto = new AssignBookDto(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), issueRecord.getBookId().getName());
        assertEquals(issueRecord, issueRecordService.assignBook(bookDto));
    }

    @Test
    void PositiveReturnBook() {
        when(issueRecordRepository.findAllIssueRecordByUserId(any())).thenReturn(List.of(new IssueRecord(1, null, LocalDate.now(), 0, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        IssueRecord issueRecord = new IssueRecord(1, LocalDate.now(), LocalDate.now(), 0, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896));
        when(issueRecordRepository.save(any())).thenAnswer(issueRecords -> issueRecords.getArguments()[0]);
        AssignBookDto bookDto = new AssignBookDto(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), issueRecord.getBookId().getName());
        assertEquals(issueRecord, issueRecordService.returnBook(bookDto));
    }

    @Test
    void PositiveFindUserInIssueRecords() {
        when(issueRecordRepository.findAllIssueRecordByUserId(any())).thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        List<IssueRecord> records = issueRecordService.findUserInIssueRecords(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER));
        assert (list.equals(records));
    }

    @Test
    void PositiveGetAllIssueRecord() {
        when(issueRecordRepository.findAll()).thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        List<IssueRecord> record = issueRecordService.getAllIssueRecord();
        assert (list.equals(record));
    }

    @Test
    void PositiveGetAllFineRecord() {
        when(issueRecordRepository.findAll()).thenReturn(List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896))));
        List<IssueRecord> list = List.of(new IssueRecord(1, LocalDate.now(), LocalDate.now(), 10, new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), new Book(1, "let us c", "Yashvant kanetkar", 256, 125896)));
        List<IssueRecord> records = issueRecordService.getAllFineRecord();
        assert (list.equals(records));
    }

    @Test
    void NegativeAssignBook() {
        when(userRepository.findById(any())).thenReturn(null);
        when(bookRepository.findByName(any())).thenReturn(null);

        AssignBookDto bookDto = new AssignBookDto(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), "let us c");
        assert(null == issueRecordService.assignBook(bookDto));
    }

    @Test
    void NegativeReturnBook() {
        when(issueRecordRepository.findAllIssueRecordByUserId(any())).thenReturn(null);
        AssignBookDto bookDto = new AssignBookDto(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER), "let us c");
        assert(null == issueRecordService.returnBook(bookDto));
    }

    @Test
    void NegativeFindUserInIssueRecords() {
        when(issueRecordRepository.findAllIssueRecordByUserId(any())).thenReturn(null);
        List<IssueRecord> records = issueRecordService.findUserInIssueRecords(new User(1, "Shubham", "shubham@gmail.com", "shubham", "789654123", UserRole.USER));
        assert (null == records);
    }

    @Test
    void NegativeGetAllIssueRecord() {
        when(issueRecordRepository.findAll()).thenReturn(null);
        List<IssueRecord> record = issueRecordService.getAllIssueRecord();
        assert (null == record);
    }

    @Test
    void NegativeGetAllFineRecord() {
        when(issueRecordRepository.findAll()).thenReturn(null);
        List<IssueRecord> records = issueRecordService.getAllFineRecord();
        assert (null == records);
    }

}

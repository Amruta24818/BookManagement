package com.library.service;

import com.library.dao.BookRepository;
import com.library.dao.IssueRecordRepository;
import com.library.dao.UserRepository;
import com.library.dto.AssignBookDto;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class IssueRecordServiceImpl implements IIssueRecordService {

    private static final int FINE = 5;
    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public IssueRecord assignBook(AssignBookDto assignBookDto) {
        User user = userRepository.findById(assignBookDto.getUserId()).get();
        Book book = bookRepository.findByName(assignBookDto.getBookName());
        return issueRecordRepository.save(new IssueRecord(null, LocalDate.now(), 0, user, book));
    }

    @Override
    public IssueRecord returnBook(AssignBookDto assignBookDto) {
        List<IssueRecord> list = findByUserId(assignBookDto.getUserId());
        IssueRecord record = list.stream()
                .filter(records -> records.getBookId().getName()
                        .equals(assignBookDto.getBookName()))
                .findAny().orElse(null);
        if(record != null){
            record.setReturnDate(LocalDate.now());
            int Days = (int) ChronoUnit.DAYS.between(record.getReturnDate(), record.getIssueDate());
            record.setAmount(FINE*Days);
            return issueRecordRepository.save(record);
        }
        return null;
    }

    @Override
    public List<IssueRecord> findByUserId(int userId) {
        return issueRecordRepository.findIssueRecordByUserId(userId);
    }


}

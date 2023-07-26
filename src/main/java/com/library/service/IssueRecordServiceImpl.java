package com.library.service;

import com.library.dao.BookRepository;
import com.library.dao.IssueRecordRepository;
import com.library.dao.UserRepository;
import com.library.dto.AssignBookDto;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        User user = userRepository.findById(assignBookDto.getUser().getUserId()).get();
        Book book = bookRepository.findByName(assignBookDto.getBookName());
        return issueRecordRepository.save(new IssueRecord(null, LocalDate.now(), 0, user, book));
    }

    @Override
    public IssueRecord returnBook(AssignBookDto assignBookDto) {
        List<IssueRecord> list = findUserInIssueRecords(assignBookDto.getUser());
        System.out.println(list.toArray().toString());
        IssueRecord record = list.stream()
                .filter(records -> records.getBookId().getName()
                        .equals(assignBookDto.getBookName()) && records.getReturnDate() == null)
                .findAny() .orElse(null);
        if(record != null){
            record.setReturnDate(LocalDate.now());
            int days = (int) ChronoUnit.DAYS.between( record.getIssueDate(), record.getReturnDate());
            if(days>7) {
                record.setAmount(FINE * (days-7));
            }
            return issueRecordRepository.save(record);
        }
        return null;
    }

    @Override
    public List<IssueRecord> findUserInIssueRecords(User user) {
        return issueRecordRepository.findAllIssueRecordByUserId(user);
    }

    @Override
    public List<IssueRecord> getAllIssueRecord() {
        return issueRecordRepository.findAll();
    }

    @Override
    public List<IssueRecord> getAllFineRecord() {
        List<IssueRecord> list = issueRecordRepository.findAll();
        List<IssueRecord> record = list.stream()
                .filter(records -> records.getAmount() > 0)
                .collect(Collectors.toList());
        return record;
    }


}

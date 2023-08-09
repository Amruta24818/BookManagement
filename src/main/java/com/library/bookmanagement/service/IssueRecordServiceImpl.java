package com.library.bookmanagement.service;

import com.library.bookmanagement.dao.BookRepository;
import com.library.bookmanagement.dao.IssueRecordRepository;
import com.library.bookmanagement.dao.UserRepository;
import com.library.bookmanagement.dto.AssignBookDto;
import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IssueRecordServiceImpl implements IIssueRecordService {

    private static final int FINE = 5;

    @Autowired
    private final IssueRecordRepository issueRecordRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    public IssueRecordServiceImpl(BookRepository bookRepository, UserRepository userRepository, IssueRecordRepository issueRecordRepository) {
        this.bookRepository = bookRepository;
        this.userRepository= userRepository;
        this.issueRecordRepository = issueRecordRepository;
    }

    @Override
    public IssueRecord assignBook(AssignBookDto assignBookDto) {
        try {
            Optional<User> temp = userRepository.findById(assignBookDto.getUser().getUserId());
            User user = temp.orElse(null);
            Book book = bookRepository.findByName(assignBookDto.getBookName());

            if (user != null && book != null)
                return issueRecordRepository.save(new IssueRecord(null,null, LocalDate.now(), 0, user, book));
            return null;
        }catch(Exception e) {
            return null;
        }
    }

    @Override
    public IssueRecord returnBook(AssignBookDto assignBookDto) {
        List<IssueRecord> list = findUserInIssueRecords(assignBookDto.getUser());
        if(list!=null) {
            IssueRecord issueRecord = list.stream()
                    .filter(records -> records.getBookId().getName()
                            .equals(assignBookDto.getBookName()) && records.getReturnDate() == null)
                    .findAny().orElse(null);
            if (issueRecord != null) {
                issueRecord.setReturnDate(LocalDate.now());
                int days = (int) ChronoUnit.DAYS.between(issueRecord.getIssueDate(), issueRecord.getReturnDate());
                if (days > 7) {
                    issueRecord.setAmount(FINE * (days - 7));
                }
                return issueRecordRepository.save(issueRecord);
            }
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
        if(list!=null)
            return list.stream()
                    .filter(records -> records.getAmount() > 0)
                    .collect(Collectors.toList());
        return null;
    }


}

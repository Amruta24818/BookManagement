package com.library.bookmanagement.service;

import com.library.bookmanagement.dto.AssignBookDto;
import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;

import java.util.List;

public interface IIssueRecordService {
    IssueRecord assignBook(AssignBookDto assignBookDto);

    IssueRecord returnBook(AssignBookDto assignBookDto);

    List<IssueRecord> findUserInIssueRecords(User user);

    List<IssueRecord> getAllIssueRecord();

    List<IssueRecord> getAllFineRecord();
}

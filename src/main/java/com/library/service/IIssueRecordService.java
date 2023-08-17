package com.library.service;

import com.library.dto.AssignBookDto;
import com.library.model.IssueRecord;
import com.library.model.User;

import java.util.List;

public interface IIssueRecordService {
    IssueRecord assignBook(AssignBookDto assignBookDto);

    IssueRecord returnBook(AssignBookDto assignBookDto);

    List<IssueRecord> findUserInIssueRecords(User user);

    List<IssueRecord> getAllIssueRecord();

    List<IssueRecord> getAllFineRecord();
}

package com.library.service;

import com.library.dto.AssignBookDto;
import com.library.model.IssueRecord;

import java.util.List;

public interface IIssueRecordService {
    IssueRecord assignBook(AssignBookDto assignBookDto);

    IssueRecord returnBook(AssignBookDto assignBookDto);

   List<IssueRecord> findByUserId(int userId);
}

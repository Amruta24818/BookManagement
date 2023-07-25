package com.library.dao;

import com.library.model.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Integer> {

    List<IssueRecord> findIssueRecordByUserId(int userId);
}

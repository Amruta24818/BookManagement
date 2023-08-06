package com.library.bookmanagement.dao;

import com.library.bookmanagement.model.IssueRecord;
import com.library.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Integer> {

    List<IssueRecord> findAllIssueRecordByUserId(User user);
}

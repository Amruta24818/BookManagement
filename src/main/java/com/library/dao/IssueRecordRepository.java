package com.library.dao;

import com.library.model.IssueRecord;
import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Integer> {

    List<IssueRecord> findAllIssueRecordByUserId(User user);
}

package com.epifanova.testtasksber.repository;

import com.epifanova.testtasksber.model.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

  Optional<BookIssue> findBookIssueById(Long id);

  void deleteBookIssueById(Long id);
}

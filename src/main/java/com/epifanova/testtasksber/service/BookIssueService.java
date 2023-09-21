package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.BookIssue;
import com.epifanova.testtasksber.repository.BookIssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookIssueService {

  private final BookIssueRepository bookIssueRepository;

  public BookIssueService(BookIssueRepository bookIssueRepository) {
    this.bookIssueRepository = bookIssueRepository;
  }

  public BookIssue saveBookIssue(BookIssue bookIssue) {
    return bookIssueRepository.save(bookIssue);
  }

  public Optional<BookIssue> getBookIssue(Long id) {
    return bookIssueRepository.findBookIssueById(id);
  }

  public List<BookIssue> getAllBookIssue() {
    return bookIssueRepository.findAll();
  }

  public void deleteBookIssue(Long id) {
    bookIssueRepository.deleteBookIssueById(id);
  }

}

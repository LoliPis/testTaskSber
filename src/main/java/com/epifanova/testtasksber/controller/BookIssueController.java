package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.model.BookIssue;
import com.epifanova.testtasksber.service.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/booksIssue")
public class BookIssueController {

  private final BookIssueService bookIssueService;

  public BookIssueController(BookIssueService bookIssueService) {
    this.bookIssueService = bookIssueService;
  }

  @PostMapping
  public ResponseEntity<?> createBookIssue(@RequestBody BookIssue bookIssue) {
    bookIssueService.saveBookIssue(bookIssue);
    return ResponseEntity.ok().body(bookIssue);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getBookIssue(@PathVariable Long id) {
    return ResponseEntity.of(bookIssueService.getBookIssue(id));
  }

  @GetMapping
  public ResponseEntity<?> getAllBooksIssue() {
    List<BookIssue> booksIssue = bookIssueService.getAllBookIssue();
    if (!booksIssue.isEmpty()) {
      return ResponseEntity.ok(booksIssue);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateBookIssue(@RequestBody BookIssue bookIssue, @PathVariable Long id) {
    if (bookIssue.getId() == null) {
      bookIssue.setId(id);
    }
    if (bookIssueService.getBookIssue(id).isPresent() && Objects.equals(bookIssue.getId(), id)) {
      bookIssueService.saveBookIssue(bookIssue);
      return ResponseEntity.ok().body(bookIssue);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBookIssue(@PathVariable Long id) {
    Optional<BookIssue> bookIssueOptional = bookIssueService.getBookIssue(id);
    if (bookIssueOptional.isPresent()) {
      bookIssueService.deleteBookIssue(id);
    }
    return ResponseEntity.of(bookIssueOptional);
  }
}

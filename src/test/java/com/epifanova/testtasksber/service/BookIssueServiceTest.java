package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.BookIssue;
import com.epifanova.testtasksber.repository.BookIssueRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookIssueServiceTest {

  private static List<BookIssue> bookIssues;

  @Mock
  private BookIssueRepository bookIssueRepository;

  @InjectMocks
  private BookIssueService bookIssueService;

  @BeforeAll
  public static void initBookIssues() {
    BookIssue bookIssue1 = new BookIssue();
    bookIssue1.setId(1L);
    BookIssue bookIssue2 = new BookIssue();
    bookIssue2.setId(2L);
    bookIssues = new ArrayList<>();
    bookIssues.add(bookIssue1);
    bookIssues.add(bookIssue2);
  }

  @Test
  public void getAllTest() {
    when(bookIssueRepository.findAll()).thenReturn(bookIssues);

    assertEquals(bookIssueService.getAllBookIssue(), bookIssues);
  }

  @Test
  public void getBookIssueTest() {
    BookIssue bookIssue = new BookIssue();
    bookIssue.setId(1L);

    when(bookIssueRepository.findBookIssueById(1L)).thenReturn(Optional.of(bookIssue));
    when(bookIssueRepository.findBookIssueById(2L)).thenReturn(Optional.empty());

    Optional<BookIssue> presentBookIssue = bookIssueService.getBookIssue(1L);
    Optional<BookIssue> emptyBookIssue = bookIssueService.getBookIssue(2L);

    assertTrue(presentBookIssue.isPresent());
    assertEquals(presentBookIssue.get(), bookIssue);
    assertTrue(emptyBookIssue.isEmpty());
  }

  @Test
  public void saveBookIssueTest() {
    BookIssue bookIssue = new BookIssue();

    bookIssueService.saveBookIssue(bookIssue);

    verify(bookIssueRepository).save(bookIssue);
  }
}

package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.model.BookIssue;
import com.epifanova.testtasksber.service.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Контроллер для управления сущностью "Выдача книги пользователю".
 */
@RestController
@RequestMapping("/booksIssue")
public class BookIssueController {

  private final BookIssueService bookIssueService;

  public BookIssueController(BookIssueService bookIssueService) {
    this.bookIssueService = bookIssueService;
  }

  /**
   * Создает новую запись о выдаче книги пользователю.
   *
   * @param bookIssue Сущность BookIssue для создания.
   * @return ResponseEntity с созданной записью о выдаче.
   */
  @PostMapping
  public ResponseEntity<?> createBookIssue(@RequestBody BookIssue bookIssue) {
    bookIssueService.saveBookIssue(bookIssue);
    return ResponseEntity.ok().body(bookIssue);
  }

  /**
   * Получает информацию о выдаче книги пользователю по ее идентификатору.
   *
   * @param id Идентификатор записи о выдаче книги пользователю.
   * @return ResponseEntity с информацией о записи, если она существует, иначе 404 Not Found.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getBookIssue(@PathVariable Long id) {
    return ResponseEntity.of(bookIssueService.getBookIssue(id));
  }

  /**
   * Получает список всех записей о выдаче книг пользователям.
   *
   * @return ResponseEntity со списком всех записей или 204 No Content, если список пуст.
   */
  @GetMapping
  public ResponseEntity<?> getAllBooksIssue() {
    List<BookIssue> booksIssue = bookIssueService.getAllBookIssue();
    if (!booksIssue.isEmpty()) {
      return ResponseEntity.ok(booksIssue);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  /**
   * Обновляет информацию о записи о выдаче книги пользователю по ее идентификатору.
   *
   * @param bookIssue Сущность BookIssue с обновленными данными.
   * @param id        Идентификатор записи о выдаче книги пользователю.
   * @return ResponseEntity с обновленной записью, если она существует, иначе 404 Not Found.
   */
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

  /**
   * Удаляет запись о выдаче книги пользователю по ее идентификатору.
   *
   * @param id Идентификатор записи о выдаче книги пользователю, которую необходимо удалить.
   * @return ResponseEntity с удаленной записью, если она существует, иначе 404 Not Found.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBookIssue(@PathVariable Long id) {
    Optional<BookIssue> bookIssueOptional = bookIssueService.getBookIssue(id);
    if (bookIssueOptional.isPresent()) {
      bookIssueService.deleteBookIssue(id);
    }
    return ResponseEntity.of(bookIssueOptional);
  }
}

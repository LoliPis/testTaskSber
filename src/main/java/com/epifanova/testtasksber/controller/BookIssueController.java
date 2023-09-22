package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.DTO.BookIssueDTO;
import com.epifanova.testtasksber.model.BookIssue;
import com.epifanova.testtasksber.service.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
   * @param bookIssueDTO Сущность BookIssueDTO для создания.
   * @return ResponseEntity с созданной DTO записью о выдаче.
   */
  @PostMapping
  public ResponseEntity<?> createBookIssue(@RequestBody BookIssueDTO bookIssueDTO) {
    BookIssue bookIssue = bookIssueDTO.toBookIssue();
    if (bookIssue.getReturnDate() != null && bookIssue.getReturnDate().before(bookIssue.getIssueDate())) {
      return ResponseEntity.badRequest().body("Дата возврата не может быть раньше даты выдачи.");
    }
    bookIssueService.saveBookIssue(bookIssue);
    return ResponseEntity.ok().body(bookIssue);
  }

  /**
   * Получает информацию о выдаче книги пользователю по ее идентификатору.
   *
   * @param id Идентификатор записи о выдаче книги пользователю.
   * @return ResponseEntity с информацией DTO о записи, если она существует, иначе 404 Not Found.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getBookIssue(@PathVariable Long id) {
    Optional<BookIssue> bookIssueOptional = bookIssueService.getBookIssue(id);
    if (bookIssueOptional.isPresent()) {
      BookIssue bookIssue = bookIssueOptional.get();
      BookIssueDTO bookIssueDTO = BookIssueDTO.from(bookIssue);
      return ResponseEntity.ok(bookIssueDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Получает список всех записей о выдаче книг пользователям.
   *
   * @return ResponseEntity со списком всех DTO записей или 204 No Content, если список пуст.
   */
  @GetMapping
  public ResponseEntity<?> getAllBooksIssue() {
    List<BookIssue> booksIssues = bookIssueService.getAllBookIssue();
    if (!booksIssues.isEmpty()) {
      List<BookIssueDTO> bookIssueDTOs = booksIssues.stream()
          .map(BookIssueDTO::from)
          .collect(Collectors.toList());
      return ResponseEntity.ok(bookIssueDTOs);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  /**
   * Обновляет информацию о записи о выдаче книги пользователю по ее идентификатору.
   *
   * @param bookIssueDTO Сущность BookIssueDTO с обновленными данными.
   * @param id        Идентификатор записи о выдаче книги пользователю.
   * @return ResponseEntity с обновленной DTO записью, если она существует, иначе 404 Not Found.
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateBookIssue(@RequestBody BookIssueDTO bookIssueDTO, @PathVariable Long id) {
    if (bookIssueDTO.getId() == null) {
      bookIssueDTO.setId(id);
    }
    if (bookIssueService.getBookIssue(id).isPresent() && Objects.equals(bookIssueDTO.getId(), id)) {
      BookIssue bookIssue = bookIssueDTO.toBookIssue();
      if (bookIssue.getReturnDate() != null && bookIssue.getReturnDate().before(bookIssue.getIssueDate())) {
        return ResponseEntity.badRequest().body("Дата возврата не может быть раньше даты выдачи.");
      }
      bookIssueService.saveBookIssue(bookIssue);
      return ResponseEntity.ok().body(bookIssueDTO);
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

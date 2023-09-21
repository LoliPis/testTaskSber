package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Контроллер для управления сущностью "Книга".
 */
@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * Создает новую книгу.
   *
   * @param book Сущность Book для создания.
   * @return ResponseEntity с созданной книгой.
   */
  @PostMapping
  public ResponseEntity<?> createBook(@RequestBody Book book) {
    bookService.saveBook(book);
    return ResponseEntity.ok().body(book);
  }

  /**
   * Получает информацию о книге по ее идентификатору.
   *
   * @param id Идентификатор книги.
   * @return ResponseEntity с информацией о книге или статусом 404, если книга не найдена.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getBook(@PathVariable Long id) {
    return ResponseEntity.of(bookService.getBook(id));
  }

  /**
   * Получает список всех книг.
   *
   * @return ResponseEntity со списком книг или статусом 204, если список пуст.
   */
  @GetMapping
  public ResponseEntity<?> getAllBooks() {
    List<Book> books = bookService.getAllBooks();
    if (!books.isEmpty()) {
      return ResponseEntity.ok(books);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  /**
   * Обновляет информацию о книге по ее идентификатору.
   *
   * @param book Сущность Book с обновленными данными.
   * @param id   Идентификатор книги для обновления.
   * @return ResponseEntity с обновленной книгой или статусом 404, если книга не найдена.
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long id) {
    if (book.getId() == null) {
      book.setId(id);
    }
    if (bookService.getBook(id).isPresent() && Objects.equals(book.getId(), id)) {
      bookService.saveBook(book);
      return ResponseEntity.ok().body(book);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Удаляет книгу по ее идентификатору.
   *
   * @param id Идентификатор книги, которую необходимо удалить.
   * @return ResponseEntity с удаленной книгой или статусом 404, если книга не найдена.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable Long id) {
    Optional<Book> bookOptional = bookService.getBook(id);
    if (bookOptional.isPresent()) {
      bookService.deleteBook(id);
    }
    return ResponseEntity.of(bookOptional);
  }
}

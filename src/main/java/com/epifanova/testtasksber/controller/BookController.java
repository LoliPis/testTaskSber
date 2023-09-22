package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.DTO.BookDTO;
import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
   * @param bookDTO Сущность BookDTO для создания.
   * @return ResponseEntity с созданным DTO книги.
   */
  @PostMapping
  public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
    Book book = bookDTO.toBook();
    bookService.saveBook(book);
    return ResponseEntity.ok().body(book);
  }

  /**
   * Получает информацию о книге по ее идентификатору.
   *
   * @param id Идентификатор книги.
   * @return ResponseEntity с информацией о DTO книге или статусом 404, если книга не найдена.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getBook(@PathVariable Long id) {
    Optional<Book> bookOptional = bookService.getBook(id);
    if (bookOptional.isPresent()) {
      Book book = bookOptional.get();
      BookDTO bookDTO = BookDTO.from(book);
      return ResponseEntity.ok(bookDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Получает список всех книг.
   *
   * @return ResponseEntity со списком DTO книг или статусом 204, если список пуст.
   */
  @GetMapping
  public ResponseEntity<?> getAllBooks() {
    List<Book> books = bookService.getAllBooks();
    if (!books.isEmpty()) {
      List<BookDTO> bookDTOs = books.stream()
          .map(BookDTO::from)
          .collect(Collectors.toList());
      return ResponseEntity.ok(bookDTOs);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  /**
   * Обновляет информацию о книге по ее идентификатору.
   *
   * @param bookDTO Сущность BookDTO с обновленными данными.
   * @param id   Идентификатор книги для обновления.
   * @return ResponseEntity с обновленным DTO книги или статусом 404, если книга не найдена.
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
    if (bookDTO.getId() == null) {
      bookDTO.setId(id);
    }
    if (bookService.getBook(id).isPresent() && Objects.equals(bookDTO.getId(), id)) {
      Book book = bookDTO.toBook();
      bookService.saveBook(book);
      return ResponseEntity.ok().body(bookDTO);
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

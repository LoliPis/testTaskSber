package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping
  public ResponseEntity<?> createBook(@RequestBody Book book) {
    bookService.saveBook(book);
    return ResponseEntity.ok().body(book);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getBook(@PathVariable Long id) {
    return ResponseEntity.of(bookService.getBook(id));
  }

  @GetMapping
  public ResponseEntity<?> getAllBooks() {
    List<Book> books = bookService.getAllBooks();
    if (!books.isEmpty()) {
      return ResponseEntity.ok(books);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

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

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable Long id) {
    Optional<Book> bookOptional = bookService.getBook(id);
    if (bookOptional.isPresent()) {
      bookService.deleteBook(id);
    }
    return ResponseEntity.of(bookOptional);
  }
}

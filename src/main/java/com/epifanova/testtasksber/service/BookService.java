package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.DTO.BookDTO;
import com.epifanova.testtasksber.exceptions.BookNotFoundError;
import com.epifanova.testtasksber.exceptions.NotFoundBooks;
import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Сервис для управления сущностью "Книга".
 */
@Slf4j
@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  /**
   * Сохраняет информацию о книге.
   *
   * @param book Сущность Book для сохранения.
   * @return Сохраненная сущность Book.
   */
  public Book saveBook(Book book) {
    log.info("Книга создана");
    return bookRepository.save(book);
  }

  /**
   * Получает информацию о книге по ее идентификатору.
   *
   * @param id Идентификатор книги.
   * @return сущность Book, если она существует, иначе пустой выбрасывается exception
   */
  public Book getBook(Long id) {
    Optional<Book> bookOptional = bookRepository.findBookById(id);
    if (bookOptional.isPresent()) {
      log.info("Книга найдена");
    } else {
      String message = String.format("Книга с данным id (%d) не найдена", id);
      log.info(message);
      throw new BookNotFoundError(message);
    }
    return bookOptional.get();
  }

  /**
   * Редактирует информацию о книге по ее идентификатору и bookDTO.
   *
   * @param bookDTO экземпляр сущности BookDTO
   * @param id      Идентификатор книги
   * @return id измененной bookDTO, если она существует, иначе пустой выбрасывается exception
   */
  public Long editBook(BookDTO bookDTO, Long id) {
    if (bookDTO.getId() == null) {
      bookDTO.setId(id);
    }
    if (bookRepository.findBookById(id).isPresent() && Objects.equals(bookDTO.getId(), id)) {
      Book book = bookDTO.toBook();
      saveBook(book);
      log.info("Книга успешно изменена");
    } else {
      String message = String.format("Книга с данным id (%d) не найдена", id);
      log.info(message);
      throw new BookNotFoundError(message);
    }
    return bookDTO.getId();
  }

  /**
   * Получает список всех книг.
   *
   * @return Список сущностей Book.
   */
  public List<Book> getAllBooks() {
    List<Book> books = bookRepository.findAll();
    if (!books.isEmpty()) {
      log.info("Найдены все имеющиеся книги");
    } else {
      String message = ("Ни одной книги не найдено");
      log.info(message);
      throw new NotFoundBooks(message);
    }
    return books;
  }

  /**
   * Удаляет информацию о книге по ее идентификатору.
   *
   * @param id Идентификатор книги, которую необходимо удалить.
   */
  public void deleteBook(Long id) {
    if (bookRepository.findBookById(id).isPresent()) {
      bookRepository.deleteBookById(id);
      log.info("Книга удалена");
    } else {
      String message = String.format("Книга с данным id (%d) не найдена", id);
      log.info(message);
      throw new BookNotFoundError(message);
    }
  }
}

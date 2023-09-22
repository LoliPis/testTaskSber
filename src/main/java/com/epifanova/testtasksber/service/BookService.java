package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
  public  Book saveBook(Book book) {
    log.info("Книга создана");
    return bookRepository.save(book);
  }

  /**
   * Получает информацию о книге по ее идентификатору.
   *
   * @param id Идентификатор книги.
   * @return Optional сущности Book, если она существует, иначе пустой Optional.
   */
  public Optional<Book> getBook(Long id){
    log.info("Книга найдена");
    return bookRepository.findBookById(id);
  }

  /**
   * Получает список всех книг.
   *
   * @return Список сущностей Book.
   */
  public List<Book> getAllBooks() {
    log.info("Найдены все имеющиеся книги");
    return bookRepository.findAll();
  }

  /**
   * Удаляет информацию о книге по ее идентификатору.
   *
   * @param id Идентификатор книги, которую необходимо удалить.
   */
  public void deleteBook(Long id){
    bookRepository.deleteBookById(id);
    log.info("Книга удалена");
  }
}

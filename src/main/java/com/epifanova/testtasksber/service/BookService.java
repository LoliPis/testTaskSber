package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.Book;
import com.epifanova.testtasksber.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для управления сущностью "Книга".
 */
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
    return bookRepository.save(book);
  }

  /**
   * Получает информацию о книге по ее идентификатору.
   *
   * @param id Идентификатор книги.
   * @return Optional сущности Book, если она существует, иначе пустой Optional.
   */
  public Optional<Book> getBook(Long id){
    return bookRepository.findBookById(id);
  }

  /**
   * Получает список всех книг.
   *
   * @return Список сущностей Book.
   */
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  /**
   * Удаляет информацию о книге по ее идентификатору.
   *
   * @param id Идентификатор книги, которую необходимо удалить.
   */
  public void deleteBook(Long id){
    bookRepository.deleteBookById(id);
  }
}

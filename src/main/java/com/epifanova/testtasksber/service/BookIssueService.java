package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.BookIssue;
import com.epifanova.testtasksber.repository.BookIssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Сервис для управления сущностью "Выдача книги пользователю".
 */
@Service
public class BookIssueService {

  private final BookIssueRepository bookIssueRepository;

  public BookIssueService(BookIssueRepository bookIssueRepository) {
    this.bookIssueRepository = bookIssueRepository;
  }

  /**
   * Сохраняет информацию о выдаче книги.
   *
   * @param bookIssue Сущность BookIssue для сохранения.
   * @return Сохраненная сущность BookIssue.
   */
  public BookIssue saveBookIssue(BookIssue bookIssue) {
    return bookIssueRepository.save(bookIssue);
  }

  /**
   * Получает информацию о выдаче книги по ее идентификатору.
   *
   * @param id Идентификатор выдачи книги.
   * @return Optional сущности BookIssue, если она существует, иначе пустой Optional.
   */
  public Optional<BookIssue> getBookIssue(Long id) {
    return bookIssueRepository.findBookIssueById(id);
  }

  /**
   * Получает список всех выдач книг.
   *
   * @return Список сущностей BookIssue.
   */
  public List<BookIssue> getAllBookIssue() {
    return bookIssueRepository.findAll();
  }

  /**
   * Удаляет информацию о выдаче книги по ее идентификатору.
   *
   * @param id Идентификатор выдачи книги, которую необходимо удалить.
   */
  public void deleteBookIssue(Long id) {
    bookIssueRepository.deleteBookIssueById(id);
  }

}

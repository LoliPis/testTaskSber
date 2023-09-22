package com.epifanova.testtasksber.repository;

import com.epifanova.testtasksber.model.BookIssue;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для операций с сущностью "Выдача книги пользователю".
 */
public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

  /**
   * Находит выдачу книги по ее идентификатору.
   *
   * @param id Идентификатор выдачи книги.
   * @return Optional сущности BookIssue, если она существует, иначе пустой Optional.
   */
  Optional<BookIssue> findBookIssueById(Long id);

  /**
   * Удаляет выдачу книги по ее идентификатору.
   *
   * @param id Идентификатор выдачи книги, которую необходимо удалить.
   */
  @Transactional
  void deleteBookIssueById(Long id);
}

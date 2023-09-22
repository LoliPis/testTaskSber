package com.epifanova.testtasksber.repository;

import com.epifanova.testtasksber.model.Book;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для операций с сущностью "Книга".
 */
public interface BookRepository extends JpaRepository<Book, Long> {

  /**
   * Находит книгу по ее идентификатору.
   *
   * @param id Идентификатор книги.
   * @return Optional сущности Book, если она существует, иначе пустой Optional.
   */
  Optional<Book> findBookById(Long id);

  /**
   * Удаляет книгу по ее идентификатору.
   *
   * @param id Идентификатор книги, которую необходимо удалить.
   */
  @Transactional
  void deleteBookById(Long id);
}

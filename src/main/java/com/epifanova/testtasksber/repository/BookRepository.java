package com.epifanova.testtasksber.repository;

import com.epifanova.testtasksber.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findBookById(Long id);

  void deleteBookById(Long id);
}

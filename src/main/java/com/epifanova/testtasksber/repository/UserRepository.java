package com.epifanova.testtasksber.repository;

import com.epifanova.testtasksber.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для операций с сущностью "Пользователь".
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Находит пользователя по его идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return Optional сущности User, если она существует, иначе пустой Optional.
   */
  Optional<User> findUserById(Long id);

  /**
   * Удаляет пользователя по его идентификатору.
   *
   * @param id Идентификатор пользователя, которого необходимо удалить.
   */
  void deleteUserById(Long id);
}

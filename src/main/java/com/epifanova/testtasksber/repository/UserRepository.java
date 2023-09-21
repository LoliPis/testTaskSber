package com.epifanova.testtasksber.repository;

import com.epifanova.testtasksber.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserById(Long id);

  void deleteUserById(Long id);
}

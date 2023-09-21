package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.User;
import com.epifanova.testtasksber.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для управления сущностью "Пользователь".
 */
@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Сохраняет информацию о пользователе.
   *
   * @param user Сущность User для сохранения.
   * @return Сохраненная сущность User.
   */
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  /**
   * Получает информацию о пользователе по его идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return Optional сущности User, если она существует, иначе пустой Optional.
   */
  public Optional<User> getUser(Long id) {
    return userRepository.findUserById(id);
  }

  /**
   * Получает список всех пользователей.
   *
   * @return Список сущностей User.
   */
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Удаляет информацию о пользователе по его идентификатору.
   *
   * @param id Идентификатор пользователя, которого необходимо удалить.
   */
  public void deleteUser(Long id) {
    userRepository.deleteUserById(id);
  }
}

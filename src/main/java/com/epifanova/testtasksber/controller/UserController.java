package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.model.User;
import com.epifanova.testtasksber.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Контроллер для управления сущностью "Пользователь".
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Создает нового пользователя.
   *
   * @param user Сущность User для создания.
   * @return ResponseEntity с созданным пользователем.
   */
  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody User user) {
     userService.saveUser(user);
     return ResponseEntity.ok().body(user);
  }

  /**
   * Получает информацию о пользователе по его идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return ResponseEntity с информацией о пользователе или статусом 404, если пользователь не найден.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable Long id) {
    return ResponseEntity.of(userService.getUser(id));
  }

  /**
   * Получает список всех пользователей.
   *
   * @return ResponseEntity со списком пользователей или статусом 204, если список пуст.
   */
  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userService.getAllUsers();
    if (!users.isEmpty()) {
      return ResponseEntity.ok(users);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  /**
   * Обновляет информацию о пользователе по его идентификатору.
   *
   * @param user Сущность User с обновленными данными.
   * @param id   Идентификатор пользователя для обновления.
   * @return ResponseEntity с обновленным пользователем или статусом 404, если пользователь не найден.
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
    if (user.getId() == null) {
      user.setId(id);
    }
    if (userService.getUser(id).isPresent() && Objects.equals(user.getId(), id)) {
      userService.saveUser(user);
      return ResponseEntity.ok().body(user);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Удаляет пользователя по его идентификатору.
   *
   * @param id Идентификатор пользователя, которого необходимо удалить.
   * @return ResponseEntity с удаленным пользователем или статусом 404, если пользователь не найден.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    Optional<User> userOptional = userService.getUser(id);
    if (userOptional.isPresent()) {
      userService.deleteUser(id);
    }
    return ResponseEntity.of(userOptional);
  }
}

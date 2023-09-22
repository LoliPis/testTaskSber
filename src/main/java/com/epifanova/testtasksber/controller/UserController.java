package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.DTO.UserDTO;
import com.epifanova.testtasksber.model.User;
import com.epifanova.testtasksber.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
   * @param userDTO Сущность UserDTO для создания.
   * @return ResponseEntity с созданным DTO пользователем.
   */
  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
    User user = userDTO.toUser();
    userService.saveUser(user);
    return ResponseEntity.ok().body(userDTO);
  }

  /**
   * Получает информацию о пользователе по его идентификатору.
   *
   * @param id Идентификатор пользователя.
   * @return ResponseEntity с информацией о DTO пользователе или статусом 404, если пользователь не найден.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable Long id) {
    Optional<User> userOptional = userService.getUser(id);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      UserDTO userDTO = UserDTO.from(user);
      return ResponseEntity.ok(userDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Получает список всех пользователей.
   *
   * @return ResponseEntity со списком DTO пользователей или статусом 204, если список пуст.
   */
  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userService.getAllUsers();
    if (!users.isEmpty()) {
      List<UserDTO> userDTOs = users.stream()
          .map(UserDTO::from)
          .collect(Collectors.toList());
      return ResponseEntity.ok(userDTOs);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  /**
   * Обновляет информацию о пользователе по его идентификатору.
   *
   * @param userDTO Сущность UserDTO с обновленными данными.
   * @param id   Идентификатор пользователя для обновления.
   * @return ResponseEntity с обновленным DTO пользователем или статусом 404, если пользователь не найден.
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
    if (userDTO.getId() == null) {
      userDTO.setId(id);
    }
    if (userService.getUser(id).isPresent() && Objects.equals(userDTO.getId(), id)) {
      User user = userDTO.toUser();
      userService.saveUser(user);
      return ResponseEntity.ok().body(userDTO);
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

package com.epifanova.testtasksber.controller;

import com.epifanova.testtasksber.model.User;
import com.epifanova.testtasksber.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody User user) {
     userService.saveUser(user);
     return ResponseEntity.ok().body(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable Long id) {
    return ResponseEntity.of(userService.getUser(id));
  }

  @GetMapping
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userService.getAllUsers();
    if (!users.isEmpty()) {
      return ResponseEntity.ok(users);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

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

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    Optional<User> userOptional = userService.getUser(id);
    if (userOptional.isPresent()) {
      userService.deleteUser(id);
    }
    return ResponseEntity.of(userOptional);
  }
}

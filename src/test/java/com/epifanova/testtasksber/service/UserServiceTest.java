package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.User;
import com.epifanova.testtasksber.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  private static List<User> users;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @BeforeAll
  public static void initUsers() {
    User user1 = new User();
    user1.setId(1L);
    User user2 = new User();
    user2.setId(2L);
    users = new ArrayList<>();
    users.add(user1);
    users.add(user2);
  }

  @Test
  public void getAllTest() {
    when(userRepository.findAll()).thenReturn(users);

    assertEquals(userService.getAllUsers(), users);
  }

  @Test
  public void getUserTest() {
    User user = new User();
    user.setId(1L);

    when(userRepository.findUserById(1L)).thenReturn(Optional.of(user));
    when(userRepository.findUserById(2L)).thenReturn(Optional.empty());

    Optional<User> presentUser = userService.getUser(1L);
    Optional<User> emptyUser = userService.getUser(2L);

    assertTrue(presentUser.isPresent());
    assertEquals(presentUser.get(), user);
    assertTrue(emptyUser.isEmpty());
  }

  @Test
  public void saveUserTest() {
    User user = new User();

    userService.saveUser(user);

    verify(userRepository).save(user);
  }
}

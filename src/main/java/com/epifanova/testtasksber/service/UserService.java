package com.epifanova.testtasksber.service;

import com.epifanova.testtasksber.model.User;
import com.epifanova.testtasksber.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public Optional<User> getUser(Long id) {
    return userRepository.findUserById(id);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(Long id) {
    userRepository.deleteUserById(id);
  }
}

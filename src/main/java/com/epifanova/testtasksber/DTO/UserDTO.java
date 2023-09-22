package com.epifanova.testtasksber.DTO;

import com.epifanova.testtasksber.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
  private Long id;
  private String name;
  private String email;

  public static UserDTO from(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setName(user.getName());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }

  public User toUser() {
    User user = new User();
    user.setId(this.id);
    user.setName(this.name);
    user.setEmail(this.email);
    return user;
  }

}

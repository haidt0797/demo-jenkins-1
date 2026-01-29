package com.example.demo.model.account;

import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
import com.example.demo.entity.UsersRole;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

  private Long id;
  private String name;

  public AccountDTO(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.username = user.getUsername();
    this.roles = user.getUserRoles().stream().map(UsersRole::getRole).map(Roles::getRole).collect(Collectors.toSet());
    this.password = user.getPassword();
  }

  private String username;
  private String password;
  private Set<String> roles;


}

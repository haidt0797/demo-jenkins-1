package com.example.demo.entity;


import com.example.demo.model.account.AccountDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String name;

  public User(AccountDTO accountDTO) {
    this.username = accountDTO.getUsername();
    this.password = accountDTO.getPassword();
    this.name = accountDTO.getName();
  }

  @OneToMany(
      fetch = FetchType.EAGER,
      mappedBy = "user",
          cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
  )
  private Set<UsersRole> userRoles = new HashSet<>();

}

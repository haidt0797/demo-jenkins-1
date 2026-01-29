package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Roles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String role;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
  private Set<UsersRole> usersRoles = new HashSet<>();

}

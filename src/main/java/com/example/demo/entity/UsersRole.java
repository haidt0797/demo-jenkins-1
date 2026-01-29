package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_roles")
@Getter
@Setter
@NoArgsConstructor
public class UsersRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "USER_ID")
  private Long userId;
  @Column(name = "ROLE_ID")
  private Long roleId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
  private Roles role;

  public UsersRole(Long userId, Long roleId) {
    this.userId = userId;
    this.roleId = roleId;
  }
}

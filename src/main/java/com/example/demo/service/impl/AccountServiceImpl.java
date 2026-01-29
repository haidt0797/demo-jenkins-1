package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UsersRole;
import com.example.demo.exception.Errors;
import com.example.demo.exception.Exceptions;
import com.example.demo.model.account.AccountDTO;
import com.example.demo.repo.RolesRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.UserRoleRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

  private final RolesRepository rolesRepository;
  private final UserRepository userRepository;
  private final UserService userService;

  @Override
  @Transactional
  public AccountDTO createAccount(AccountDTO accountDTO) {
    if (userRepository.existsByUsername(accountDTO.getUsername())) {
      throw Exceptions.of(Errors.EXISTS_USERNAME, accountDTO.getUsername());
    }
    var user = new User(accountDTO);
    userRepository.save(user);
    var roles = Optional.of(accountDTO).map(AccountDTO::getRoles)
            .map(rolesRepository::findAllByRoleIn).stream()
            .flatMap(Collection::stream)
            .map(rl -> new UsersRole(user.getId(), rl.getId()))
            .collect(Collectors.toSet());
    if (CollectionUtils.isEmpty(roles)) {
      throw Exceptions.of(Errors.NOT_EXIST_ROLES, StringUtils.join(accountDTO.getRoles(), ", "));
    }
    user.setUserRoles(roles);
    userRepository.save(user);
    return null;
  }

  @Override
  public AccountDTO viewDetail(Long id) {
    var user = userRepository.findById(id).map(AccountDTO::new)
        .orElseThrow(() -> Exceptions.of(Errors.NOT_EXISTS_USER));
    if (!user.getUsername().equals(userService.getCurrentUser().getUsername())) {
      user.setPassword(null);
    }
    return user;
  }

}

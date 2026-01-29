package com.example.demo.service;

import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.User;
import com.example.demo.exception.Errors;
import com.example.demo.exception.Exceptions;
import com.example.demo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userRepository.findByUsername(username).map(CustomUserDetails::new)
        .orElseThrow(() -> Exceptions.of(Errors.NOT_EXISTS_USER_NAME, username));
  }


  public UserDetails loadUserById(Long userId) {
    return userRepository.findById(userId).map(CustomUserDetails::new)
        .orElseThrow(() -> Exceptions.of(Errors.NOT_EXISTS_USER_ID, String.valueOf(userId)));
  }

  public User getCurrentUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      return userRepository.findByUsername(((UserDetails) principal).getUsername())
          .orElseThrow(() -> Exceptions.of(Errors.NOT_EXISTS_USER_NAME, ((UserDetails) principal).getUsername()));
    } else {
      return userRepository.findByUsername(principal.toString())
          .orElseThrow(() -> Exceptions.of(Errors.NOT_EXISTS_USER_NAME, principal.toString()));
    }
  }
}

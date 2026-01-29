package com.example.demo.controller;

import com.example.demo.model.account.AccountDTO;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.model.authentication.AuthenticationRequest;
import com.example.demo.model.authentication.AuthenticationResponse;
import com.example.demo.entity.CustomUserDetails;
import com.example.demo.service.AccountService;
import com.example.demo.service.FeignClientService;
import com.example.demo.service.UserService;
import com.example.demo.util.ResponseData;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final AccountService accountService;

    private final FeignClientService feignClientService;

    @PostMapping("/login")
    public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String token = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        var roles = authentication.getAuthorities();
        var user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        return new AuthenticationResponse(user.getId(), token, user.getName(), user.getUsername(), roles);
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @PostMapping("/register")
    public ResponseEntity<ResponseData<Object>> create(@RequestBody AccountDTO dto){
        return ResponseUtils.created(accountService.createAccount(dto), HttpStatus.CREATED.value());
    }

}


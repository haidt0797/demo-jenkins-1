package com.example.demo.controller;

import com.example.demo.service.AccountService;
import com.example.demo.util.ResponseData;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final AccountService accountService;

    @GetMapping("/viewDetail/{id}")
    public ResponseEntity<ResponseData<Object>> viewDetail(@PathVariable Long id) {
        return ResponseUtils.success(accountService.viewDetail(id), HttpStatus.OK.value());
    }

}

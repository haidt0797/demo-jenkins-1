package com.example.demo.controller;

import com.example.demo.service.FeignClientService;
import com.example.demo.util.ResponseData;
import com.example.demo.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feignClient")
public class FeignClientController {

    private final FeignClientService feignClientService;

    @GetMapping("/view")
    public ResponseEntity<ResponseData<Object>> viewDetail() {
        return ResponseUtils.success(feignClientService.getFeignClientDemo(), HttpStatus.OK.value());
    }
}

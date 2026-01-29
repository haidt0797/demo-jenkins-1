package com.example.demo.controller;

import com.example.demo.service.impl.KafkaProducerService;
import com.example.demo.util.ResponseData;
import com.example.demo.util.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaProducerService service;

    @GetMapping("/send")
    public ResponseEntity<ResponseData<Object>> sendMessage(HttpServletRequest request) {
        return ResponseUtils.success(service.send(request), HttpStatus.OK.value());
    }

}

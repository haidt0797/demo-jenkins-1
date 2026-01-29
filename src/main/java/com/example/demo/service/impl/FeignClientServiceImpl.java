package com.example.demo.service.impl;

import com.example.demo.feign.FeignClientDemo;
import com.example.demo.service.FeignClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class FeignClientServiceImpl implements FeignClientService {

    private final FeignClientDemo feignClientDemo;

    @Override
    public String getFeignClientDemo() {
        var rqId = UUID.randomUUID().toString();
        return feignClientDemo.feignClient(rqId);
    }
}

package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "my-client", url = "http://localhost:10430", configuration = FeignConfig.class)
public interface FeignClientDemo {


    @GetMapping(value = "/feignClient")
    String feignClient(@RequestParam String requestId);
}

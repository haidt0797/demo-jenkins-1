package com.example.demo.feign;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer retryer() {
        // Cấu hình retry: retry lại tối đa 5 lần, với khoảng thời gian tăng dần
        return new Retryer.Default(100, 1000, 5);
    }
}

package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SpringCloudStreamConfiguration {
    public static final String BINDING_NAME_TEST_KAFKA_0 = "test-kafka-out-0";
}
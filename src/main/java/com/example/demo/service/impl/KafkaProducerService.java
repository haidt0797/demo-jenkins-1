package com.example.demo.service.impl;

import com.example.demo.aop.Event;
import com.example.demo.config.KafkaProducer;
import com.example.demo.config.SpringCloudStreamConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaProducer kafkaProducer;

    public String send(HttpServletRequest httpServletRequest) {
        kafkaProducer.sendMessageToKafka(SpringCloudStreamConfiguration.BINDING_NAME_TEST_KAFKA_0,
                Event.builder()
                        .eventCreatedAt(new Date())
                        .key(UUID.randomUUID().toString())
                        .type("test")
                        .messageId(httpServletRequest.getHeader("clientMessageId"))
                        .data("abc").build());
        return "success";
    }
}

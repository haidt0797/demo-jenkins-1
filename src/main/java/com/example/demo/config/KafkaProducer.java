package com.example.demo.config;

import com.example.demo.aop.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final StreamBridge streamBridge;

    public void sendMessageToKafka(String bindingName, Event event) {
        try {
            Message message = MessageBuilder.withPayload(event).setHeader("partitionKey", event.getKey()).build();
            log.info("data send kafka: {} - {}", bindingName, event);
            streamBridge.send(bindingName, message);
        } catch (Exception ex) {
            log.error("Send message kafka error: {}", ex.getMessage());
        }
    }
}
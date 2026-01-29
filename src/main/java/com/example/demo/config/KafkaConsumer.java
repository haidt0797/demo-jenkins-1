package com.example.demo.config;

import com.example.demo.util.JsonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.kafka.support.Acknowledgment;

import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {

    @Bean
    public Consumer<Message<Object>> consumerTest() {
        return message -> {
            var payload = message.getPayload();
            Acknowledgment ack =
                    message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);

            try {
                System.out.println("👉 Receive message: " + JsonUtil.toJson(payload));

                // TODO xử lý business ở đây

                // commit offset
                if (ack != null) {
                    ack.acknowledge();
                }

            } catch (Exception e) {
                System.err.println("❌ Consumer error: " + e.getMessage());
                throw e; // để Spring Cloud Stream retry
            }
        };
    }
}

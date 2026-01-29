package com.example.demo.aop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class Event<K, T> {

    private final K key;
    private final T data;
    private final String type;
    private final String messageId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final Date eventCreatedAt;

    public Event() {
        this.key = null;
        this.data = null;
        this.type = null;
        this.messageId = null;
        this.eventCreatedAt = null;
    }

    public Event(K key, T data, String type) {
        this.key = key;
        this.data = data;
        this.type = type;
        this.messageId = UUID.randomUUID().toString();
        this.eventCreatedAt = new Date();
    }
}


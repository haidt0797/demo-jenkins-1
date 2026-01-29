package com.example.demo.util;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ResponseError implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String timestamp;

  private final String clientMessageId;

  private final String transactionId;
  private final String path;
  private int code;
  private String message;
  private int status;

  public ResponseError(String clientMessageId, String transactionId, String path) {
    this.code = 9999;
    this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    this.message = "Có lỗi xảy ra! Xin vui lòng liên hệ Administrator";
    this.clientMessageId = clientMessageId;
    this.transactionId = transactionId;
    this.path = path;
    this.status = 500;
  }

  public ResponseError error(int code, String message, int status) {
    this.code = code;
    this.message = message;
    this.status = status;
    return this;
  }
}

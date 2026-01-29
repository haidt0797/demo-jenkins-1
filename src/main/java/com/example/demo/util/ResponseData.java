package com.example.demo.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author haidv
 * @version 1.0
 */
@Getter
public class ResponseData<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String timestamp;

  private final String clientMessageId;

  private final String transactionId;
  private final String path;
  private int code;
  private String message;
  private int status;

  @Setter
  private T data;

  public ResponseData(String clientMessageId, String transactionId, String path) {
    this.code = 0;
    this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    this.message = "Successful!";
    this.clientMessageId = clientMessageId;
    this.transactionId = transactionId;
    this.path = path;
    this.status = 200;
  }

  public ResponseData<T> success(T data,int status) {
    this.data = data;
    this.status = status;
    return this;
  }
  public ResponseData<T> status(int status) {
    this.data = data;
    this.status = status;
    return this;
  }

  public ResponseData<T> error(int code, String message, int status) {
    this.code = code;
    this.message = message;
    this.status = status;
    return this;
  }

  public ResponseData<T> error(int code, String message, T data, int status) {
    this.data = data;
    this.code = code;
    this.message = message;
    this.status = status;
    return this;
  }

}

package com.example.demo.exception;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Exceptions extends RuntimeException {

  private String messageKey;
  private String message;
  private Throwable throwable;
  private List<String> messages;
  private Integer code;
  private Integer httpStatus;

  public Exceptions(Integer code, String msg, Integer httpStatus) {
    this.message = msg;
    this.code = code;
    this.httpStatus = httpStatus;
  }

  public static Exceptions of(Errors error) {
    return new Exceptions(
        error.getCode(),
        error.getMessage(),
        error.getHttpStatus().value()
    );
  }

  public static Exceptions of(Errors error, Object... params) {
    return new Exceptions(
        error.getCode(),
        String.format(error.getMessage(), params),
        error.getHttpStatus().value()
    );
  }
}

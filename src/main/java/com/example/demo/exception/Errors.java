package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Errors {

  BAD_REQUEST(1, "Sai form", HttpStatus.BAD_REQUEST),
  UNAUTHORIZED(2, "Không có quyền", HttpStatus.UNAUTHORIZED),
  EXISTS_USERNAME(3, "Username %s đã tồn tại", HttpStatus.INTERNAL_SERVER_ERROR),
  NOT_EXISTS_USER_ID(4, "UserId %s không tồn tại", HttpStatus.INTERNAL_SERVER_ERROR),
  NOT_EXISTS_USER_NAME(5, "Username %s không tồn tại", HttpStatus.INTERNAL_SERVER_ERROR),
  NOT_EXISTS_USER(6, "User không tồn tại", HttpStatus.INTERNAL_SERVER_ERROR),
  MISSING_REQUEST_PARAMETER(7, "Thiếu Parameter" , HttpStatus.BAD_REQUEST),
  NOT_EXIST_ROLES(8, "Roles không tồn tại: %s" , HttpStatus.BAD_REQUEST)
  ;

  private final int code;

  private final String message;

  private final HttpStatus httpStatus;

  Errors(int code, String message, HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
    this.code = code;
    this.message = message;
  }

}

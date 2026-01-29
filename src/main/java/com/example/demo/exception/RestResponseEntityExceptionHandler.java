package com.example.demo.exception;

import com.example.demo.util.ResponseData;
import com.example.demo.util.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @SuppressWarnings("rawtypes")
  private ResponseEntity handleError(Errors commonError) {
    return this.handleError(commonError, null, null);
  }

  @SuppressWarnings("rawtypes")
  private ResponseEntity handleError(Errors commonError, Object[] messageArg) {
    return this.handleError(commonError, messageArg, null);
  }

  @SuppressWarnings("rawtypes")
  private ResponseEntity handleError(
      Errors commonError, Object[] messageArg, List<SubError> subErrors) {
    String message = commonError.getMessage();
    int code = commonError.getCode();
    if (messageArg != null) {
      message = String.format(message, messageArg);
    }
    HttpStatus httpStatus = commonError.getHttpStatus();
    return ResponseUtils.error(code, message, subErrors, httpStatus);
  }

  private List<SubError> getSubErrors(MethodArgumentNotValidException e) {
    List<SubError> subErrors = new ArrayList<>();
    List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
      SubError subError =
          new SubError(
              fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
      subErrors.add(subError);
    }
    return subErrors;
  }

  /**
   * @author haidv
   * @version 1.0
   */
  @Getter
  @RequiredArgsConstructor
  public static class SubError {

    private final String fieldName;

    private final Object fieldValue;

    private final String message;
  }


  @ExceptionHandler(Exceptions.class)
  ResponseEntity<ResponseData<Object>> handleHcmException(Exceptions e,
                                                          HttpServletRequest request) {
    HttpStatus httpStatus = HttpStatus.valueOf(e.getHttpStatus());
    return ResponseUtils.error(e.getCode(), e.getMessage(), httpStatus);
  }
}

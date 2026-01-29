package com.example.demo.util;

import com.example.demo.config.HttpMonitoringInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

/**
 * @author haidv
 * @version 1.0
 */
public class ResponseUtils {

  private ResponseUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static <T> ResponseEntity<ResponseData<T>> success() {
    var serviceHeader = extractServiceHeader();
    return ResponseEntity.ok(
        new ResponseData<>(
            serviceHeader.getClientMessageId(),
            serviceHeader.getTransactionId(),
            serviceHeader.getHttpPath()));
  }

  public static <T> ResponseEntity<ResponseData<T>> success(T o,int status) {
    var serviceHeader = extractServiceHeader();
    return ResponseEntity.ok(
        new ResponseData<T>(
            serviceHeader.getClientMessageId(),
            serviceHeader.getTransactionId(),
            serviceHeader.getHttpPath())
            .success(o,status));
  }

  public static <T> ResponseEntity<ResponseData<T>> created() {
    var serviceHeader = extractServiceHeader();
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            new ResponseData<>(
                serviceHeader.getClientMessageId(),
                serviceHeader.getTransactionId(),
                serviceHeader.getHttpPath()));
  }

  public static <T> ResponseEntity<ResponseData<T>> created(T o,int status) {
    var serviceHeader = extractServiceHeader();
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            new ResponseData<T>(
                serviceHeader.getClientMessageId(),
                serviceHeader.getTransactionId(),
                serviceHeader.getHttpPath())
                .success(o,status));
  }

  public static <T> ResponseEntity<ResponseData<T>> error(
      int code, String message, HttpStatus status) {
    return ResponseEntity.status(status)
        .body(getResponseDataError(code, message, null, status.value()));
  }

  public static <T> ResponseEntity<ResponseData<T>> error(
      int code, String message, T data, HttpStatus status) {
    return ResponseEntity.status(status)
        .body(getResponseDataError(code, message, data, status.value()));
  }

  public static <T> ResponseData<T> getResponseDataError(
      int code, String message, T data, int status) {
    var serviceHeader = extractServiceHeader();
    return new ResponseData<T>(
        serviceHeader.getClientMessageId(),
        serviceHeader.getTransactionId(),
        serviceHeader.getHttpPath())
        .error(code, message, data, status);
  }

  private static HttpMonitoringInterceptor.ServiceHeader extractServiceHeader() {
    try {
      var str = ThreadContext.get("serviceHeader");
      var serviceHeader = JsonUtil.fromJson(str, HttpMonitoringInterceptor.ServiceHeader.class);
      return serviceHeader == null ? getServiceHeader() : serviceHeader;
    } catch (Exception e) {
      return new HttpMonitoringInterceptor.ServiceHeader();
    }
  }

  private static HttpMonitoringInterceptor.ServiceHeader getServiceHeader() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    var serviceHeader = new HttpMonitoringInterceptor.ServiceHeader();
    serviceHeader.setClientMessageId(StringUtils.hasText(request.getHeader("clientMessageId"))
        ? request.getHeader("clientMessageId") : UUID.randomUUID().toString());
    serviceHeader.setTransactionId(StringUtils.hasText(request.getHeader("TransactionId"))
            ? request.getHeader("TransactionId") : UUID.randomUUID().toString());
    serviceHeader.setHttpPath(request.getRequestURL().toString());
    return serviceHeader;
  }
}

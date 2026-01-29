package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.Arrays;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final ApplicationContext applicationContext;
  private final HttpMonitoringInterceptor interceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(
        new MappedInterceptor(
            applicationContext.getBeansWithAnnotation(RequestMapping.class).values().stream()
                .map(Object::getClass)
                .filter(
                    item ->
                        StringUtils.equals(
                            item.getPackageName(), "com.example.demo.controller"))
                .map(item -> AnnotationUtils.findAnnotation(item, RequestMapping.class))
                .filter(Objects::nonNull)
                .map(RequestMapping::path)
                .flatMap(Arrays::stream)
                .map(item -> String.format("%s/**", item))
                .distinct()
                .toArray(String[]::new),
            interceptor));
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOriginPatterns("*")
        .allowedHeaders("*")
        .allowedMethods("*")
        .allowCredentials(true);
  }
}

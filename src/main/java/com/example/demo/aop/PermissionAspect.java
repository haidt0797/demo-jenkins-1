package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Aspect
@Configuration
@Slf4j
public class PermissionAspect {

//    @Before("execution(* com.example.demo.service.impl.*.*(..))")
//    public void before(JoinPoint joinPoint) {
//        log.info("before call method {}", joinPoint.getSignature().getName());
//    }
//
//    @After("execution(* com.example.demo.service.impl.*.*(..))")
//    public void after(JoinPoint joinPoint) {
//        log.info("after call method {}", joinPoint.getSignature().getName());
//    }

    @Around("execution(* com.example.demo.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String id = UUID.randomUUID().toString();
        ThreadContext.put("clientMessageId", id);
        log.info("method [{}] start", methodName);
        long startTime = System.currentTimeMillis();
        log.info("Start Time Taken by {} is {}", joinPoint, startTime);
        Object o = joinPoint.proceed();

        Long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Time Taken by {} is {}", joinPoint, timeTaken);
        return o;
    }


}

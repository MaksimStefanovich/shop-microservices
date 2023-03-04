package com.stefanovich.productback.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MyCacheAspect {

  @Around("@annotation(com.stefanovich.productback.aop.annotation.MyCache)")
  public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    try {
      Object result = joinPoint.proceed();
      return result;

    } catch (Throwable e) {
      throw e;
    } finally {
      log.info("Execution method time = {}ms", System.currentTimeMillis() - startTime);

    }
  }
}

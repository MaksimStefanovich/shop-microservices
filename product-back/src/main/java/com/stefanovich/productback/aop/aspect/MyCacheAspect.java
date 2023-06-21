package com.stefanovich.productback.aop.aspect;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.model.dto.PageDto;
import com.stefanovich.productback.repository.redis.ItemSearchFilterCacheRepository;
import com.stefanovich.productback.service.cache.ItemCache;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class MyCacheAspect {

  private final ItemSearchFilterCacheRepository itemSearchFilterCacheRepository;
  private final ItemCache itemCache;

//  @Around("@annotation(com.stefanovich.productback.aop.annotation.MyCache)")
//  public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
//    long startTime = System.currentTimeMillis();
//    try {
//      Object result = joinPoint.proceed();
//      return result;
//
//    } catch (Throwable e) {
//      throw e;
//    } finally {
//      log.info("Execution method time = {}ms", System.currentTimeMillis() - startTime);
//
//    }
//  }

  @Around("@annotation(com.stefanovich.productback.aop.annotation.MyCache)")
  public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
    ItemSearchFilterDto arg = (ItemSearchFilterDto) joinPoint.getArgs()[0];

    Optional<PageDto<Item>> cacheItems = itemCache.get(arg);
    if (cacheItems.isPresent()) {
      log.debug("use cache result");
      return cacheItems.get();
    }
    log.debug("cache miss");
    PageDto result = (PageDto) joinPoint.proceed();
    itemCache.put(arg, result);
    return result;
  }
}

package com.stefanovich.cartandorderback.config.feign;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NaiveRetryer implements feign.Retryer {

  int count = 0;

  @Override
  public void continueOrPropagate(RetryableException e) {
    log.info("retry for exception ", e);

    if (count == 5) {
      int httpStatusCode = e.status();
      if (httpStatusCode >= 500) {
        throw new RetryableException(
            e.status(),
            e.getMessage(),
            e.request().httpMethod(),
            e,
            null, e.request());
      } else {
        throw new RuntimeException("Stopping application.");
      }
    }

    try {
      Thread.sleep(1000L);
      count++;
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw e;
    }


  }

  @Override
  public Retryer clone() {
    return new NaiveRetryer();
  }

}

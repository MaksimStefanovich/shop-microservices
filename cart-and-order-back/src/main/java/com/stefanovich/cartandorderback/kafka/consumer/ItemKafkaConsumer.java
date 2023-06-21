package com.stefanovich.cartandorderback.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefanovich.cartandorderback.model.dto.ItemUpdateKafkaDto;
import com.stefanovich.cartandorderback.model.jpa.CartItem;
import com.stefanovich.cartandorderback.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ItemKafkaConsumer {

  private final CartService cartService;
  private final ObjectMapper objectMapper;

  @SneakyThrows
  @KafkaListener(topics = "${kafka.topic.name.items-update}")
  public void consumeItemsUpdate(@Payload String itemJson,
      @Header(KafkaHeaders.RECEIVED_KEY) String key) {
    log.info("receive item from kafka = {}, with key = {}", itemJson, key);
    if (!cartService.existsCartItemById(key)) {
      log.info("item with id = {} not found", key);
      return;
    }
    log.info("update item with id = {}", key);
    ItemUpdateKafkaDto itemUpdateKafkaDto = objectMapper.readValue(itemJson,
        ItemUpdateKafkaDto.class);

    CartItem cartItem = CartItem.builder()
        .id(itemUpdateKafkaDto.getId())
        .name(itemUpdateKafkaDto.getName())
        .price(itemUpdateKafkaDto.getPrice())
        .build();
    cartService.replaceCartItem(cartItem);
  }

}

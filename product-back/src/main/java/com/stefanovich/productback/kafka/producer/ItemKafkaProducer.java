package com.stefanovich.productback.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemUpdateKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ItemKafkaProducer {

  private final KafkaTemplate<String, ItemUpdateKafkaDto> kafkaTemplate;
  private final ObjectMapper objectMapper;
  @Value("${kafka.topic.name.items-update}")
  private String itemsUpdateTopic;

  @Async
  @SneakyThrows
  public void sendUpdate(Item item) {
    log.info("sent ItemUpdate to kafka with itemId = {}", item.getId());
    log.debug("item = {}", item);
    ItemUpdateKafkaDto dto = ItemUpdateKafkaDto.builder()
        .id(item.getId().toString())
        .name(item.getName())
        .price(item.getPrice())
        .build();
    kafkaTemplate.send(itemsUpdateTopic, dto.getId(), dto);
  }
}

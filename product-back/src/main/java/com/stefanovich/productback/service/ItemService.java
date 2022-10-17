package com.stefanovich.productback.service;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.repository.ItemRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public void saveItem(ItemSaveDto itemSaveDto) {
    itemRepository.insert(Item.builder()
        .name(itemSaveDto.getName())
        .price(itemSaveDto.getPrice()).build());
  }
 @Value("${spring.data.mongodb.username}")
 public String test;


  @PostConstruct
  public void init(){
    System.out.println("test = " + test);

  }
}

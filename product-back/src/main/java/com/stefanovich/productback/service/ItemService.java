package com.stefanovich.productback.service;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
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

}

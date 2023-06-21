package com.stefanovich.productback.api;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemReplaceDto;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.model.dto.PageDto;
import com.stefanovich.productback.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "api/v1/items")
@RequiredArgsConstructor
public class ItemApi {

  private final ItemService itemService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addItem(@RequestBody ItemSaveDto itemSaveDto) {

    itemService.saveItem(itemSaveDto);
  }

  @GetMapping("{id}")
  public Item getItem(@PathVariable ObjectId id) {
    return itemService.getById(id);
  }

  @GetMapping
  public PageDto<Item> getAllItems(
      ItemSearchFilterDto itemSearchFilter
  ) {
    return itemService.getAllItemsByFilters(itemSearchFilter);
  }

  @PutMapping
  public ItemReplaceDto replace(@RequestBody ItemReplaceDto itemReplaceDto) {
    Item item = Item.builder()
        .id(itemReplaceDto.getId())
        .name(itemReplaceDto.getName())
        .price(itemReplaceDto.getPrice())
        .category(itemReplaceDto.getCategory()).build();
    itemService.replace(item);

    return ItemReplaceDto.builder()
        .id(item.getId())
        .name(item.getName())
        .price(item.getPrice())
        .category(item.getCategory())
        .build();
  }

}
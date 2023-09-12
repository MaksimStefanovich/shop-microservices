package com.stefanovich.productback.api;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemReplaceDto;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.model.dto.PageDto;
import com.stefanovich.productback.service.ItemService;
import com.stefanovich.productback.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "api/v1/items")
@RequiredArgsConstructor
public class ItemApi {

  private final ItemService itemService;
  private final JwtService jwtService;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addItem(@RequestBody ItemSaveDto itemSaveDto, @RequestHeader("Authorization") String jwt) {
    jwtService.validate(jwt);
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

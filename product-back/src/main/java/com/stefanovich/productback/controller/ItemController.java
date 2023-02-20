package com.stefanovich.productback.controller;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/items")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addItem(@RequestBody ItemSaveDto itemSaveDto) {

    itemService.saveItem(itemSaveDto);
  }

//  @GetMapping("{id}")
//  public Item getItem(@PathVariable ObjectId id) throws Exception {
//    return itemRepository.findById(id).orElseThrow(()
//        -> new Exception("not found item wit id " + id));
//  }

  @GetMapping
  public Page<Item> getAllItems(
      ItemSearchFilterDto itemSearchFilter,
      @RequestParam(required = false, defaultValue = "10") int size,
      @RequestParam(required = false, defaultValue = "0") int page
  ) {
    return itemService.getAllItemsByFilters(itemSearchFilter, size, page);
  }
}

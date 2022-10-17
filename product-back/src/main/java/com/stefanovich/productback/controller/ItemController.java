package com.stefanovich.productback.controller;

import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSaveDto;
import com.stefanovich.productback.repository.ItemRepository;
import com.stefanovich.productback.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody ItemSaveDto itemSaveDto) {

        itemService.saveItem(itemSaveDto);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("{id}")
    public Item getItem(@PathVariable ObjectId id) throws Exception {
        return itemRepository.findById(id).orElseThrow(()
                -> new Exception("not found item wit id " + id));
    }
}

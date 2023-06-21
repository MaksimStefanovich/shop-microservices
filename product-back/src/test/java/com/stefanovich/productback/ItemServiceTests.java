//package com.stefanovich.productback;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
//
//import com.stefanovich.productback.container.MongoDBTestContainer;
//import com.stefanovich.productback.model.Item;
//import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
//import com.stefanovich.productback.repository.mongo.ItemRepository;
//import com.stefanovich.productback.service.ItemService;
//import java.math.BigDecimal;
//import java.util.List;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest
//public class ItemServiceTests implements MongoDBTestContainer {
//
//  @Autowired
//  ItemService itemService;
//  @Autowired
//  ItemRepository itemRepository;
//
//  Item item1, item2, item3;
//  List<Item> items;
//  TODO change test for working with cache and without(mock)
//  @BeforeEach
//  void prepare() {
//    item1 = itemRepository.save(Item.builder()
//        .price(BigDecimal.valueOf(30))
//        .name("Phone")
//        .build());
//
//    item2 = itemRepository.save(Item.builder()
//        .price(BigDecimal.valueOf(30))
//        .name("TV")
//        .build());
//
//    item3 = itemRepository.save(Item.builder()
//        .price(BigDecimal.valueOf(500))
//        .name("car")
//        .build());
//
//    items = List.of(item1, item2, item3);
//  }
//
//  @AfterEach
//  void clear() {
//    itemRepository.deleteAll();
//  }
//
//  @Test
//  void getAllItemsByFilters_whenNoFilters_thenGetAllItems() {
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    List<Item> actualItems = itemService.getAllItemsByFilters(itemSearchFilterDto);
//    assertThat(actualItems).hasSize(items.size());
//  }
//
//  @Test
//  void getAllItemsByFilters_whenFirstFilter_thenGetMatchItems() {
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    itemSearchFilterDto.setName("Phone");
//    List<Item> actualItems = itemService.getAllItemsByFilters(itemSearchFilterDto);
//    assertThat(actualItems).containsExactly(item1);
//  }
//
//  @Test
//  void getAllItemsByFilters_whenSecondFilter_thenGetMatchItems() {
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    itemSearchFilterDto.setPrice(BigDecimal.valueOf(30));
//    List<Item> actualItems = itemService.getAllItemsByFilters(itemSearchFilterDto);
//    assertThat(actualItems).containsExactly(item1, item2);
//  }
//
//  @Test
//  void getAllItemsByFilters_whenBothFilters_thenGetMatchItems() {
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    itemSearchFilterDto.setPrice(BigDecimal.valueOf(30));
//    itemSearchFilterDto.setName("TV");
//    List<Item> actualItems = itemService.getAllItemsByFilters(itemSearchFilterDto);
//    assertThat(actualItems).containsExactly(item2);
//  }
//
//  @Test
//  void getAllItemsByFilters_whenNull_thenGetException() {
//    assertThatIllegalArgumentException()
//        .isThrownBy(() -> itemService.getAllItemsByFilters(null));
//  }
//}
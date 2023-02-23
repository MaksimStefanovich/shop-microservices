//package com.stefanovich.productback;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.stefanovich.productback.controller.ItemController;
//import com.stefanovich.productback.model.Item;
//import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
//import com.stefanovich.productback.service.ItemService;
//import java.math.BigDecimal;
//import java.util.List;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//
//@WebMvcTest(ItemController.class)
//public class ItemControllerTests {
//
//  @Autowired
//  MockMvc mvc;
//
//  @MockBean
//  ItemService itemService;
//  int size = 10;
//  int page = 0;
//  static Item item1, item2, item3;
//  static Page<Item> items;
//
//  @BeforeAll
//  static void prepare() {
//    item1 = Item.builder()
//        .price(BigDecimal.valueOf(30))
//        .name("Phone")
//        .build();
//
//    item2 = Item.builder()
//        .price(BigDecimal.valueOf(30))
//        .name("TV")
//        .build();
//
//    item3 = Item.builder()
//        .price(BigDecimal.valueOf(500))
//        .name("car")
//        .build();
//    items = new PageImpl<>(List.of(item1, item2, item3));
//
//  }
//
//  @Test
//  @SneakyThrows
//  void getAllItems_whenNoFilters_thenGetAllItems() {
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    when(itemService.getAllItemsByFilters(itemSearchFilterDto)).thenReturn(items);
//
//    mvc.perform(get("/items")).andExpect(status().isOk())
//        .andExpect(jsonPath("$.content.size()").value(3));
//  }
//
//  @Test
//  @SneakyThrows
//  void getAllItems_whenFirstFilter_thenGetMatchItems() {
//    String itemName = "Phone";
//
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    itemSearchFilterDto.setName(itemName);
//
//    Page<Item> itemPage = new PageImpl<>(List.of(item1));
//
//    when(itemService.getAllItemsByFilters(itemSearchFilterDto)).thenReturn(itemPage);
//
//    mvc.perform(
//            get("/items")
//                .param("name", itemName)
//                .param("page", String.valueOf(page))
//                .param("size", String.valueOf(size))
//        ).andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.content.size()").value(1))
//        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Phone"));
//  }
//
//  @Test
//  @SneakyThrows
//  void getAllItems_whenSecondFilter_thenGetMatchItems() {
//    BigDecimal itemPrice = BigDecimal.valueOf(30);
//
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    itemSearchFilterDto.setPrice(itemPrice);
//
//    Page<Item> itemPage = new PageImpl<>(List.of(item2));
//
//    when(itemService.getAllItemsByFilters(itemSearchFilterDto)).thenReturn(itemPage);
//
//    mvc.perform(
//            get("/items")
//                .param("price", String.valueOf(itemPrice))
//                .param("page", String.valueOf(page))
//                .param("size", String.valueOf(size))
//        ).andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.content.size()").value(1))
//        .andExpect(jsonPath("$.content[0].name").value("TV"))
//        .andDo(print());
//  }
//
//  @Test
//  @SneakyThrows
//  void getAllItems_whenBothFilter_thenGetMatchItems() {
//    BigDecimal itemPrice = BigDecimal.valueOf(30);
//    String itemName = "TV";
//
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    itemSearchFilterDto.setPrice(itemPrice);
//    itemSearchFilterDto.setName(itemName);
//
//    Page<Item> itemPage = new PageImpl<>(List.of(item2));
//
//    when(itemService.getAllItemsByFilters(itemSearchFilterDto)).thenReturn(itemPage);
//
//    mvc.perform(
//            get("/items")
//                .param("name", itemName)
//                .param("price", String.valueOf(itemPrice))
//                .param("page", String.valueOf(page))
//                .param("size", String.valueOf(size))
//        ).andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.content.size()").value(1))
//        .andExpect(jsonPath("$.content[0].name").value("TV"))
//        .andExpect(jsonPath("$.content[0].price").value(30))
//        .andDo(print());
//
//  }
//
//  @Test
//  @SneakyThrows
//  void getAllPages_whenNoFilters_thenGetMatchItems() {
//    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
//    Page<Item> itemPage = new PageImpl<>(List.of(item1));
//    when(itemService.getAllItemsByFilters(itemSearchFilterDto)).thenReturn(itemPage);
//
//
//    mvc.perform(get("/items")
//            .param("page", String.valueOf(0))
//            .param("size", String.valueOf(1)))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.content.size()").value(1))
//        .andExpect(jsonPath("$.totalPages").value(1))
//        .andDo(print());
//  }
//}

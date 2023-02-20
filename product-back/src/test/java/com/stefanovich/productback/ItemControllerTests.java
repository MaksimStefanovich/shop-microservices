package com.stefanovich.productback;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.stefanovich.productback.controller.ItemController;
import com.stefanovich.productback.model.Item;
import com.stefanovich.productback.model.dto.ItemSearchFilterDto;
import com.stefanovich.productback.service.ItemService;
import java.math.BigDecimal;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(ItemController.class)
public class ItemControllerTests {

  @Autowired
  MockMvc mvc;

  @MockBean
  ItemService itemService;
  int size = 10;
  int page = 0;
  static Item item1, item2, item3;
  static Page<Item> items;

  @BeforeAll
  static void prepare() {
    item1 = Item.builder()
        .price(BigDecimal.valueOf(30))
        .name("Phone")
        .build();

    item2 = Item.builder()
        .price(BigDecimal.valueOf(30))
        .name("TV")
        .build();

    item3 = Item.builder()
        .price(BigDecimal.valueOf(500))
        .name("car")
        .build();
    items = new PageImpl<>(List.of(item1, item2, item3));

  }

  @Test
  @SneakyThrows
  void getAllItems_whenNoFilters_thenGetAllItems() {
    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
    when(itemService.getAllItemsByFilters(itemSearchFilterDto, 10, 0)).thenReturn(items);

    mvc.perform(get("/items")).andExpect(status().isOk())
        .andExpect(jsonPath("$.content.size()").value(3));
  }

  @Test
  @SneakyThrows
  void getAllItems_whenFirstFilter_thenGetMatchItems() {
    String itemName = "Phone";

    ItemSearchFilterDto itemSearchFilterDto = new ItemSearchFilterDto();
    itemSearchFilterDto.setName(itemName);

    Page<Item> itemPage = new PageImpl<>(List.of(item1));

    when(itemService.getAllItemsByFilters(itemSearchFilterDto, size, page)).thenReturn(itemPage);

    mvc.perform(
            get("/items")
                .param("name", itemName)
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
        ).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content.size()").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Phone"));
  }
}

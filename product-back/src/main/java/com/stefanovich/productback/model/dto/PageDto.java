package com.stefanovich.productback.model.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class PageDto<T> {

  List<T> content;
  int currentPage;
  int currentSize;
  int totalPages;
  long totalElements;

  public PageDto(Page<T> page) {
    this.content = page.getContent();
    this.currentPage = page.getNumber();
    this.currentSize = page.getSize();
    this.totalPages = page.getTotalPages();
    this.totalElements = page.getTotalElements();
  }
}

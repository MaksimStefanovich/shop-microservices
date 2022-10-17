package com.stefanovich.productback.model;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class Item {
  @Id
  ObjectId id;
  String name;
  BigDecimal price;
}


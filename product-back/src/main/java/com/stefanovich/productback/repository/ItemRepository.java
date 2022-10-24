package com.stefanovich.productback.repository;

import com.stefanovich.productback.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ItemRepository extends MongoRepository<Item, ObjectId> {

  @Query("{ 'name' : ?0,  $or : [ { $where: '?1 == null' } , { price : ?1 } ] }")
  List<Item> findByFilters(String name, BigDecimal price);
}

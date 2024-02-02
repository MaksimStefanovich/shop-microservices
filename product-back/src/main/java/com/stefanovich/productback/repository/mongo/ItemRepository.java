package com.stefanovich.productback.repository.mongo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.stefanovich.productback.model.Item;
import java.math.BigDecimal;
import java.util.List;

import com.stefanovich.productback.model.QItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends MongoRepository<Item, ObjectId>,
    QuerydslPredicateExecutor<Item> {

  static BooleanExpression nameLikeIgnoreCase(String name) {
    return QItem.item.name.likeIgnoreCase(name);
  }

  static BooleanExpression priceEq(BigDecimal price) {
    return QItem.item.price.eq(price);
  }

  static BooleanExpression categoriesIn(List<String> categories){
    return QItem.item.category.in(categories);
  }

  static BooleanExpression categoryEq(String category){
    return  QItem.item.category.eq(category);
  }
}

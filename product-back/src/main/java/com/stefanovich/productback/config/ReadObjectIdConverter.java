package com.stefanovich.productback.config;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class ReadObjectIdConverter implements Converter<byte[], ObjectId> {

  @Override
  public ObjectId convert(byte[] source) {
    return new ObjectId(source);
  }
}

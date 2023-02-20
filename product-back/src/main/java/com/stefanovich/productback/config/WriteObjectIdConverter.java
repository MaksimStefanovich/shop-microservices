package com.stefanovich.productback.config;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class WriteObjectIdConverter implements Converter<ObjectId, byte[]> {

  @Override
  public byte[] convert(ObjectId source) {
    return source.toByteArray();
  }
}

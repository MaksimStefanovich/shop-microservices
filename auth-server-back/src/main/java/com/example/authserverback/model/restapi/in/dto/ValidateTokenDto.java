package com.example.authserverback.model.restapi.in.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
public class ValidateTokenDto {
    String jwt;
}

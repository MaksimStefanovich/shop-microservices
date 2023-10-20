package com.stefanovich.productback.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class JwkDto {
    /**
     * key type
     */
    String kty;
    /**
     * exponent
     */
    String e;
    /**
     * use for signature or encryption
     */
    String use;
    /**
     * ID
     */
    UUID kid;
    /**
     * issuer at
     */
    Instant iat;
    /**
     * modulus
     */
    String n;
}

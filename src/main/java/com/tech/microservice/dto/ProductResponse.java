package com.tech.microservice.dto;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String description, BigDecimal price) {
    // This record class is used to encapsulate the product response data
    // It automatically generates getters, equals, hashCode, and toString methods
}

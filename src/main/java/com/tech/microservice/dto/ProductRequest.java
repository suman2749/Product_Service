package com.tech.microservice.dto;

import lombok.Data;

import java.math.BigDecimal;

public record ProductRequest(
        String id,
        String name,
        String description,
        BigDecimal price
) {
    // This record class is used to encapsulate the product request data
    // It automatically generates getters, equals, hashCode, and toString methods) {
}

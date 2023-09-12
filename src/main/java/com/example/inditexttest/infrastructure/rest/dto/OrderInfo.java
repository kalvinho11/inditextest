package com.example.inditexttest.infrastructure.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@Valid
public class OrderInfo {

    @NotNull(message = "productId cannot be null")
    private Integer productId;

    @NotNull(message = "brandId cannot be null")
    private Integer brandId;

    @NotNull(message = "timestamp cannot be null")
    private Timestamp timestamp;
}

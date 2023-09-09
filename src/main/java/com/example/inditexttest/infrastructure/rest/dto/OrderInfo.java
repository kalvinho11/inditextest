package com.example.inditexttest.infrastructure.rest.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderInfo {

    private Integer productId;

    private Integer brandId;

    private Timestamp timestamp;
}

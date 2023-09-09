package com.example.inditexttest.infrastructure.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PriceDto {

    private Integer productId;

    private Integer brandId;

    private Integer priceList;

    private Date endDate;

    private Date startDate;

    private Float price;

}

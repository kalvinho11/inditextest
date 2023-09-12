package com.example.inditexttest.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {

    private Integer productId;

    private Integer brandId;

    private Integer priceList;

    private LocalDateTime endDate;

    private LocalDateTime startDate;

    private Float price;

}

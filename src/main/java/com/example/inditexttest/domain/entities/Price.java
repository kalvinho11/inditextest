package com.example.inditexttest.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "PRICES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    private Integer id;

    private Integer brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceList;

    private Integer productId;

    private Integer priority;

    private Float price;

    private String curr;
}

package com.example.inditexttest.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "PRICES")
@Data
public class Price {

    @Id
    private Integer id;

    private Integer brandId;

    private Date startDate;

    private Date endDate;

    private Integer priceList;

    private Integer productId;

    private Integer priority;

    private Float price;

    private String curr;
}

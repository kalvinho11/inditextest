package com.example.inditexttest.infrastructure.rest.controller;

import com.example.inditexttest.application.service.prices.impl.PricesServiceImpl;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PricesController {

    @Autowired
    private PricesServiceImpl pricesService;

    @PostMapping
    private ResponseEntity<Object> findCorrectPrice(final OrderInfo orderInfo) {

        pricesService.obtainPrice(orderInfo);

        return ResponseEntity.ok(new Object());

    }



}

package com.example.inditexttest.infrastructure.rest.controller;

import com.example.inditexttest.application.service.prices.impl.PricesServiceImpl;
import com.example.inditexttest.domain.entities.Price;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/prices")
public class PricesController {

    @Autowired
    private PricesServiceImpl pricesService;

    @PostMapping("/findCorrect")
    private ResponseEntity<Object> findCorrectPrice(@RequestBody final OrderInfo orderInfo) {

        PriceDto price = pricesService.obtainPrice(orderInfo);

        return ResponseEntity.ok(price);

    }



}

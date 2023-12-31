package com.example.inditexttest.infrastructure.rest.controller;

import com.example.inditexttest.application.service.prices.exception.PriceNotFoundException;
import com.example.inditexttest.application.service.prices.exception.ProductNotFoundException;
import com.example.inditexttest.application.service.prices.impl.PricesServiceImpl;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping("/find")
    public ResponseEntity findCorrectPrice(@Valid @RequestBody final OrderInfo orderInfo) {

        ResponseEntity response;

        try {
            final PriceDto price = pricesService.obtainPrice(orderInfo);
            response = ResponseEntity.ok(price);
        } catch (PriceNotFoundException e) {
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON)
                    .body(e.getMessage());
        } catch (ProductNotFoundException e) {
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON)
                    .body(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.internalServerError().body("An error occurred in server.");
        }

        return response;

    }



}

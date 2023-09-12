package com.example.inditexttest.application.service.prices;

import com.example.inditexttest.application.service.prices.exception.PriceNotFoundException;
import com.example.inditexttest.application.service.prices.exception.ProductNotFoundException;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;

public interface PricesService {

    PriceDto obtainPrice(final OrderInfo orderInfo) throws PriceNotFoundException, ProductNotFoundException;
}

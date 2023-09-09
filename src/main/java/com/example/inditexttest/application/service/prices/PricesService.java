package com.example.inditexttest.application.service.prices;

import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;

public interface PricesService {

    void obtainPrice(final OrderInfo orderInfo);
}

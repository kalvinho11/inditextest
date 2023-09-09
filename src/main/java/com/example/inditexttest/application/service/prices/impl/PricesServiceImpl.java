package com.example.inditexttest.application.service.prices.impl;

import com.example.inditexttest.application.service.prices.PricesService;
import com.example.inditexttest.domain.entities.PriceEntity;
import com.example.inditexttest.domain.repository.PricesRepository;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesRepository pricesRepository;

    @Override
    public void obtainPrice(final OrderInfo orderInfo) {

        Collection<PriceEntity> priceEntity = findPrice(orderInfo);

        if (priceEntity.isEmpty()) {

        }

    }

    private Collection<PriceEntity> findPrice(final OrderInfo orderInfo) {
        return pricesRepository.findCorrectPriceInDate(orderInfo.getDate().toLocalDateTime(), orderInfo.getBrandId(),
                orderInfo.getProductId());
    }
}

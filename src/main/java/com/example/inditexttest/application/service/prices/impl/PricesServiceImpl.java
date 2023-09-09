package com.example.inditexttest.application.service.prices.impl;

import com.example.inditexttest.application.service.prices.PricesService;
import com.example.inditexttest.domain.entities.Price;
import com.example.inditexttest.domain.repository.PricesRepository;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import com.example.inditexttest.infrastructure.rest.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;

@Service
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceDto obtainPrice(final OrderInfo orderInfo) {

        Collection<Price> priceEntity = findInDatabase(orderInfo);

        return priceMapper.toDto(priceEntity.size() > 1 ? obtainPriorityPrice(priceEntity) : priceEntity.stream()
                .findFirst().get());

    }

    private Collection<Price> findInDatabase(final OrderInfo orderInfo) {
        return pricesRepository.findCorrectPriceInDate(orderInfo.getTimestamp(), orderInfo.getBrandId(),
                orderInfo.getProductId());
    }

    private Price obtainPriorityPrice(final Collection<Price> prices) {
        return prices.stream().max(Comparator.comparingInt(Price::getPriority)).get();
    }
}

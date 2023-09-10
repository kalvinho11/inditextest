package com.example.inditexttest.application.service.prices.impl;

import com.example.inditexttest.application.service.prices.PricesService;
import com.example.inditexttest.application.service.prices.exception.PriceNotFoundException;
import com.example.inditexttest.domain.entities.Price;
import com.example.inditexttest.domain.repository.PricesRepository;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import com.example.inditexttest.infrastructure.rest.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceDto obtainPrice(final OrderInfo orderInfo) throws PriceNotFoundException {

        List<Price> priceEntity = findInDatabase(orderInfo);

        if (priceEntity.isEmpty()) {
            throw new PriceNotFoundException("Not found price for given parameters.");
        }

        return priceMapper.toDto(priceEntity.size() > 1 ? obtainPriorityPrice(priceEntity) : priceEntity.stream()
                .findFirst().get());

    }

    private List<Price> findInDatabase(final OrderInfo orderInfo) {
        return pricesRepository.findCorrectPriceInDate(orderInfo.getTimestamp(), orderInfo.getBrandId(),
                orderInfo.getProductId());
    }

    private Price obtainPriorityPrice(final Collection<Price> prices) {
        return prices.stream().max(Comparator.comparingInt(Price::getPriority)).get();
    }
}

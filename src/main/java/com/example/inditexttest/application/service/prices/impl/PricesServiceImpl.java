package com.example.inditexttest.application.service.prices.impl;

import com.example.inditexttest.application.service.prices.PricesService;
import com.example.inditexttest.application.service.prices.exception.PriceNotFoundException;
import com.example.inditexttest.application.service.prices.exception.ProductNotFoundException;
import com.example.inditexttest.domain.entities.Price;
import com.example.inditexttest.domain.repository.PricesRepository;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import com.example.inditexttest.infrastructure.rest.mapper.PriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceDto obtainPrice(final OrderInfo orderInfo) throws PriceNotFoundException, ProductNotFoundException {

        if (!productIdExists(orderInfo.getProductId())) {
            log.debug("Not existing product with productId: {}", orderInfo.getProductId());
            throw new ProductNotFoundException(orderInfo.getProductId());
        }

        final List<Price> pricesList = findInDatabase(orderInfo);

        if (pricesList.isEmpty()) {
            log.debug("Not found price for: {}", orderInfo);
            throw new PriceNotFoundException();
        }

        log.debug("Prices found from database for given parameters: {}", pricesList);

        return priceMapper.toDto(pricesList.size() > 1 ? obtainPriorityPriceFromList(pricesList) : pricesList.stream()
                .findFirst().get());

    }

    private Boolean productIdExists(Integer productId) {
        return pricesRepository.countPriceByProductId(productId) != 0;
    }

    private List<Price> findInDatabase(final OrderInfo orderInfo) {
        log.trace("Query for prices in DB for date {}, brandId {} and productId {}", orderInfo.getTimestamp(),
                orderInfo.getBrandId(), orderInfo.getProductId());
        return pricesRepository.findCorrectPriceInDate(orderInfo.getTimestamp(), orderInfo.getBrandId(),
                orderInfo.getProductId());
    }

    private Price obtainPriorityPriceFromList(final List<Price> prices) {
        return prices.stream().max(Comparator.comparingInt(Price::getPriority)).get();
    }
}

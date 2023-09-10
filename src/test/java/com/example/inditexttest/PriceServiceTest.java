package com.example.inditexttest;

import com.example.inditexttest.application.service.prices.PricesService;
import com.example.inditexttest.application.service.prices.exception.PriceNotFoundException;
import com.example.inditexttest.domain.entities.Price;
import com.example.inditexttest.domain.repository.PricesRepository;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import com.example.inditexttest.infrastructure.rest.mapper.PriceMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    @InjectMocks
    private PricesService pricesService;

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private PriceMapper priceMapper;

    @Test
    public void itOnlyExistsAApplicablePrice() throws PriceNotFoundException {
        final OrderInfo orderInfo = OrderInfo.builder().
                productId(3229).
                brandId(1).
                timestamp(obtainTimestampFromDate("2020-06-14 10.00.00")).build();

        final Price mockedPrice = getMockedPrices().stream().findFirst().get();

        final PriceDto expectedPrice = PriceDto.builder()
                .startDate(mockedPrice.getStartDate())
                .endDate(mockedPrice.getEndDate())
                .priceList(mockedPrice.getPriceList())
                .brandId(mockedPrice.getBrandId())
                .productId(mockedPrice.getProductId())
                .price(mockedPrice.getPrice()).build();


        when(pricesRepository.findCorrectPriceInDate(orderInfo.getTimestamp(), orderInfo.getBrandId(),
                orderInfo.getProductId())).thenReturn(List.of(mockedPrice));

        when(priceMapper.toDto(mockedPrice)).thenReturn(expectedPrice);

        PriceDto price = pricesService.obtainPrice(orderInfo);

        assertThat(price).isEqualTo(expectedPrice);

    }

    @Test
    public void itShouldPrioritizeOnePrice() throws PriceNotFoundException {
        final OrderInfo orderInfo = OrderInfo.builder().
                productId(3229).
                brandId(1).
                timestamp(obtainTimestampFromDate("2020-06-14 10.00.00")).build();

        final Price mockedPrice = getMockedPrices().stream().findFirst().get();

        final PriceDto expectedPrice = PriceDto.builder()
                .startDate(mockedPrice.getStartDate())
                .endDate(mockedPrice.getEndDate())
                .priceList(mockedPrice.getPriceList())
                .brandId(mockedPrice.getBrandId())
                .productId(mockedPrice.getProductId())
                .price(mockedPrice.getPrice()).build();


        when(pricesRepository.findCorrectPriceInDate(orderInfo.getTimestamp(), orderInfo.getBrandId(),
                orderInfo.getProductId())).thenReturn(List.of(mockedPrice));

        when(priceMapper.toDto(mockedPrice)).thenReturn(expectedPrice);

        PriceDto price = pricesService.obtainPrice(orderInfo);

        assertThat(price).isEqualTo(expectedPrice);

    }

    private Timestamp obtainTimestampFromDate(final String date) {

        final LocalDateTime requestDate = LocalDateTime.parse(date, formatter);

        return Timestamp.from(requestDate.toInstant(ZoneOffset.of("+02:00")));
    }

    private List<Price> getMockedPrices() {
        final Price price1 = Price.builder()
                .startDate(LocalDateTime.parse("2020-06-14 09.00.00"))
                .endDate((LocalDateTime.parse("2020-06-17 10.00.00")))
                .brandId(1)
                .priority(1)
                .productId(3655)
                .priceList(1)
                .price(33.0F)
                .curr("EUR").build();

        final Price price2 = Price.builder()
                .startDate(LocalDateTime.parse("2020-06-14 09.00.00"))
                .endDate((LocalDateTime.parse("2020-06-15 10.00.00")))
                .brandId(1)
                .priority(1)
                .productId(3655)
                .priceList(1)
                .price(25.0F)
                .curr("EUR").build();

        return Arrays.asList(price1, price2);
    }



}

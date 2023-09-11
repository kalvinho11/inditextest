package com.example.inditexttest;

import com.example.inditexttest.domain.entities.Price;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import com.example.inditexttest.infrastructure.rest.mapper.PriceMapper;
import com.example.inditexttest.infrastructure.rest.mapper.PriceMapperImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceMapperTests {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    private PriceMapper priceMapper = new PriceMapperImpl();

    @Test
    public void shouldMapPrice() {
        final Price price = Price.builder()
                .startDate(LocalDateTime.parse("2020-06-14 09.00.00", formatter))
                .endDate((LocalDateTime.parse("2020-06-17 10.00.00", formatter)))
                .brandId(1)
                .priority(1)
                .productId(3655)
                .priceList(1)
                .price(33.0F)
                .curr("EUR").build();

        final PriceDto expectedPrice = PriceDto.builder()
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .price(price.getPrice()).build();

        assertThat(priceMapper.toDto(price)).isEqualTo(expectedPrice);

    }

    @Test
    public void shouldNotMapPrice() {
        final Price price = Price.builder()
                .startDate(LocalDateTime.parse("2020-06-14 09.00.00", formatter))
                .endDate((LocalDateTime.parse("2020-06-17 10.00.00", formatter)))
                .brandId(1)
                .priority(1)
                .productId(3655)
                .priceList(1)
                .price(33.0F)
                .curr("EUR").build();

        final PriceDto wrongPrice = PriceDto.builder()
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .brandId(2)
                .productId(100)
                .price(0F).build();

        assertThat(priceMapper.toDto(price)).isNotEqualTo(wrongPrice);

    }


}

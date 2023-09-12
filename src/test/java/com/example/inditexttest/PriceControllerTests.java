package com.example.inditexttest;

import com.example.inditexttest.application.service.prices.exception.PriceNotFoundException;
import com.example.inditexttest.application.service.prices.exception.ProductNotFoundException;
import com.example.inditexttest.application.service.prices.impl.PricesServiceImpl;
import com.example.inditexttest.infrastructure.rest.controller.PricesController;
import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTests {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    @InjectMocks
    private PricesController pricesController;

    @Mock
    private PricesServiceImpl pricesService;

    @Test
    public void happyPath() throws PriceNotFoundException, ProductNotFoundException {
        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-14 10.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        final PriceDto price = PriceDto.builder()
                .productId(35455)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14 00.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23.59.59", formatter))
                .priceList(1)
                .price(35.50F).build();

        when(pricesService.obtainPrice(orderRequest)).thenReturn(price);

        ResponseEntity response = pricesController.findCorrectPrice(orderRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(price);
    }

    @Test
    public void shouldReturnNotFoundPriceMessage() throws PriceNotFoundException, ProductNotFoundException {
        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-14 10.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        when(pricesService.obtainPrice(orderRequest)).thenThrow(new PriceNotFoundException());

        ResponseEntity response = pricesController.findCorrectPrice(orderRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isEqualTo("Not found price for given parameters.");
    }

    @Test
    public void shouldReturnProductNotFoundMessage() throws PriceNotFoundException, ProductNotFoundException {
        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-14 10.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        when(pricesService.obtainPrice(orderRequest)).thenThrow(new ProductNotFoundException(orderRequest
                .getProductId()));

        ResponseEntity response = pricesController.findCorrectPrice(orderRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isEqualTo("Not found product with productId " + orderRequest
                .getProductId());
    }

    @Test
    public void shouldReturn500() throws PriceNotFoundException, ProductNotFoundException {
        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-14 10.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        when(pricesService.obtainPrice(orderRequest)).thenThrow(new RuntimeException());

        ResponseEntity response = pricesController.findCorrectPrice(orderRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("An error occurred in server.");
    }

    private Timestamp obtainTimestampFromDate(final String date) {

        final LocalDateTime requestDate = LocalDateTime.parse(date, formatter);

        return Timestamp.from(requestDate.toInstant(ZoneOffset.of("+02:00")));
    }
}

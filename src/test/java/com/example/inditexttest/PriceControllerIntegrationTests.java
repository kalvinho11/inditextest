package com.example.inditexttest;

import com.example.inditexttest.infrastructure.rest.dto.OrderInfo;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIntegrationTests {

    private final ObjectMapper mapper = new ObjectMapper();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setJavaTimeModule() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void requestAt10onDay14() throws Exception {

        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-14 10.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        final PriceDto expectedPrice = PriceDto.builder()
                .productId(35455)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14 00.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23.59.59", formatter))
                .priceList(1)
                .price(35.50F).build();

        final MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertThat(mapper.readValue(response.getResponse().getContentAsString(), PriceDto.class))
                .isEqualTo(expectedPrice);

    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void requestAt16onDay14() throws Exception {

        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-14 16.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        final PriceDto expectedPrice = PriceDto.builder()
                .productId(35455)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14 15.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-06-14 18.30.00", formatter))
                .priceList(2)
                .price(25.45F).build();

        final MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertThat(mapper.readValue(response.getResponse().getContentAsString(), PriceDto.class))
                .isEqualTo(expectedPrice);

    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    public void requestAt21onDay14() throws Exception {

        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-14 21.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        final PriceDto expectedPrice = PriceDto.builder()
                .productId(35455)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-14 00.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23.59.59", formatter))
                .priceList(1)
                .price(35.50F).build();

        final MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertThat(mapper.readValue(response.getResponse().getContentAsString(), PriceDto.class))
                .isEqualTo(expectedPrice);

    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    public void requestAt10onDay15() throws Exception {

        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-15 10.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        final PriceDto expectedPrice = PriceDto.builder()
                .productId(35455)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-15 00.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-06-15 11.00.00", formatter))
                .priceList(3)
                .price(30.50F).build();

        final MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertThat(mapper.readValue(response.getResponse().getContentAsString(), PriceDto.class))
                .isEqualTo(expectedPrice);

    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    public void requestAt21onDay16() throws Exception {

        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-16 21.00.00"))
                .brandId(1)
                .productId(35455)
                .build();

        final PriceDto expectedPrice = PriceDto.builder()
                .productId(35455)
                .brandId(1)
                .startDate(LocalDateTime.parse("2020-06-15 16.00.00", formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23.59.59", formatter))
                .priceList(4)
                .price(38.95F).build();

        final MvcResult response = mockMvc
                .perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertThat(mapper.readValue(response.getResponse().getContentAsString(), PriceDto.class))
                .isEqualTo(expectedPrice);

    }

    @Test
    public void orderWithoutBrandIdShouldReturn400() throws Exception {

        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-16 21.00.00"))
                .productId(35455)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void orderWithoutProductIdShouldReturn400() throws Exception {

        final OrderInfo orderRequest = OrderInfo.builder()
                .timestamp(obtainTimestampFromDate("2020-06-16 21.00.00"))
                .brandId(1)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void orderWithoutTimestampShouldReturn400() throws Exception {

        final OrderInfo orderRequest = OrderInfo.builder()
                .brandId(1)
                .productId(35455)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void orderInfoIsNullReturn400() throws Exception {

        final OrderInfo orderRequest = null;

        mockMvc.perform(MockMvcRequestBuilders.post("/prices/find")
                        .content(mapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    private Timestamp obtainTimestampFromDate(final String date) {

        final LocalDateTime requestDate = LocalDateTime.parse(date, formatter);

        return Timestamp.from(requestDate.toInstant(ZoneOffset.of("+02:00")));
    }
}

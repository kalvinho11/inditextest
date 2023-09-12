package com.example.inditexttest.infrastructure.rest.mapper;

import com.example.inditexttest.domain.entities.Price;
import com.example.inditexttest.infrastructure.rest.dto.PriceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceDto toDto(Price price);


}

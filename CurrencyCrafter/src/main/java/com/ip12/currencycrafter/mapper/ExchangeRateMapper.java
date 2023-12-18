package com.ip12.currencycrafter.mapper;

import com.ip12.currencycrafter.dto.ExchangeRateDto;
import com.ip12.currencycrafter.entity.ExchangeRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {

    ExchangeRateDto toDTO(ExchangeRate exchangeRate);

    ExchangeRate toEntity(ExchangeRateDto exchangeRateDto);
}

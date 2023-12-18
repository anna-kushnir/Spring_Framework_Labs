package com.ip12.currencycrafter.mapper;

import com.ip12.currencycrafter.dto.CurrencyRateDto;
import com.ip12.currencycrafter.entity.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyRateDto toDTO(Currency currency);

    Currency toEntity(CurrencyRateDto currencyRateDto);
}

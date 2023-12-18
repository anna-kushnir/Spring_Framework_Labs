package com.ip12.currencycrafter.service;

import com.ip12.currencycrafter.dto.CurrencyRateDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CurrencyService {

    void deleteById(long id);

    CurrencyRateDto getById(long id);

    List<CurrencyRateDto> getAll();

    CurrencyRateDto update(CurrencyRateDto currency);

    CurrencyRateDto save(CurrencyRateDto currency);

    Map<LocalDate, BigDecimal> getAllExchangeRateInRange(Long firstCurrencyId, Long secondCurrencyId, LocalDate startDate, LocalDate endDate);

    List<CurrencyRateDto> getAllWithTodayRate();
}

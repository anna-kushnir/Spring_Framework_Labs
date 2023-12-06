package com.ip12.currencycrafter.service;

import com.ip12.currencycrafter.entity.Currency;
import com.ip12.currencycrafter.entity.ExchangeRate;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateService {

    void deleteById(long id);

    ExchangeRate getById(long id);

    List<ExchangeRate> getAll();

    List<ExchangeRate> getAllByCurrency(Currency currency);

    List<ExchangeRate> getAllByCurrency(long currencyId);

    List<ExchangeRate> getAllByDate(LocalDate date);

    ExchangeRate update(ExchangeRate exchangeRate);

    ExchangeRate save(ExchangeRate exchangeRate);


}

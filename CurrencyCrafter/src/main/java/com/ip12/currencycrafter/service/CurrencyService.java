package com.ip12.currencycrafter.service;

import com.ip12.currencycrafter.entity.Currency;

import java.util.List;

public interface CurrencyService {

    void deleteById(long id);

    Currency getById(long id);

    List<Currency> getAll();

    Currency update(Currency currency);

    Currency save(Currency currency);
}

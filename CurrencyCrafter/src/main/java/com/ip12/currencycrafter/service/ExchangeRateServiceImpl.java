package com.ip12.currencycrafter.service;

import com.ip12.currencycrafter.entity.Currency;
import com.ip12.currencycrafter.entity.ExchangeRate;
import com.ip12.currencycrafter.exception.ResourceNotFoundException;
import com.ip12.currencycrafter.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public void deleteById(long id) {
        exchangeRateRepository.deleteById(id);
    }

    @Override
    public ExchangeRate getById(long id) {
        return exchangeRateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No exchangeRate with id {%s} found!".formatted(id)));
    }

    @Override
    public List<ExchangeRate> getAll() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public List<ExchangeRate> getAllByCurrency(Currency currency) {
        return exchangeRateRepository.findAllByCurrency(currency);
    }

    @Override
    public List<ExchangeRate> getAllByCurrency(long currencyId) {
        return exchangeRateRepository.findAllByCurrency_Id(currencyId);
    }

    @Override
    public List<ExchangeRate> getAllByDate(LocalDate date) {
        return exchangeRateRepository.findAllByLocalDate(date);
    }

    @Override
    public ExchangeRate update(ExchangeRate exchangeRate) {
        if (!exchangeRateRepository.existsById(exchangeRate.getId())) {
            throw new ResourceNotFoundException("No exchangeRate with id {%s} found!".formatted(exchangeRate.getId()));
        }
        return exchangeRateRepository.save(exchangeRate);
    }

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }
}

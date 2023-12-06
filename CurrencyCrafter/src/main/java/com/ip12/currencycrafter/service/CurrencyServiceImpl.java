package com.ip12.currencycrafter.service;

import com.ip12.currencycrafter.entity.Currency;
import com.ip12.currencycrafter.exception.ResourceNotFoundException;
import com.ip12.currencycrafter.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;


    @Override
    public void deleteById(long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public Currency getById(long id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No currency with id {%s} found!".formatted(id)));
    }

    @Override
    public List<Currency> getAll() {
        return currencyRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Currency update(Currency currency) {
        if (!currencyRepository.existsById(currency.getId())) {
            throw new ResourceNotFoundException("No currency with id {%s} found!".formatted(currency.getId()));
        }
        return currencyRepository.save(currency);
    }

    @Override
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }
}

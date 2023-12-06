package com.ip12.currencycrafter.repository;


import com.ip12.currencycrafter.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Currency> findAllByOrderByIdAsc();
}

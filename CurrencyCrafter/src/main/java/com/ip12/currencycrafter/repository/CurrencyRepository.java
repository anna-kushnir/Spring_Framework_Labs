package com.ip12.currencycrafter.repository;


import com.ip12.currencycrafter.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Currency> findAllByOrderByIdAsc();

    boolean existsByName(String name);

    Optional<Currency> findByName(String name);
}

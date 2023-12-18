package com.ip12.currencycrafter.repository;


import com.ip12.currencycrafter.entity.Currency;
import com.ip12.currencycrafter.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    List<ExchangeRate> findAllByCurrency_Id(Long currencyId);

    List<ExchangeRate> findAllByCurrency(Currency currency);

    List<ExchangeRate> findAllByLocalDate(LocalDate date);


    @Query("""
            SELECT er FROM EXCHANGE_RATE er
            WHERE er.currency.id = :currencyId
            AND er.localDate BETWEEN :startDate AND :endDate
            """)
    List<ExchangeRate> findAllByCurrencyIdAndDateLimits(
            @Param("currencyId") Long currencyId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    Optional<ExchangeRate> findByLocalDateAndCurrency_Id(LocalDate localDate, Long currencyId);
}

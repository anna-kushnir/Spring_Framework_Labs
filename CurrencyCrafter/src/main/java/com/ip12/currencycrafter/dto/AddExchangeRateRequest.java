package com.ip12.currencycrafter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AddExchangeRateRequest {
    @NotNull
    private String currencyName;

    @NotNull
    private BigDecimal rateToUsd;

    @NotNull
    private LocalDate localDate;
}

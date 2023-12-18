package com.ip12.currencycrafter.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateExchangeRateDto {
    private LocalDate localDate;
    private BigDecimal rate;
}

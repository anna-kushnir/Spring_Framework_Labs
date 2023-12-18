package com.ip12.currencycrafter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class CurrencyRateDto {
    @Schema(description = "Currency Id", example = "5")
    private Long id;

    @Min(value = 3, message = "Currency ISO code must be 3 capital letters")
    @Max(value = 3, message = "Currency ISO code must be 3 capital letters")
    @Pattern(regexp = "[A-Z]", message = "Currency ISO code must be 3 capital letters")
    @NotBlank
    @Schema(description = "Currency Name", example = "UAH")
    private String name;
    @Schema(description = "Currency Exchange Rates")
    private List<ExchangeRateDto> exchangeRates;
}

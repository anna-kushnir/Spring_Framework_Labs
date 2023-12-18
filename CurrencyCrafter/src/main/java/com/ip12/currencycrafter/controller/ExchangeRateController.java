package com.ip12.currencycrafter.controller;

import com.ip12.currencycrafter.dto.AddExchangeRateRequest;
import com.ip12.currencycrafter.service.CurrencyService;
import com.ip12.currencycrafter.service.ExchangeRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ExchangeRateController {
    private final CurrencyService currencyService;
    private final ExchangeRateService exchangeRateService;

    @GetMapping("/currencies/{firstCurrencyId}/exchange-rates")
    public String getAllExchangeRatesByCurrency(Model model,
                                                @PathVariable Long firstCurrencyId,
                                                @RequestParam(name = "secondCurrencyId", defaultValue = "1") Long secondCurrencyId,
                                                @RequestParam(name = "startDate", required = false) LocalDate startDateReq,
                                                @RequestParam(name = "endDate", required = false) LocalDate endDateReq
    ) {
        var startDate = Optional.ofNullable(startDateReq)
                .orElseGet(() -> LocalDate.now().minusWeeks(1));

        var endDate = Optional.ofNullable(endDateReq)
                .orElseGet(LocalDate::now);

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("firstCurrency", currencyService.getById(firstCurrencyId));
        model.addAttribute("secondCurrency", currencyService.getById(secondCurrencyId));
        model.addAttribute("allCurrencies", currencyService.getAll());
        model.addAttribute("exchangeRates", currencyService.getAllExchangeRateInRange(firstCurrencyId, secondCurrencyId, startDate, endDate));
        return "exchange-rates-by-currency";
    }

    @GetMapping("/currencies/{currencyId}/exchange-rates/new")
    public String getAddForm(Model model, @PathVariable("currencyId") Long currencyId) {
        model.addAttribute("currency", currencyService.getById(currencyId));
        return "add-exchange-rate";
    }

    @PostMapping("/exchange-rates")
    public String addExchangeRate(@Valid AddExchangeRateRequest addExchangeRateRequest) {
        log.info("Add exchange rate request {}", addExchangeRateRequest);
        exchangeRateService.save(addExchangeRateRequest);
        return "success";
    }
}

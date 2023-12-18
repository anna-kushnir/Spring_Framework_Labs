package com.ip12.currencycrafter.controller;

import com.ip12.currencycrafter.dto.CurrencyRateDto;
import com.ip12.currencycrafter.service.CurrencyService;
import com.ip12.currencycrafter.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Controller
@Slf4j
@RequestMapping("/currencies")
@RequiredArgsConstructor
@Tag(name = "Currency Controller", description = "Operations with currencies")
public class CurrencyController {
    private final CurrencyService currencyService;
    private final ExchangeRateService exchangeRateService;

    @GetMapping
    public String getAllCurrencies(Model model) {
        model.addAttribute("currencies", currencyService.getAll());
        return "currency";
    }

    @GetMapping("/today")
    public String getTodayCurrencies(Model model) {
        var allCurrencies = currencyService.getAllWithTodayRate();
        model.addAttribute("currencies", allCurrencies);
        model.addAttribute("today", LocalDate.now());
        return "today";
    }

    @GetMapping("/{currencyId}/edit")
    public String getUpdateForm(Model model, @PathVariable("currencyId") Long currencyId) {
        log.info("Currency with id {} is being edited", currencyId);
        var currency = currencyService.getById(currencyId);
        model.addAttribute("currency", currency);
        log.info("Currency to edit: {}", currency);
        return "update";
    }

    @GetMapping("/new")
    public String getAddForm() {
        return "add-currency";
    }

    @ResponseBody
    @DeleteMapping("/{currencyId}")
    public ResponseEntity<?> deleteCurrencyById(@PathVariable("currencyId") Long currencyId) {
        currencyService.deleteById(currencyId);
        return ResponseEntity.ok("ok");
    }

    @ResponseBody
    @PutMapping("/{currencyId}")
    public ResponseEntity<?> updateCurrencyById(@PathVariable("currencyId") Long currencyId, @RequestBody CurrencyRateDto currency) {
        currencyService.update(currency);
        return ResponseEntity.ok("ok");
    }

//    @ResponseBody
//    @PostMapping
//    public ResponseEntity<?> addCurrency(@RequestBody Currency currency) {
//        currencyService.save(currency);
//        return ResponseEntity.ok("ok");
//    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> addCurrency(@RequestBody CurrencyRateDto currencyDto) {
        if (currencyDto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        CurrencyRateDto currency = currencyService.save(currencyDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(currency.getId()).toUri();
        return ResponseEntity.created(uri).body(currency);
    }


//    @GetMapping("/{firstCurrencyId}/exchange-rates/")
//    @ResponseBody
//    public Map<LocalDate, BigDecimal> getExchangeRateInDateRangeForCurrency(
//            @PathVariable Long firstCurrencyId,
//            @RequestParam(name = "secondCurrencyId") Long secondCurrencyId,
//            @RequestParam(name = "startDate", required = false) LocalDate startDate,
//            @RequestParam(name = "endDate", required = false) LocalDate endDate
//    ) {
//        return currencyService.getAllExchangeRateInRange(firstCurrencyId, secondCurrencyId, startDate, endDate);
//    }
}

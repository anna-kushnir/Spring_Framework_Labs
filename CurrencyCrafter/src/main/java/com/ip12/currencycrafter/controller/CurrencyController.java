package com.ip12.currencycrafter.controller;

import com.ip12.currencycrafter.entity.Currency;
import com.ip12.currencycrafter.service.CurrencyService;
import com.ip12.currencycrafter.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/currency")
@RequiredArgsConstructor
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
        var allCurrencies = exchangeRateService.getAllByDate(LocalDate.of(2022, 2, 5));
        model.addAttribute("exchangeRates", allCurrencies);
        return "today";
    }

    @GetMapping("/updateform")
    public String getUpdateForm(Model model, @RequestParam(name = "currencyId") Long currencyId) {
        model.addAttribute("currency", currencyService.getById(currencyId));
        return "update";
    }

    @GetMapping("/addform")
    public String getAddForm() {
        return "add";
    }

    @ResponseBody
    @DeleteMapping("/{currencyId}")
    public ResponseEntity<?> deleteCurrencyById(@PathVariable("currencyId") Long currencyId) {
        currencyService.deleteById(currencyId);
        return ResponseEntity.ok("ok");
    }

    @ResponseBody
    @PutMapping
    public ResponseEntity<?> updateCurrencyById(@RequestBody Currency currency) {
        currencyService.update(currency);
        return ResponseEntity.ok("ok");
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> addCurrency(@RequestBody Currency currency) {
        currencyService.save(currency);
        return ResponseEntity.ok("ok");
    }
}

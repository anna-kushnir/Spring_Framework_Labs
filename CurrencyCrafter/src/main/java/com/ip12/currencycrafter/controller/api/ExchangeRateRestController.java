package com.ip12.currencycrafter.controller.api;

import com.ip12.currencycrafter.dto.AddExchangeRateRequest;
import com.ip12.currencycrafter.dto.ExchangeRateDto;
import com.ip12.currencycrafter.dto.UpdateExchangeRateDto;
import com.ip12.currencycrafter.exception.ResourceNotFoundException;
import com.ip12.currencycrafter.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/exchange-rates")
@RestController
@RequiredArgsConstructor
@Tag(name = "Exchange Rate Rest Controller", description = "Operations with exchange rates (REST API)")
public class ExchangeRateRestController {
    private final ExchangeRateService exchangeRateService;

    @GetMapping
    @Operation(summary = "Get all", description = "Get a list of all Exchange Rates in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully received")
    })
    public List<ExchangeRateDto> findAll() {
        return exchangeRateService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get by Id",
            description = "Get Exchange Rate by its Identifier",
            parameters = {@Parameter(name = "id", description = "Exchange Rate Id", example = "5")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully received"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<ExchangeRateDto> findById(@PathVariable Long id) throws ResourceNotFoundException {
        ExchangeRateDto exchangeRateDto = exchangeRateService.getById(id);
        return ResponseEntity.ok(exchangeRateDto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete by Id",
            description = "Delete Exchange Rate with specified Identifier",
            parameters = {@Parameter(name = "id", description = "Exchange Rate Id", example = "10")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
    })
    public void deleteById(@PathVariable Long id) {
        exchangeRateService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update by Id",
            description = "Update Exchange Rate with specified Identifier",
            parameters = {@Parameter(name = "id", description = "Exchange Rate Id", example = "7")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Exchange Rate with specified date already exists", content = @Content)
    })
    public ResponseEntity<ExchangeRateDto> update(@PathVariable Long id, @RequestBody @Valid UpdateExchangeRateDto updateExchangeRateDto) {
        var exchangeRate = exchangeRateService.update(id, updateExchangeRateDto);
        return ResponseEntity.ok(exchangeRate);
    }


    @PostMapping
    @Operation(
            summary = "Create",
            description = "Create new Exchange Rate"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
    })
    public ResponseEntity<ExchangeRateDto> create(@RequestBody @Valid AddExchangeRateRequest addExchangeRateRequest) {
        var exchangeRate = exchangeRateService.save(addExchangeRateRequest);
        return ResponseEntity.ok(exchangeRate);
    }
}
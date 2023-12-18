package com.ip12.currencycrafter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "EXCHANGE_RATE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EXCHANGE_RATE", schema = "CURRENCY_SCHEMA")
public class ExchangeRate {
    @Id
    @SequenceGenerator(name = "ID_GENERATOR_EXCHANGE_RATE", sequenceName = "CURRENCY_SCHEMA.EXCHANGE_RATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GENERATOR_EXCHANGE_RATE")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "LOCAL_DATE", columnDefinition = "DATE")
    private LocalDate localDate;

    @Column(name = "RATE")
    private BigDecimal rate;


    //TODO: add custom join @Query
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;
}

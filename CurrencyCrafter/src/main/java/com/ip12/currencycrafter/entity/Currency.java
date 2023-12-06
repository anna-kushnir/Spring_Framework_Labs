package com.ip12.currencycrafter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "CURRENCY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CURRENCY", schema = "CURRENCY_SCHEMA")
public class Currency {
    @Id
    @SequenceGenerator(name = "ID_GENERATOR_CURRENCY", sequenceName = "CURRENCY_SCHEMA.CURRENCY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GENERATOR_CURRENCY")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "currency", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ExchangeRate> exchangeRates = new HashSet<>();
}

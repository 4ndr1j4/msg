package com.msg.microservices.vatrates.service;

import com.msg.microservices.vatrates.external.CountryRates;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.function.Function;

public enum TypeOfCountryRates {
    STANDARD_RATES(CountryRates::getStandard_rate),
    REDUCED_RATES(CountryRates::getReduced_rate);

    @Getter
    private final Function<CountryRates, BigDecimal> rates;

    TypeOfCountryRates(Function<CountryRates, BigDecimal> rates) {
        this.rates=rates;
    }
}

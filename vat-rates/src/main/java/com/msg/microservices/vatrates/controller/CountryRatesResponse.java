package com.msg.microservices.vatrates.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.msg.microservices.shared.FalseToZeroBigDecimalDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CountryRatesResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("isoCountryCode")
    String iso_duplicate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nonIsoCountryCode")
    String iso_duplicate_of;

    @JsonProperty("countryName")
    String country;

    @JsonProperty("standardRate")
    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal standard_rate;

    @JsonProperty("reducedRate")
    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal reduced_rate;

    @JsonIgnore
    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal reduced_rate_alt;

    @JsonIgnore
    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal super_reduced_rate;

    @JsonIgnore
    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal parking_rate;

}

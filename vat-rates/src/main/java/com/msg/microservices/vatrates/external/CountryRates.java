package com.msg.microservices.vatrates.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.msg.microservices.shared.FalseToZeroBigDecimalDeserializer;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class CountryRates<CountryCode> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    CountryCode iso_duplicate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    CountryCode iso_duplicate_of;

    String country;

    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal standard_rate;

    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal reduced_rate;

    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal reduced_rate_alt;

    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal super_reduced_rate;

    @JsonDeserialize(using = FalseToZeroBigDecimalDeserializer.class)
    BigDecimal parking_rate;

}

package com.msg.microservices.vatrates.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msg.microservices.shared.ExternalResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountriesRatesExternalResponse<CR extends CountryRates> implements ExternalResponse {
    @Setter
    @Getter
    private Map<String, CR> rates;

}

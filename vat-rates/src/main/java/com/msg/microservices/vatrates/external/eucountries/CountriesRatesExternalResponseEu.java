package com.msg.microservices.vatrates.external.eucountries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.msg.microservices.vatrates.external.CountriesRatesExternalResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountriesRatesExternalResponseEu extends CountriesRatesExternalResponse<CountryRatesEu> {

}

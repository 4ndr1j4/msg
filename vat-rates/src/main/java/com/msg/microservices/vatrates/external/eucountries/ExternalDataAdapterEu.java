package com.msg.microservices.vatrates.external.eucountries;

import com.msg.microservices.shared.enums.CountryCodeEu;
import com.msg.microservices.shared.ExternalDataAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class ExternalDataAdapterEu extends ExternalDataAdapter {

    ExternalDataAdapterEu(@Value("${eu.vatrates.url}") final URI url) {
        super(url, CountriesRatesExternalResponseEu.class, CountryCodeEu.values());

    }

}

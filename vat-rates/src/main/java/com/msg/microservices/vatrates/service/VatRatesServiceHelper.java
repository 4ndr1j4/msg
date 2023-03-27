package com.msg.microservices.vatrates.service;

import com.msg.microservices.shared.ExternalDataAdapter;
import com.msg.microservices.shared.enums.CountryCodeStandard;
import com.msg.microservices.shared.enums.SortOrder;
import com.msg.microservices.vatrates.exceptions.IncorrectCountryCodeException;
import com.msg.microservices.vatrates.external.CountriesRatesExternalResponse;
import com.msg.microservices.vatrates.external.CountryRates;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class VatRatesServiceHelper {


    public final ExternalDataAdapter externalDataAdapter;

    public VatRatesServiceHelper(@Autowired ExternalDataAdapter externalDataAdapter) {
        this.externalDataAdapter = externalDataAdapter;
    }

    @Getter(AccessLevel.PACKAGE)
    private Stream<CountryRates> countryRatesStream;

     VatRatesServiceHelper countryRatesStream() {
        CountriesRatesExternalResponse mappedResponse = (CountriesRatesExternalResponse) externalDataAdapter.getMappedDto();
        Map<String, CountryRates> rates = mappedResponse.getRates();
        List<String> countryCodesEnums = Arrays.stream(externalDataAdapter.getCc()).map(Enum::name).toList();

        long numberOfCountriesNotInTheGroup = rates.keySet().stream().filter(cc -> !countryCodesEnums.contains(cc)).count();
        long numberOfCountriesMissingInTheGroup = countryCodesEnums.stream().filter(cc -> !rates.keySet().contains(cc)).count();
        if(numberOfCountriesNotInTheGroup!=0 || numberOfCountriesMissingInTheGroup!=0){
            throw new IncorrectCountryCodeException("Incorrect country codes, numberOfCountriesNotInTheGroup: "+numberOfCountriesNotInTheGroup
                    +" numberOfCountriesMissingInTheGroup: "+numberOfCountriesMissingInTheGroup);
        }

        countryRatesStream = rates.values().stream();
        return this;
    }


    VatRatesServiceHelper filterOutDuplicatesWith(CountryCodeStandard cs) {
        countryRatesStream = countryRatesStream
                .filter(cc -> cs.equals(CountryCodeStandard.EU) ?
                        cc.getIso_duplicate_of() == null : cc.getIso_duplicate() == null);
        return this;

    }

    VatRatesServiceHelper sortBy(TypeOfCountryRates type, SortOrder sortOrder) {
        countryRatesStream = countryRatesStream
                .sorted(sortOrder.equals(SortOrder.DESC) ?
                        Comparator.comparing(type.getRates()).reversed()
                        : Comparator.comparing(type.getRates()));
        return this;
    }

}

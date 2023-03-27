package com.msg.microservices.vatrates.service;


import com.msg.microservices.shared.enums.CountryCodeStandard;
import com.msg.microservices.shared.enums.SortOrder;
import com.msg.microservices.vatrates.controller.CountryRatesListResponse;
import com.msg.microservices.vatrates.controller.CountryRatesResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.msg.microservices.shared.ModelMapper.mapTo;

@Service
public class VatRatesService {

    final
    VatRatesServiceHelper vatRatesServiceHelper;

    public VatRatesService(VatRatesServiceHelper vatRatesServiceHelper) {
        this.vatRatesServiceHelper = vatRatesServiceHelper;
    }


    public CountryRatesListResponse returnCountriesWith(CountryCodeStandard cs, TypeOfCountryRates type, SortOrder sortOrder, int limit) {
        List<CountryRatesResponse> countryRates = vatRatesServiceHelper.countryRatesStream().filterOutDuplicatesWith(cs).sortBy(type, sortOrder)
                .getCountryRatesStream().limit(limit).map(c -> mapTo().map(c, CountryRatesResponse.class)).collect(Collectors.toList());
        return CountryRatesListResponse.builder().countries(countryRates).build();
    }


}

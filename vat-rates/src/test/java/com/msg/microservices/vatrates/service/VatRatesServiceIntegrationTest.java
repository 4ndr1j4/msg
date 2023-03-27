package com.msg.microservices.vatrates.service;

import com.msg.microservices.shared.enums.CountryCodeStandard;
import com.msg.microservices.shared.enums.SortOrder;
import com.msg.microservices.vatrates.controller.CountryRatesListResponse;
import com.msg.microservices.vatrates.controller.CountryRatesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
class VatRatesServiceIntegrationTest {

    @Autowired
    VatRatesService vatRatesService;

    @Test
    void returnCountriesWithHighestStandardRatesLimit3() {
        CountryRatesListResponse result = vatRatesService.returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.STANDARD_RATES, SortOrder.DESC, 3);
        List<CountryRatesResponse> countries = result.getCountries();
        assert (long) countries.size() == 3;
        CountryRatesResponse firstCountry = countries.get(0);
        assert firstCountry.getCountry().equals("Hungary");
        assert firstCountry.getStandard_rate().equals(new BigDecimal("27.00"));
        assert firstCountry.getReduced_rate().equals(new BigDecimal("18.00"));
    }
    @Test
    void returnCountriesWithHighestStandardRatesLimit10() {
        CountryRatesListResponse result = vatRatesService.returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.STANDARD_RATES, SortOrder.DESC, 10);
        List<CountryRatesResponse> countries = result.getCountries();
        assert (long) countries.size() == 10;
        CountryRatesResponse countryNo10 = countries.get(9);
        assert countryNo10.getCountry().equals("Italy");
        assert countryNo10.getStandard_rate().equals(new BigDecimal("22.00"));
        assert countryNo10.getReduced_rate().equals(new BigDecimal("10.00"));
        assert countryNo10.getReduced_rate_alt().equals(new BigDecimal("4.00"));
    }

    @Test
    void returnCountriesWithHighestStandardRatesLimit4_test_IsoCountryCodeStandard() {
        CountryRatesListResponse result = vatRatesService.returnCountriesWith(CountryCodeStandard.ISO, TypeOfCountryRates.STANDARD_RATES, SortOrder.DESC, 5);
        List<CountryRatesResponse> countries = result.getCountries();
        assert (long) countries.size() == 5;
        CountryRatesResponse countryNo4 = countries.get(4);
        assert countryNo4.getCountry().equals("Greece");
        assert countryNo4.getIso_duplicate_of().equals("EL");
    }

    @Test
    void returnCountriesWithHighestStandardRatesLimit4_test_EuCountryCodeStandard() {
        CountryRatesListResponse result = vatRatesService.returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.STANDARD_RATES, SortOrder.DESC, 5);
        List<CountryRatesResponse> countries = result.getCountries();
        assert (long) countries.size() == 5;
        CountryRatesResponse countryNo4 = countries.get(4);
        assert countryNo4.getCountry().equals("Greece");
        assert countryNo4.getIso_duplicate().equals("GR");
    }

    @Test
    void returnCountriesWithLowestStandardRatesLimit1() {
        CountryRatesListResponse result = vatRatesService.returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.STANDARD_RATES, SortOrder.ASC, 1);
        List<CountryRatesResponse> countries = result.getCountries();
        assert (long) countries.size() == 1;
        CountryRatesResponse countryNo1 = countries.get(0);
        assert countryNo1.getCountry().equals("Luxembourg");
        assert countryNo1.getStandard_rate().equals(new BigDecimal("17.00"));
        assert countryNo1.getReduced_rate().equals(new BigDecimal("14.00"));
        assert countryNo1.getReduced_rate_alt().equals(new BigDecimal("8.00"));
    }

    @Test
    void returnCountriesWithLowestTReducedRatesLimit4() {
        CountryRatesListResponse result = vatRatesService.returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.REDUCED_RATES, SortOrder.ASC, 4);
        List<CountryRatesResponse> countries = result.getCountries();
        assert (long) countries.size() == 4;
        CountryRatesResponse countryNo4 = countries.get(3);
        assert countryNo4.getCountry().equals("Germany");
        assert countryNo4.getStandard_rate().equals(new BigDecimal("19.00"));
        assert countryNo4.getReduced_rate().equals(new BigDecimal("7.00"));
        assert countryNo4.getReduced_rate_alt().equals(new BigDecimal("0"));
    }
}
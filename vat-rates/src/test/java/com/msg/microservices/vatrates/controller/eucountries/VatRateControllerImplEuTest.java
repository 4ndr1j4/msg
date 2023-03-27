package com.msg.microservices.vatrates.controller.eucountries;

import com.msg.microservices.shared.enums.CountryCodeStandard;
import com.msg.microservices.shared.enums.SortOrder;
import com.msg.microservices.vatrates.controller.CountryRatesListResponse;
import com.msg.microservices.vatrates.controller.CountryRatesResponse;
import com.msg.microservices.vatrates.service.TypeOfCountryRates;
import com.msg.microservices.vatrates.service.VatRatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(VatRateControllerImplEu.class)
class VatRateControllerImplEuTest {

@Autowired
private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VatRatesService vatRateService;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getEuStandardHighest3() throws Exception {
        doReturn(getEuStandardHighest3Response()).when(vatRateService).returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.STANDARD_RATES, SortOrder.DESC, 3);
        this.mockMvc.perform(get("/vat-rates/eu/standard/highest/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hungary")))
                .andExpect(content().string(containsString("Denmark")))
                .andExpect(content().string(containsString("Croatia")))
                .andExpect(content().string(containsString("countries")));
    }

    @Test
    void getEuReducedLowest3() throws Exception {
        doReturn(getEuReducedLowest3Response()).when(vatRateService).returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.REDUCED_RATES, SortOrder.ASC, 3);
        this.mockMvc.perform(get("/vat-rates/eu/reduced/lowest/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Denmark")))
                .andExpect(content().string(containsString("United Kingdom")))
                .andExpect(content().string(containsString("Netherlands")))
                .andExpect(content().string(containsString("countries")));
    }


    private CountryRatesListResponse getEuStandardHighest3Response() {

        CountryRatesResponse countryRatesResponse1 = new CountryRatesResponse();

        countryRatesResponse1.setCountry("Hungary");
        countryRatesResponse1.setStandard_rate(new BigDecimal("27.00"));
        countryRatesResponse1.setReduced_rate(new BigDecimal("18.00"));

        CountryRatesResponse countryRatesResponse2 = new CountryRatesResponse();
        countryRatesResponse2.setCountry("Denmark");
        countryRatesResponse2.setStandard_rate(new BigDecimal("25.00"));
        countryRatesResponse2.setReduced_rate(new BigDecimal("0"));

        CountryRatesResponse countryRatesResponse3 = new CountryRatesResponse();
        countryRatesResponse3.setCountry("Croatia");
        countryRatesResponse3.setStandard_rate(new BigDecimal("25.00"));
        countryRatesResponse3.setReduced_rate(new BigDecimal("13.00"));


        List<CountryRatesResponse> countries = List.of(countryRatesResponse1,countryRatesResponse2,countryRatesResponse3);

        return new CountryRatesListResponse(countries);
    }

    private CountryRatesListResponse getEuReducedLowest3Response() {


        CountryRatesResponse countryRatesResponse1 = new CountryRatesResponse();
        countryRatesResponse1.setCountry("Denmark");
        countryRatesResponse1.setStandard_rate(new BigDecimal("25.00"));
        countryRatesResponse1.setReduced_rate(new BigDecimal("0"));

        CountryRatesResponse countryRatesResponse2 = new CountryRatesResponse();
        countryRatesResponse2.setCountry("United Kingdom");
        countryRatesResponse2.setStandard_rate(new BigDecimal("20.00"));
        countryRatesResponse2.setReduced_rate(new BigDecimal("5.00"));

        CountryRatesResponse countryRatesResponse3 = new CountryRatesResponse();
        countryRatesResponse3.setCountry("Netherlands");
        countryRatesResponse3.setStandard_rate(new BigDecimal("21.00"));
        countryRatesResponse3.setReduced_rate(new BigDecimal("6.00"));

        List<CountryRatesResponse> countries = List.of(countryRatesResponse1,countryRatesResponse2,countryRatesResponse3);

        return new CountryRatesListResponse(countries);
    }
}

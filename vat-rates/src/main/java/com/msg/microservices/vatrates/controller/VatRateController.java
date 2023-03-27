package com.msg.microservices.vatrates.controller;

import com.msg.microservices.shared.enums.CountryCodeStandard;
import com.msg.microservices.shared.enums.SortOrder;
import com.msg.microservices.vatrates.service.TypeOfCountryRates;
import com.msg.microservices.vatrates.service.VatRatesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;


//@RestController
@AllArgsConstructor
@RequestMapping(value = VatRateController.VAT_RATES)
public class VatRateController {


    VatRatesService vatRatesService;

    public static final String VAT_RATES = "/vat-rates";

    public static final String STANDARD_RATES = "/standard";

    public static final String REDUCED_RATES = "/reduced";
    public static final String HIGHEST = "/highest";

    public static final String LOWEST = "/lowest";
    public static final String THREE = "/3";


    @Operation(summary = "Return three EU countries with the highest standard VAT rate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CountryRatesListResponse.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping(STANDARD_RATES + HIGHEST + THREE)
    private ResponseEntity<CountryRatesListResponse> returnCountriesWithHighestThreeStandardRates() {

        CountryRatesListResponse result = vatRatesService.returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.STANDARD_RATES, SortOrder.DESC, 3);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Return three EU countries with the lowest reduced VAT rates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CountryRatesListResponse.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping(REDUCED_RATES + LOWEST + THREE)
    private ResponseEntity<CountryRatesListResponse> returnCountriesWithLowestThreeReducedRates() {

        CountryRatesListResponse result = vatRatesService.returnCountriesWith(CountryCodeStandard.EU, TypeOfCountryRates.REDUCED_RATES, SortOrder.ASC, 3);
        return ResponseEntity.ok(result);
    }

    /**
     // Can be more generic, but also more validation needs to be added
     @GetMapping("/vat-rates/{countryCodeStandard}/{ratesType}/{order}/{limit}") private ResponseEntity<List<CountryRates>> returnCountriesWith(@PathVariable CountryCodeStandard countryCodeStandard,
     @PathVariable TypeOfCountryRates ratesType,
     @PathVariable SortOrder order,
     @PathVariable int limit) {

     List<CountryRates> result = vatRatesService.returnCountriesWith(countryCodeStandard, ratesType,order,limit);
     return ResponseEntity.ok(result);
     }
     **/

}

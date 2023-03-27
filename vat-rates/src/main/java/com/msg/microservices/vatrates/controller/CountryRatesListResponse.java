package com.msg.microservices.vatrates.controller;

import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@AllArgsConstructor
@Getter
public class CountryRatesListResponse {

    @SchemaProperty(name = "List of filtered countries by rates")
    private List<CountryRatesResponse> countries;
}

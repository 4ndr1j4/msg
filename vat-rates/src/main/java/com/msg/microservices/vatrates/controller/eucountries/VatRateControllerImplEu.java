package com.msg.microservices.vatrates.controller.eucountries;

import com.msg.microservices.vatrates.controller.VatRateController;
import com.msg.microservices.vatrates.service.VatRatesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(VatRateController.VAT_RATES + VatRateControllerImplEu.EU)
public class VatRateControllerImplEu extends VatRateController {


    public static final String EU = "/eu";

    public VatRateControllerImplEu(VatRatesService vatRatesService) {
        super(vatRatesService);
    }

}

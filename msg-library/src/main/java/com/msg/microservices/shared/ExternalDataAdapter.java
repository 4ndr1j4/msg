package com.msg.microservices.shared;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;


@Slf4j
public class ExternalDataAdapter {
    private final URI url;

    private final Class<ExternalResponse> dto;

    //@todo find another place for country code enums
    @Getter
    private final Enum[] cc;


    public ExternalDataAdapter(URI url, Class dto, Enum[] cc) {
        this.dto = dto;
        this.url = url;
        this.cc = cc;
    }


    public ExternalResponse getMappedDto() {
        ExternalResponse result = null;
        try {
            RestTemplate rt = new RestTemplate();
            result = rt.getForObject(url, dto);

        } catch (RestClientException e) {
            log.error("RestClientException caused by:/n" + e.getCause() + " Message:/n" + e.getMessage() + "Stack trace:/n " + Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            log.error("Exception caused by:/n" + e.getCause() + " Message:/n" + e.getMessage() + "Stack trace:/n " + Arrays.toString(e.getStackTrace()));
        }
        return result;
    }
}

package com.msg.microservices.vatrates.exceptions;


public class IncorrectCountryCodeException extends RuntimeException {
    public IncorrectCountryCodeException(String s) {
        super(s);
    }
}

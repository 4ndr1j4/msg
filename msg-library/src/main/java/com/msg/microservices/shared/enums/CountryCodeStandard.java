package com.msg.microservices.shared.enums;

public enum CountryCodeStandard {

    EU("eu"),
    ISO("iso");

    String standard;

    CountryCodeStandard(String standard) {
        this.standard = standard;
    }
}

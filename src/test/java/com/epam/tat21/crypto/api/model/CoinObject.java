package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinObject {

    @JsonProperty("EUR")
    private double eur;

    @JsonProperty("JPY")
    private double jpy;

    @JsonProperty("USD")
    private double usd;


    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public double getJpy() {
        return jpy;
    }

    public void setJpy(double jpy) {
        this.jpy = jpy;
    }
}

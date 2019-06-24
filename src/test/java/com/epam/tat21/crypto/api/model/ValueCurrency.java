package com.epam.tat21.crypto.api.model;

import com.epam.tat21.crypto.bo.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueCurrency {

    @JsonProperty("EUR")
    public Currency eur;

    @JsonProperty("JPY")
    public Currency jpy;

    @JsonProperty("Currency")
    public Currency currency;



    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

}

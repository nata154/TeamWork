package com.epam.tat21.crypto.api.model;

import com.epam.tat21.crypto.bo.Coin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinValueInCurrency {

    @JsonProperty("Coin")
    private List<Coin> coinListInCurrency;

    @JsonProperty("CurrencyForCoin")
    private List<CurrencyForCoin> currencyForCoin;

    public List<Coin> getCoinListInCurrency() {
        return coinListInCurrency;
    }

    public void setCoinListInCurrency(List<Coin> coinListInCurrency) {
        this.coinListInCurrency = coinListInCurrency;
    }

    public List<CurrencyForCoin> getCurrencyForCoin() {
        return currencyForCoin;
    }

    public void setCurrencyForCoin(List<CurrencyForCoin> currencyForCoin) {
        this.currencyForCoin = currencyForCoin;
    }
}

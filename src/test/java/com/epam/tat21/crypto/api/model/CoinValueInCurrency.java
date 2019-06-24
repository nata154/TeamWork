package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinValueInCurrency {

    @JsonProperty("BTC")
    public Map<String, ValueCurrency> currencyMap1;

    @JsonProperty("Coin")
    public Map<String, ValueCurrency> currencyMap;

    public Map<String, ValueCurrency> getCurrencyMap() {
        return currencyMap;
    }

    public void setCurrencyMap(Map<String, ValueCurrency> data) {
        this.currencyMap = data;
    }

    //    @JsonProperty("Coin")
//    private List<Coin> coinListInCurrency;
//
//    @JsonProperty("CurrencyForCoin")
//    private List<CurrencyForCoin> currencyForCoin;
//
//    public List<Coin> getCoinListInCurrency() {
//        return coinListInCurrency;
//    }
//
//    public void setCoinListInCurrency(List<Coin> coinListInCurrency) {
//        this.coinListInCurrency = coinListInCurrency;
//    }
//
//    public List<CurrencyForCoin> getCurrencyForCoin() {
//        return currencyForCoin;
//    }
//
//    public void setCurrencyForCoin(List<CurrencyForCoin> currencyForCoin) {
//        this.currencyForCoin = currencyForCoin;
//    }

}

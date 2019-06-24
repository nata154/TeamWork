package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinValueInCurrency {

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

    //  LTC&tsyms=EUR,JPY

    @JsonProperty("LTC")
    private Object coinItem;

    @JsonProperty("EUR")
    private String currencyEUR;

    @JsonProperty("JPY")
    private String currencyJPY;

    public Object getCoinItem() {
        return coinItem;
    }

    public void setCoinItem(Object coinItem) {
        this.coinItem = coinItem;
    }

    public String getCurrencyEUR() {
        return currencyEUR;
    }

    public void setCurrencyEUR(String currencyEUR) {
        this.currencyEUR = currencyEUR;
    }

    public String getCurrencyJPY() {
        return currencyJPY;
    }

    public void setCurrencyJPY(String currencyJPY) {
        this.currencyJPY = currencyJPY;
    }
}

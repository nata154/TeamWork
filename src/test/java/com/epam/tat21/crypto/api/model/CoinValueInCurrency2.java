package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinValueInCurrency2 {

    @JsonProperty("BTC")
    private Map<String, ValueCurrency2> currencyMapBTC;

    @JsonProperty("ETC")
    private Map<String, ValueCurrency2> currencyMapETC;

    @JsonProperty("LTC")
    private Map<String, ValueCurrency2> currencyMapLTC;

    @JsonProperty("XMR")
    private Map<String, ValueCurrency2> currencyMapXMR;

    @JsonProperty("ZEC")
    private Map<String, ValueCurrency2> currencyMapZEC;

    @JsonProperty("XRP")
    private Map<String, ValueCurrency2> currencyMapXRP;

    public Map<String, ValueCurrency2> getCurrencyMapBTC() {
        return currencyMapBTC;
    }

    public void setCurrencyMapBTC(Map<String, ValueCurrency2> currencyMapBTC) {
        this.currencyMapBTC = currencyMapBTC;
    }

    public Map<String, ValueCurrency2> getCurrencyMapETC() {
        return currencyMapETC;
    }

    public void setCurrencyMapETC(Map<String, ValueCurrency2> currencyMapETC) {
        this.currencyMapETC = currencyMapETC;
    }

    public Map<String, ValueCurrency2> getCurrencyMapLTC() {
        return currencyMapLTC;
    }

    public void setCurrencyMapLTC(Map<String, ValueCurrency2> currencyMapLTC) {
        this.currencyMapLTC = currencyMapLTC;
    }

    public Map<String, ValueCurrency2> getCurrencyMapXMR() {
        return currencyMapXMR;
    }

    public void setCurrencyMapXMR(Map<String, ValueCurrency2> currencyMapXMR) {
        this.currencyMapXMR = currencyMapXMR;
    }

    public Map<String, ValueCurrency2> getCurrencyMapZEC() {
        return currencyMapZEC;
    }

    public void setCurrencyMapZEC(Map<String, ValueCurrency2> currencyMapZEC) {
        this.currencyMapZEC = currencyMapZEC;
    }

    public Map<String, ValueCurrency2> getCurrencyMapXRP() {
        return currencyMapXRP;
    }

    public void setCurrencyMapXRP(Map<String, ValueCurrency2> currencyMapXRP) {
        this.currencyMapXRP = currencyMapXRP;
    }
}

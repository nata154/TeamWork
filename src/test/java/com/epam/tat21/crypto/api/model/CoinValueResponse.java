package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinValueResponse {
    @JsonProperty("LTC")
    private CoinObject ltc;

    @JsonProperty("BTC")
    private CoinObject btc;

    public CoinObject getLtc() {
        return ltc;
    }

    public void setLtc(CoinObject ltc) {
        this.ltc = ltc;
    }

    public CoinObject getBtc() {
        return btc;
    }

    public void setBtc(CoinObject btc) {
        this.btc = btc;
    }
}

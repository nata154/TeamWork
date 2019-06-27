package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinValueResponse1 {

    @JsonProperty("BTC")
    private CoinObject1 btc;

    @JsonProperty("ETC")
    private CoinObject1 etc;

    @JsonProperty("LTC")
    private CoinObject1 ltc;

    @JsonProperty("XMR")
    private CoinObject1 xmr;

    @JsonProperty("ZEC")
    private CoinObject1 zec;

    @JsonProperty("XRP")
    private CoinObject1 xrp;

    public CoinObject1 getBtc() {
        return btc;
    }

    public void setBtc(CoinObject1 btc) {
        this.btc = btc;
    }

    public CoinObject1 getEtc() {
        return etc;
    }

    public void setEtc(CoinObject1 etc) {
        this.etc = etc;
    }

    public CoinObject1 getLtc() {
        return ltc;
    }

    public void setLtc(CoinObject1 ltc) {
        this.ltc = ltc;
    }

    public CoinObject1 getXmr() {
        return xmr;
    }

    public void setXmr(CoinObject1 xmr) {
        this.xmr = xmr;
    }

    public CoinObject1 getZec() {
        return zec;
    }

    public void setZec(CoinObject1 zec) {
        this.zec = zec;
    }

    public CoinObject1 getXrp() {
        return xrp;
    }

    public void setXrp(CoinObject1 xrp) {
        this.xrp = xrp;
    }
}

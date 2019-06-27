package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueCurrency2 {

    @JsonProperty("USD")
    public double usd;

    @JsonProperty("USDT")
    public double usdt;

    @JsonProperty("BTC")
    public double btc;

    @JsonProperty("ETH")
    public double eth;

    @JsonProperty("EUR")
    public double eur;

    @JsonProperty("GBP")
    public double gbp;

    @JsonProperty("JPY")
    public double jpy;

    @JsonProperty("KRW")
    public double krw;

    @JsonProperty("BYN")
    public double byn;

    @JsonProperty("RUR")
    public double rur;

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getUsdt() {
        return usdt;
    }

    public void setUsdt(double usdt) {
        this.usdt = usdt;
    }

    public double getBtc() {
        return btc;
    }

    public void setBtc(double btc) {
        this.btc = btc;
    }

    public double getEth() {
        return eth;
    }

    public void setEth(double eth) {
        this.eth = eth;
    }

    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public double getGbp() {
        return gbp;
    }

    public void setGbp(double gbp) {
        this.gbp = gbp;
    }

    public double getJpy() {
        return jpy;
    }

    public void setJpy(double jpy) {
        this.jpy = jpy;
    }

    public double getKrw() {
        return krw;
    }

    public void setKrw(double krw) {
        this.krw = krw;
    }

    public double getByn() {
        return byn;
    }

    public void setByn(double byn) {
        this.byn = byn;
    }

    public double getRur() {
        return rur;
    }

    public void setRur(double rur) {
        this.rur = rur;
    }

}

package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinObject1 {

    @JsonProperty("USD")
    private double usd;

    @JsonProperty("USDT")
    private double usdt;

    @JsonProperty("BTC")
    private double btc;

    @JsonProperty("ETH")
    private double eth;

    @JsonProperty("EUR")
    private double eur;

    @JsonProperty("GBP")
    private double gbp;

    @JsonProperty("JPY")
    private double jpy;

    @JsonProperty("KRW")
    private double krw;

    @JsonProperty("BYN")
    private double byn;

    @JsonProperty("RUR")
    private double rur;

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

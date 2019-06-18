package com.epam.tat21.crypto.bo;

public enum  Currency {

    USD("USD"),
    USDT("USDT"),
    BTC("BTC"),
    ETH("ETH"),
    EUR("EUR"),
    GBP("GBP"),
    JPY("JPY"),
    KRW("KRW"),
    BYN("BYN"),
    RUR("RUR");

    private String nameCurrency;

    Currency(String nameCoin) {
        this.nameCurrency = nameCoin;
    }

    public String getNameOfCoin() {
        return nameCurrency;
    }
}

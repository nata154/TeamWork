package com.epam.tat21.crypto.ui.businessObjects;

public enum Currency {

    USD("USD"),
    USDT("USDT"),
    BTC("BTC"),
    ETH("ETH"),
    EUR("EUR"),
    GBP("GBP"),
    JPY("JPY"),
    KRW("KRW");

    private String nameCurrency;

    Currency(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }

    public String getNameOfCurrency() {
        return nameCurrency;
    }
}

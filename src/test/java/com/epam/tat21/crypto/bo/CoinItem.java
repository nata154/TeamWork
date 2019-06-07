package com.epam.tat21.crypto.bo;

public class CoinItem {

    private String coinName;
    private String coinTotalValue;

    public CoinItem(String coinName, String coinTotalValue) {
        this.coinName = coinName;
        this.coinTotalValue = coinTotalValue;
    }

    public String getCoinName() {
        return coinName;
    }

    public String getCoinTotalValue() {
        return coinTotalValue;
    }
}

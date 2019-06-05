package com.epam.tat21.crypto.utils;

public class SimpleCoin {

    private String coinName="Default Coin";
    private double coinTotalValue = 0;

    public SimpleCoin(String name, Double value) {
        setCoinName(name);
        setCoinTotalValue(value);
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public double getCoinTotalValue() {
        return coinTotalValue;
    }

    public void setCoinTotalValue(double coinTotalValue) {
        this.coinTotalValue = coinTotalValue;
    }

    public void coinReport() {
        System.out.println("COIN NAME - "+getCoinName());
        System.out.println("COIN TOTAL VALUE - "+getCoinTotalValue());
        System.out.println("=========================================");
    }

}

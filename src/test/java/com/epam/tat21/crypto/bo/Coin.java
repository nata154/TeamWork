package com.epam.tat21.crypto.bo;

public enum Coin {

    BTC("Bitcoin", "BTC"),
    ETH("Ethereum", "ETH"),
    LTC("Litecoin", "LTC"),
    XMR("Monero", "XMR"),
    ZEC("ZCash", "ZEC"),
    XRP("XRP", "XRP");

    private String nameCoin;
    private String abbreviationCoin;

    Coin(String nameCoin, String abbreviationCoin) {
        this.nameCoin = nameCoin;
        this.abbreviationCoin = abbreviationCoin;
    }

    public String getNameOfCoin() {
        return nameCoin;
    }

    public String getAbbreviationCoin() {
        return abbreviationCoin;
    }
}

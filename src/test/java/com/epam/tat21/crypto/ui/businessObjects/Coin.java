package com.epam.tat21.crypto.ui.businessObjects;

import com.epam.tat21.crypto.exceptions.NotProperCoinTypeException;
import com.epam.tat21.crypto.ui.utils.CoinInformationParser;

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

    public synchronized String getAbbreviationCoin() {
        return abbreviationCoin;
    }

    public static Coin setProperCoinEnum(String coinName) throws NotProperCoinTypeException {
        for (Coin currentCoinEnum : Coin.values()) {
            if (CoinInformationParser.parseStrictCoinName(coinName).equals(currentCoinEnum.getNameOfCoin())) {
                return currentCoinEnum;
            }
        }
        throw new NotProperCoinTypeException();
    }
}

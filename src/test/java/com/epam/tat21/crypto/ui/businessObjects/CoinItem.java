package com.epam.tat21.crypto.ui.businessObjects;

public class CoinItem {

    private Coin coinEnum;
    private String coinTotalValue;

    public CoinItem(String coinName, String coinTotalValue) {
        this.coinTotalValue = coinTotalValue;
        coinEnum = Coin.setProperCoinEnum(coinName);
    }

    public String getCoinName() {
        return coinEnum.getNameOfCoin();
    }

    public String getCoinTotalValue() {
        return coinTotalValue;
    }

    public String getCoinType() {
        return coinEnum.getAbbreviationCoin();
    }
}

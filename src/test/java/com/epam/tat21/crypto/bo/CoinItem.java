package com.epam.tat21.crypto.bo;

import org.apache.commons.lang3.StringUtils;

public class CoinItem {

    private String coinName;
    private String coinType;
    private String coinTotalValue;


    public CoinItem(String coinName, String coinTotalValue) {
        this.coinName = coinName;
        this.coinTotalValue = coinTotalValue;
        this.coinType = StringUtils.substringBetween(coinName, "(", ")");
    }

    public String getCoinName() {
        return coinName;
    }

    public String getCoinTotalValue() {
        return coinTotalValue;
    }

    public String getCoinType() {
        return coinType;
    }
}

package com.epam.tat21.crypto.bo;

import com.epam.tat21.crypto.exceptions.NotProperCoinTypeException;
import com.epam.tat21.crypto.utils.MyLogger;

public class CoinItem {

    private Coin coinEnum;
    private String coinTotalValue;

    public CoinItem(String coinName, String coinTotalValue) {
        this.coinTotalValue = coinTotalValue;

        try {
            coinEnum = Coin.setProperCoinEnum(coinName);
        } catch (NotProperCoinTypeException ex) {
            MyLogger.error(ex.getMessage());
        }
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

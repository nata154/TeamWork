package com.epam.tat21.crypto.api.model;

import com.epam.tat21.crypto.bo.Currency;

import java.util.List;

public class CurrencyForCoin {
    private List<Currency> currencyList;

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}

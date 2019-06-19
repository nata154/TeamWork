package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.tests.CommonConditions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MatchingCoinValueInCurrencyWithCommonCurrencyTest extends CommonConditions {


    @Test
    public void matchingCoinValueInCurrencyWithCommonCurrencyTest() {

        List<Coin> coins = new ArrayList<Coin>();
        List<Currency> currencies = new ArrayList<Currency>();

        Coin coinLTC = Coin.LTC;
        Coin coinBTC = Coin.BTC;
        Currency currencyEUR = Currency.EUR;
        Currency currencyJPY = Currency.JPY;

        coins.add(coinLTC);
        coins.add(coinBTC);
        currencies.add(currencyEUR);
        currencies.add(currencyJPY);

        steps.loginUser();
        steps.openCoinsPage();

        steps.getActualCurrencyForCoin(coins, currencies);
        //apiSteps.getResponseWithCoinCostInCurrency();

    }
}


package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.tests.CommonConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchingCoinValueInCurrencyWithCommonCurrencyTest extends CommonConditions {

    @Test
    public void matchingCoinValueInCurrencyWithCommonCurrencyTest() throws IOException {

        List<Coin> coins = new ArrayList<Coin>();
        List<Currency> currencies = new ArrayList<Currency>();
        coins.add(Coin.LTC);
        coins.add(Coin.BTC);
        currencies.add(Currency.EUR);
        currencies.add(Currency.JPY);

        steps.openCoinsPage();
        steps.getActualCurrencyForCoin(coins, currencies);
        apiSteps.getResponseWithCoinCostInCurrency();
        apiSteps.getCoinsValueInCurrencyItemsAsArray();

        String[] coinsValuesFromResponse = apiSteps.getCoinsValueInCurrencyItemsAsArray();
        String[] coinsValuesFromPage = steps.generateStringArrayFromMap();

        Assert.assertTrue(Arrays.equals(coinsValuesFromResponse, coinsValuesFromPage));

    }
}


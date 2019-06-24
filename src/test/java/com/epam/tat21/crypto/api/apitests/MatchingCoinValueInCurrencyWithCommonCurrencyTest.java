package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.tests.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatchingCoinValueInCurrencyWithCommonCurrencyTest extends CommonConditions {

    @JIRATestKey(key = "EPMFARMATS-9340")
    @Test
    public void matchingCoinValueInCurrencyWithCommonCurrencyTest() throws IOException {

        List<Coin> coins = new ArrayList<Coin>();
        List<Currency> currencies = new ArrayList<Currency>();
        coins.add(Coin.LTC);
        //coins.add(Coin.BTC);
        currencies.add(Currency.EUR);
        currencies.add(Currency.JPY);

        steps.openCoinsPage();
        steps.getActualCurrencyForCoin(coins, currencies);


        System.out.println(apiSteps.getCoinsValueInCurrency(apiSteps.getResponseWithCoinCostInCurrency()).getBtc().getEur() + "//////////////////");
        System.out.println(apiSteps.getCoinsValueInCurrency(apiSteps.getResponseWithCoinCostInCurrency()).getBtc().getJpy() + "//////////////////");


//        apiSteps.getCoinsValueInCurrencyItemsAsArray();

//        String[] coinsValuesFromResponse = apiSteps.getCoinsValueInCurrencyItemsAsArray();
        // String[] coinsValuesFromPage = steps.generateStringArrayFromMap();

        // Assert.assertTrue(Arrays.equals(coinsValuesFromResponse, coinsValuesFromPage));

    }
}


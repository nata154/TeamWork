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
        coins.add(Coin.BTC);
        coins.add(Coin.XRP);
        currencies.add(Currency.EUR);
        currencies.add(Currency.JPY);

        String resultCoinsForQuery = apiSteps.getResultCoinsForQuery(coins);
        String resultCurrenciesForQuery = apiSteps.getResultCurrenciesForQuery(currencies);
        String query = apiSteps.getQueryForMatchingCurrenciesOfCoins(resultCoinsForQuery, resultCurrenciesForQuery);
        System.out.println("---------------");
        System.out.println(query);
        System.out.println("---------------");

        steps.openCoinsPage();
        steps.getActualCurrencyForCoin(coins, currencies);


        //System.out.println(apiSteps.getCoinsValueInCurrency(apiSteps.getResponseWithCoinCostInCurrency(query)).getBtc().getEur() + "//////////////////");
        System.out.println(apiSteps.getCoinsValueInCurrency(apiSteps
                .getResponseWithCoinCostInCurrency(query))
                .getCurrencyMap()
                .get(Currency.EUR) + "//////////////////");


//        apiSteps.getCoinsValueInCurrencyItemsAsArray();

//        String[] coinsValuesFromResponse = apiSteps.getCoinsValueInCurrencyItemsAsArray();
        // String[] coinsValuesFromPage = steps.generateStringArrayFromMap();

        // Assert.assertTrue(Arrays.equals(coinsValuesFromResponse, coinsValuesFromPage));

    }
}

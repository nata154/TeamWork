package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.tests.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MatchingCoinValueInCurrencyWithCommonCurrencyTest extends CommonConditions {

    @JIRATestKey(key = "EPMFARMATS-9340")
    @Test
    public void matchingCoinValueInCurrencyWithCommonCurrencyTest() throws IOException {

        List<Coin> coins = Arrays.asList(Coin.values());
        List<Currency> currencies = Arrays.asList(Currency.values());

        Map<String, Map<String, Double>> multiPricesFromPageAsArray = steps.openCoinsPage().selectCurrencyAndGetCostForCoins(coins, currencies);

        String coinAbbreviations = apiSteps.getResultCoinsForQuery(coins);
        String currencyAbbreviations = apiSteps.getResultCurrenciesForQuery(currencies);
        Map<String, Map<String, Double>> multiPriceResponseAsArray = apiSteps.getMultiPriceResponseAsArray(coinAbbreviations, currencyAbbreviations).getPrices();

        boolean resultCompareMaps = apiSteps.compareMultiPricesWithDelta(multiPricesFromPageAsArray, multiPriceResponseAsArray, coins, currencies);

        Assert.assertTrue(resultCompareMaps);
    }
}

package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.tests.CommonConditions;
import com.epam.tat21.crypto.utils.DataVerification;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MatchingCoinsMultiPricesTest extends CommonConditions {

    @JIRATestKey(key = "EPMFARMATS-9340")
    @Test
    public void matchingCoinValueInCurrencyTest() throws IOException {
        List<Coin> coins = Arrays.asList(Coin.values());
        List<Currency> currencies = Arrays.asList(Currency.values());

        Map<String, Map<String, Double>> multiPricesFromPage = steps.openCoinsPage().selectCurrencyAndGetCostForCoins(coins, currencies);

        String coinAbbreviationsForRequest = apiSteps.getResultCoinsForQuery(coins);
        String currencyAbbreviationsForRequest = apiSteps.getResultCurrenciesForQuery(currencies);
        Map<String, Map<String, Double>> multiPriceResponse = apiSteps.getCoinsMultiPriceByCurrency(coinAbbreviationsForRequest, currencyAbbreviationsForRequest).getPrices();

        boolean areMapsEqualAccordingDelta = DataVerification.compareMultiPricesWithDelta(multiPricesFromPage, multiPriceResponse, coins, currencies, 2);

        Assert.assertTrue(areMapsEqualAccordingDelta, "Difference between current and expected values for one of the coins is unacceptable");
    }
}

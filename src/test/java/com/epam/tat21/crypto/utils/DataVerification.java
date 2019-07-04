package com.epam.tat21.crypto.utils;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DataVerification {

    private DataVerification() {
    }

    public static boolean compareMultiPricesWithDelta(Map<String, Map<String, Double>> multiPricesFromPage, Map<String, Map<String, Double>> multiPriceResponse, List<Coin> coins, List<Currency> currencies, double deltaExpected) {
        boolean resultCompareMaps = true;
        for (Coin coin : coins) {
            for (Currency currency : currencies) {
                String coinAbbreviation = coin.getAbbreviationCoin();
                String currencyAbbreviation = currency.getNameOfCurrency();

                double coinCostInCurrencyFromPage = multiPricesFromPage.get(coinAbbreviation).get(currencyAbbreviation);
                double coinCostInCurrencyFromResponse = multiPriceResponse.get(coinAbbreviation).get(currencyAbbreviation);
                double deltaActual = ((Math.abs(coinCostInCurrencyFromPage - coinCostInCurrencyFromResponse)) / coinCostInCurrencyFromPage) * 100;

                BigDecimal deltaActualRounded = BigDecimal.valueOf(deltaActual).setScale(4, BigDecimal.ROUND_HALF_UP);

                MyLogger.info("Difference between current and expected values for " + coinAbbreviation + " in currency " + currencyAbbreviation + " is: " + deltaActualRounded + " %.");
                if (deltaActual > deltaExpected) {
                    MyLogger.error("For " + coinAbbreviation + " in currency " + currencyAbbreviation + " difference between current and expected values is: " + deltaActualRounded + ". But expected no more than " + deltaExpected + " %.");
                    resultCompareMaps = false;
                }
            }
        }
        return resultCompareMaps;
    }

}

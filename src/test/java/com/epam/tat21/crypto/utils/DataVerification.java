package com.epam.tat21.crypto.utils;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DataVerification {

    public static boolean compareMultiPricesWithDelta(Map<String, Map<String, Double>> multiPricesFromPageAsArray, Map<String, Map<String, Double>> multiPriceResponseAsArray, List<Coin> coins, List<Currency> currencies, double deltaExpected) {
        boolean resultCompareMaps = true;
        for (int i = 0; i < coins.size(); i++) {
            for (int j = 0; j < currencies.size(); j++) {
                String coinAbbreviation = coins.get(i).getAbbreviationCoin();
                String currencyAbbreviation = currencies.get(j).getNameOfCurrency();

                double coinCostInCurrencyFromPage = multiPricesFromPageAsArray.get(coinAbbreviation).get(currencyAbbreviation);
                double coinCostInCurrencyFromResponse = multiPriceResponseAsArray.get(coinAbbreviation).get(currencyAbbreviation);
                double deltaActual = ((Math.abs(coinCostInCurrencyFromPage - coinCostInCurrencyFromResponse)) / coinCostInCurrencyFromPage) * 100;

                BigDecimal deltaActualRounded = new BigDecimal(deltaActual).setScale(4, BigDecimal.ROUND_HALF_UP);

                MyLogger.info("Difference between current and expected values for " + coinAbbreviation + " in currency " + currencyAbbreviation + " is: " + deltaActualRounded + ".");
                if (deltaActual > deltaExpected) {
                    MyLogger.error("For " + coinAbbreviation + " in currency " + currencyAbbreviation + " difference between current and expected values is: " + deltaActualRounded + ". But expected no more than " + deltaExpected + " %.");
                    resultCompareMaps = false;
                }
            }
        }
        return resultCompareMaps;
    }

}

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
                double coinCostInCurrencyFromPage = multiPricesFromPageAsArray.get(coins.get(i).getAbbreviationCoin()).get(currencies.get(j).getNameOfCurrency());
                double coinCostInCurrencyFromResponse = multiPriceResponseAsArray.get(coins.get(i).getAbbreviationCoin()).get(currencies.get(j).getNameOfCurrency());
                double deltaActual = ((Math.abs(coinCostInCurrencyFromPage - coinCostInCurrencyFromResponse)) / coinCostInCurrencyFromPage) * 100;

                BigDecimal bigDecimal = new BigDecimal(deltaActual);
                BigDecimal deltaActualRounded = bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);

                MyLogger.info("Difference between current and expected values for " + coins.get(i).getAbbreviationCoin() + " in currency " + currencies.get(j).getNameOfCurrency() + " is: " + deltaActualRounded + ".");
                if (deltaActual > deltaExpected) {
                    MyLogger.error("For " + coins.get(i).getAbbreviationCoin() + " in currency " + currencies.get(j).getNameOfCurrency() + " difference between current and expected values is: " + deltaActualRounded + ". But expected no more than " + deltaExpected + " %.");
                    resultCompareMaps = false;
                }
            }
        }
        return resultCompareMaps;
    }

}

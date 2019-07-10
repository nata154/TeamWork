package com.epam.tat21.crypto.ui.utils;

import org.apache.commons.lang3.StringUtils;

public class CoinInformationParser {

    private CoinInformationParser() {
    }

    public static double parseTotalCoinValue(String valueExpression) {

        double finalValue = 0;

        if (!valueExpression.isEmpty()) {
            // matches numbers like $ 8785.25 M
            if (valueExpression.matches("(\\W{2}\\d*\\.\\d*\\sM)")) {
                finalValue = (Double.parseDouble(valueExpression.substring(2, valueExpression.length() - 2))) * 1000000;

                // matches numbers like $ 385.25 k
            } else if (valueExpression.matches("(\\W{2}\\d*\\.\\d*\\sk)")) {
                finalValue = (Double.parseDouble(valueExpression.substring(2, valueExpression.length() - 2))) * 1000;

                // matches numbers like $ 1,005.25
            } else if (valueExpression.matches("(\\W{2}\\d*\\,\\d{3}\\.\\d{2})")) {
                finalValue = (Double.parseDouble((valueExpression.substring(2, valueExpression.length() - 1)).replace(",", "")));

                // matches numbers like $ 1,005,500.25
            } else if (valueExpression.matches("\\W{2}\\d*\\,\\d{3}\\,\\d{3}\\.\\d*")) {
                finalValue = (Double.parseDouble((valueExpression.substring(2, valueExpression.length())).replace(",", "")));

                // for numbers like $ 64.55
            } else {
                finalValue = Double.parseDouble(valueExpression.substring(2, valueExpression.length() - 1));
            }
        }

        return finalValue;
    }

    public static String parseStrictCoinName(String coinNameFromWebsite) {
        return coinNameFromWebsite.substring(0, coinNameFromWebsite.length() -
                (StringUtils.substringBetween(coinNameFromWebsite, "(", ")").length() + 3));
    }

}

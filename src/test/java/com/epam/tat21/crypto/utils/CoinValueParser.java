package com.epam.tat21.crypto.utils;

public class CoinValueParser {

    private CoinValueParser() {
    }

    public static double parseTotalCoinValue(String valueExpression) {

        double finalValue = 0;

        if (!valueExpression.isEmpty()) {
            if (valueExpression.matches("(\\W{2}\\d*\\.\\d*\\sM)")) {
                finalValue = (Double.parseDouble(valueExpression.substring(2, valueExpression.length() - 2))) * 1000000;
            } else if (valueExpression.matches("(\\W{2}\\d*\\.\\d*\\sk)")) {
                finalValue = (Double.parseDouble(valueExpression.substring(2, valueExpression.length() - 2))) * 1000;
            } else if (valueExpression.matches("(\\W{2}\\d*\\,\\d{3}\\.\\d{2})")) {
                finalValue = (Double.parseDouble((valueExpression.substring(2, valueExpression.length() - 1)).replace(",", "")));
            } else {
                finalValue = Double.parseDouble(valueExpression.substring(2, valueExpression.length() - 1));
            }
        }

        return finalValue;
    }
}
package com.epam.tat21.crypto.utils;

public class CoinParser {

    public CoinParser() {}

    public double parseTotalCoinValue(String valueExpression) {

        double finalValue = 0;

        if (valueExpression.length()>1) {
            String lastSymbol = valueExpression.substring(valueExpression.length()-1);

            switch (lastSymbol) {
                case "M": {
                    finalValue = (Double.parseDouble(valueExpression.substring(2, valueExpression.length() - 2)))*1000000;
                    break;
                }
                case "k": {
                    finalValue = (Double.parseDouble(valueExpression.substring(2, valueExpression.length() - 2)))*1000;
                    break;
                }
                case "0": {
                    finalValue = (Double.parseDouble((valueExpression.substring(2, valueExpression.length() - 3)).replace(",", ".")))*1000;
                    break;
                }
                default: {
                    finalValue = finalValue * 1;
                }
            }
        } else {
            System.out.println();
        }
        return finalValue;
    }

}

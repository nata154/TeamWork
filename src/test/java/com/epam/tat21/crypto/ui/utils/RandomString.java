package com.epam.tat21.crypto.ui.utils;

import java.util.Random;

public class RandomString {
    private static final String ALFANUMERICAL_ALL_CAPS = "0123456789ABCDEFGHIJKLMOPQRSTUVWXYZ";
    private static Random random = new Random();

    private RandomString() {
    }

    public static synchronized String getRandomString(int stringLength) {
        StringBuilder stringBuilder = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++)
        {
            stringBuilder.append(ALFANUMERICAL_ALL_CAPS.charAt(random.nextInt(ALFANUMERICAL_ALL_CAPS.length())));
        }
        return stringBuilder.toString();
    }

    public static synchronized String getRandomNumber(int limit) {
		int num = random.nextInt(limit);
		return String.valueOf(num);
	}

    public static String generateRandomUserFirstName(int postfixLength){
        return "userFirstName_".concat(getRandomString(postfixLength));
    }

    public static String generateRandomUserSurname(int postfixLength){
        return "surname_".concat(getRandomString(postfixLength));
    }

    public static String generateRandomPortfolioName(int postfixLength){
        return "portfolio_".concat(getRandomString(postfixLength));
    }

}

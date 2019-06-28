package com.epam.tat21.crypto.data;

import com.epam.tat21.crypto.bo.Coin;
import org.testng.annotations.DataProvider;

public class CoinsDataProvider {

    private CoinsDataProvider() {
    }

    @DataProvider(name = "coinsForTests")
    public static Object[][] coinNameForFilter() {

        Object[][] toReturn = new Object[Coin.values().length][];
        int i = 0;
        for (Coin coin : Coin.values()) {
            toReturn[i] = new Object[1];
            toReturn[i][0] = coin;
            i++;
        }
        return toReturn;
    }

}

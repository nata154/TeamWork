package com.epam.tat21.crypto.ui.data;

import com.epam.tat21.crypto.ui.businessObjects.Countries;
import org.testng.annotations.DataProvider;

public class CountriesDataProvider {

    private CountriesDataProvider() {
    }

    @DataProvider(name = "countriesForTests")
    public static Object[][] countryNameForFilter() {

        Object[][] toReturn = new Object[Countries.values().length][];
        int i = 0;
        for (Countries country : Countries.values()) {
            toReturn[i] = new Object[1];
            toReturn[i][0] = country;
            i++;
        }
        return toReturn;
    }

}

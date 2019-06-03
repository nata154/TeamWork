package com.epam.tat21.crypto.data;

import com.epam.tat21.crypto.bo.Countries;
import org.testng.annotations.DataProvider;

public class CountriesDataProvider {

    @DataProvider(name = "countriesForTests")
    public static Object[][] countryNameForFilter(){
        return new Object[][] {
                {Countries.BRAZIL},
                {Countries.DENMARK},
                {Countries.JAPAN},
                {Countries.RUSSIA},
                {Countries.SINGAPORE},
                {Countries.UK}
        };
    }
}

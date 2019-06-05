package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.bo.Countries;
import com.epam.tat21.crypto.data.CountriesDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountryFilterOnExchangePageTests extends CommonConditions {

    @Test(dataProvider = "countriesForTests", dataProviderClass = CountriesDataProvider.class)
    public void isExchangesNumberOnFilteredPageEqualNumberFromBadgeTest(Countries country) {
        steps.openExchangePage();
        steps.filterByCountry(country);
        int numberOfResultWithCountry = steps.getFromFilteredPageNumberOfResultsWith(country);
        int numberFromCountryBadge = steps.getNumberOfExchangesFromCountryBadge();
        Assert.assertEquals(numberOfResultWithCountry, numberFromCountryBadge);
    }

    @Test(dataProvider = "countriesForTests", dataProviderClass = CountriesDataProvider.class)
    public void isFilteredPageContainsOnlyExchangesFromNeededCountryTest(Countries country) {
        steps.openExchangePage();
        steps.filterByCountry(country);
        int numberOfResultWithCountry = steps.getFromFilteredPageNumberOfResultsWith(country);
        int numberOfAllCountryLabels = steps.getAllCountryLabelsFromFilteredPage();
        Assert.assertEquals(numberOfResultWithCountry, numberOfAllCountryLabels);
    }


}

package com.epam.tat21.crypto.ui.tests;

import com.epam.tat21.crypto.ui.businessObjects.Countries;
import com.epam.tat21.crypto.ui.data.CountriesDataProvider;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountryFilterOnExchangePageTests extends CommonConditions {

    @JIRATestKey(key = "EPMFARMATS-9116")
    @Test(dataProvider = "countriesForTests", dataProviderClass = CountriesDataProvider.class)
    public void isExchangesNumberOnFilteredPageEqualNumberFromBadgeTest(Countries country) {
        steps.openExchangePage();
        steps.filterByCountry(country);
        int numberOfResultWithCountry = steps.getFromFilteredPageNumberOfResultsWith(country);
        int numberFromCountryBadge = steps.getNumberOfExchangesFromCountryBadge();
        Assert.assertEquals(numberOfResultWithCountry, numberFromCountryBadge);
    }

    @JIRATestKey(key = "EPMFARMATS-9115")
    @Test(dataProvider = "countriesForTests", dataProviderClass = CountriesDataProvider.class)
    public void isFilteredPageContainsOnlyExchangesFromNeededCountryTest(Countries country) {
        steps.openExchangePage();
        steps.filterByCountry(country);
        int numberOfResultWithCountry = steps.getFromFilteredPageNumberOfResultsWith(country);
        int numberOfAllCountryLabels = steps.getAllCountryLabelsFromFilteredPage();
        Assert.assertEquals(numberOfResultWithCountry, numberOfAllCountryLabels);
    }


}

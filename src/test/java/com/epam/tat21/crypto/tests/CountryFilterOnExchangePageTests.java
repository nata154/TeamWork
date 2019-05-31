package com.epam.tat21.crypto.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CountryFilterOnExchangePageTests extends CommonConditions{

    @Test
    public void isExchangesNumberOnFilteredPageEqualNumberFromBadgeTest() {
        Assert.assertTrue(steps.isExchangesNumberOnFilteredPageEqualNumberFromBadge());
    }

    @Test
    public void isFilteredPageContainsOnlyExchangesFromNeededCountryTest() {
        Assert.assertTrue(steps.isFilteredPageContainsOnlyExchangesFromNeededCountry());
    }
}

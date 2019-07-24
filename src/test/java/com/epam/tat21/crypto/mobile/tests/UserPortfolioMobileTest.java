package com.epam.tat21.crypto.mobile.tests;

import com.epam.jira.JIRATestKey;
import com.epam.tat21.crypto.mobile.service.PreConditionsOfMobileTest;
import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.utils.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserPortfolioMobileTest extends PreConditionsOfMobileTest {
    private static final int COUNT_OF_SYMBOLS = 5;
    private Coin coin = Coin.BTC;
    private String currency = coin.getAbbreviationCoin();

    @JIRATestKey(key = "EPMFARMATS-9649")
    @Test
    public void portfolioCreationTest() {
        String description = RandomString.getRandomString(COUNT_OF_SYMBOLS);
        String portfolioName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
        mobSteps.loginUser();
        mobSteps.createUserPortfolio(portfolioName, currency, description);
        Assert.assertTrue(mobSteps.isPortfolioPresent(portfolioName));
    }

    @JIRATestKey(key = "EPMFARMATS-9649")
    @Test(dependsOnMethods = {"portfolioCreationTest"})
    public void portfolioEditingTest() {
        String changedName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
        mobSteps.changeUserPortfolioName(changedName);
        Assert.assertTrue(mobSteps.isPortfolioPresent(changedName));
    }

    @JIRATestKey(key = "EPMFARMATS-9649")
    @Test(dependsOnMethods = {"portfolioEditingTest"})
    public void portfolioDeletingTest() {
        mobSteps.deleteUserPortfolio();
        Assert.assertTrue(mobSteps.isPortfolioDelete());
    }
}

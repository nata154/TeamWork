package com.epam.tat21.crypto.mobile.tests;

import com.epam.tat21.crypto.mobile.service.PreConditionsOfMobileTest;
import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.utils.RandomString;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserPortfolioMobileTest extends PreConditionsOfMobileTest {

    private static final int COUNT_OF_SYMBOLS = 5;
    private Coin coin = Coin.BTC;
    private String currency = coin.getAbbreviationCoin();
    String changedName = RandomString.getRandomString(COUNT_OF_SYMBOLS);

    @JIRATestKey(key = "EPMFARMATS-9649")
    @Test
    public void portfolioCreationTest() {
        String description = RandomString.getRandomString(COUNT_OF_SYMBOLS);
        String portfolioName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
        mobSteps.loginUser();
        mobSteps.createUserPortfolio(portfolioName, currency, description);
        Assert.assertEquals(mobSteps.getNameOfPortfolio(), portfolioName, "Wrong name of portfolio while creating it!");
    }

    @JIRATestKey(key = "EPMFARMATS-9649")
    @Test(dependsOnMethods = {"portfolioCreationTest"})
    public void portfolioEditingTest() {
        mobSteps.changeUserPortfolioName(changedName);
        Assert.assertEquals(mobSteps.getNameOfPortfolio(), changedName, "Wrong name of portfolio while editing it!");
    }

    @JIRATestKey(key = "EPMFARMATS-9649")
    @Test(dependsOnMethods = {"portfolioEditingTest"})
    public void portfolioDeletingTest() {
        mobSteps.deleteUserPortfolio();
        Assert.assertTrue(mobSteps.isPortfolioDeleted(changedName), "Issue while deleting portfolio - portfolio with such name still exists.");
    }
}

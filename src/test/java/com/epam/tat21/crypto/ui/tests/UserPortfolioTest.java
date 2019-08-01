package com.epam.tat21.crypto.ui.tests;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.service.CommonConditions;
import com.epam.tat21.crypto.ui.utils.RandomString;
import com.epam.testng.JIRATestKey;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class UserPortfolioTest extends CommonConditions {

	private static final int COUNT_OF_SYMBOLS = 5;
	private Coin coin = Coin.BTC;
	private String currency = coin.getAbbreviationCoin();
    private static final String description = RandomString.getRandomString(COUNT_OF_SYMBOLS);
    private static final String portfolioName = RandomString.getRandomString(COUNT_OF_SYMBOLS);

    @BeforeClass
    public void logIn() {
        steps.loginUser();
    }

	@JIRATestKey(key = "EPMFARMATS-9258")
	@Test
	public void portfolioCreationTest() {
		steps.createUserPortfolio(portfolioName, currency, description);
		assertTrue(steps.isPortfolioPresent(portfolioName));
        steps.deleteUserPortfolio();
	}

	@JIRATestKey(key = "EPMFARMATS-9266")
    @Test
	public void portfolioEditingTest() {
        steps.createUserPortfolio(portfolioName, currency, description);
		String changedName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
		steps.changeUserPortfolioName(changedName);
		assertTrue(steps.isPortfolioPresent(changedName));
        steps.deleteUserPortfolio();
	}

	@JIRATestKey(key = "EPMFARMATS-9264")
    @Test
	public void portfolioDeletingTest() {
        steps.createUserPortfolio(portfolioName, currency, description);
		steps.deleteUserPortfolio();
		assertTrue(steps.isPortfolioDelete());
	}
}


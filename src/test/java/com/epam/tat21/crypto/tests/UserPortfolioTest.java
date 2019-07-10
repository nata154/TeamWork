package com.epam.tat21.crypto.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.utils.RandomString;
import com.epam.testng.JIRATestKey;


public class UserPortfolioTest extends CommonConditions{

	private static final int COUNT_OF_SYMBOLS = 5;
	private String portfolioName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
	private String changedName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
	private Coin coin = Coin.BTC;
	private String currency = coin.getAbbreviationCoin();
	
	@JIRATestKey(key = "EPMFARMATS-9258")
	@Test
	public void portfolioCreationTest() {
		String description = RandomString.getRandomString(COUNT_OF_SYMBOLS);
		steps.loginUser();
		steps.createUserPortfolio(portfolioName, currency, description);
		assertTrue(steps.isPortfolioPresent(portfolioName));	
	}
	
	@JIRATestKey(key = "EPMFARMATS-9266")
	@Test(dependsOnMethods = { "portfolioCreationTest" })
	public void portfolioEditingTest() {
		 steps.changeUserPortfolioName(changedName);
		 assertTrue(steps.isPortfolioPresent(changedName));
	}
	
	@JIRATestKey(key = "EPMFARMATS-9264")
	@Test(dependsOnMethods = { "portfolioEditingTest" })
	public void portfolioDeletingTest() {
		steps.deleteUserPortfolio();
		assertTrue(steps.isPortfolioDelete());
	}
}

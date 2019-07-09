package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.utils.MyLogger;
import com.epam.tat21.crypto.utils.RandomString;
import com.epam.testng.JIRATestKey;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CoinInUserPortfolioTest extends CommonConditions {
	
	private static final int COUNT_OF_SYMBOLS = 5;
	private static final int LIMIT = 1000;
	private String portfolioName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
	private Coin coin = Coin.BTC;
	private String currency = coin.getAbbreviationCoin();
	private String amount = RandomString.getRandomNumber(LIMIT);
	private String changedAmount = RandomString.getRandomNumber(LIMIT);
	private String price = RandomString.getRandomNumber(LIMIT);
	
	@BeforeClass
	public void portfolioCreation() {
		String description = RandomString.getRandomString(COUNT_OF_SYMBOLS);
		steps.loginUser();
		steps.createUserPortfolio(portfolioName, currency, description);
		MyLogger.info("CoinInUserPortfolioTest before class");
	}
	
	@AfterClass
    public void portfolioDeleting() {
		steps.deleteUserPortfolio();
		MyLogger.info("CoinInUserPortfolioTest after class");
    }
	
	@JIRATestKey(key = "EPMFARMATS-9267")
	@Test
	public void coinAddingTest() {
		steps.addCoinToUserPortfolio(coin, amount, price);
		assertTrue(steps.isCoinAdded());
	}
	
	@JIRATestKey(key = "EPMFARMATS-9271")
	@Test(dependsOnMethods = { "coinAddingTest" })
	public void coinEditingTest() { 
		steps.changeAmountOfCoins(changedAmount);
		assertTrue(steps.isAmountOfCoinChanged());
	}
	
	@JIRATestKey(key = "EPMFARMATS-9268")
	@Test(dependsOnMethods = { "coinEditingTest" })
	public void coinDeletingTest() {
		steps.deleteCoinFromUserPortfolio();
		assertTrue(steps.isCoinDelete());
	}
}

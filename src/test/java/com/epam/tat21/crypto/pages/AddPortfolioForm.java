package com.epam.tat21.crypto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.tat21.crypto.service.TestDataReader;

public class AddPortfolioForm extends HeaderPage {
	private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";

	@FindBy(xpath = " //input[@ng-model='newPortfolio.Name']")
	private WebElement inputPortfolioName;

	@FindBy(xpath = "(//span[contains(text(),'USD')])[2]")
	private WebElement dropdownCurrency;

	@FindBy(xpath = "//textarea[@ng-model='newPortfolio.Description']")
	private WebElement textareaDiscription;

	@FindBy(xpath = "//span[text()=' Private Portfolio ']")
	private WebElement radiobuttonPrivatePortfolio;

	@FindBy(xpath = "//span[text()=' Public Portfolio ']")
	private WebElement radiobuttonPublicPortfolio;

	@FindBy(xpath = "//span[contains(text(), 'Create')]")
	private WebElement buttonCreate;

	@FindBy(xpath = "//md-icon[@aria-label='Close dialog']")
	private WebElement buttonCloseFormAddPortfolio;

	public AddPortfolioForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public AddPortfolioForm openPage() {
		driver.navigate().to(BASE_URL);
		return this;
	}

	public WebElement getElementCurrency(String currency) {
		String xpathForGetCurrency = "(//md-option[@value='" + currency + "']/div[1]/span)[2]";
		return driver.findElement(By.xpath(xpathForGetCurrency));
	}

	public AddPortfolioForm createNewPortfolio(String name, String currency, String description) {
		waitForElementVisible(inputPortfolioName);
		inputPortfolioName.sendKeys(name);
		dropdownCurrency.click();
		WebElement getCurrency = getElementCurrency(currency);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getCurrency);
		textareaDiscription.sendKeys(description);
		buttonCreate.click();
		return this;
	}

}

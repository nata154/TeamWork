package com.epam.tat21.crypto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.tat21.crypto.service.TestDataReader;

public class PortfolioPage extends BasePage {

	private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";

	@FindBy(xpath = "//button[@ng-click='addPortfolioDialog()']")
	private WebElement buttonAddPortfolio;

	public PortfolioPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public PortfolioPage openPage() {
		driver.get(BASE_URL);
		return this;
	}

	public AddPortfolioForm addPortfolioForm() {
		waitForElementClicable(buttonAddPortfolio);
		buttonAddPortfolio.click();
		return new AddPortfolioForm(driver);
	}
}

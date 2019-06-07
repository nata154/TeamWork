package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Countries;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class ExchangesPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "exchanges/";
    private final String COUNTRY_LABEL_ON_PAGE_LOCATOR = "//div[@class='feature-label' and contains(text(), 'Country')]";
    private final String COUNTRY_IN_BADGE_LOCATOR = "//span[contains(text(), '%s')]/../span[@class='badge badge-filter-count pull-right ng-binding']";
    private final String COUNTRY_IN_DROPDOWN_LOCATOR = "//span[@class='pull-left ng-binding' and contains(text(), '%s')]";
    private final String COUNTRY_ON_FILTERED_PAGE_LOCATOR = "//span[@class='ng-binding' and contains(text(), '%s')]";
    private int numberOfExchangesInBadge;

    @FindBy(xpath = "//div[@class='btn-group btn-block dropdown']/button[contains(text(), 'Country')]")
    private WebElement countryDropdownMenuLink;

    @FindBy(xpath = "//div[@class='btn-group btn-block dropdown open']/button[contains(text(), 'Country')]/../ul")
    private WebElement countryDropdownMenu;

    public ExchangesPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfExchangesInBadge() {
        return numberOfExchangesInBadge;
    }

    @Override
    public ExchangesPage openPage() {
        driver.get(BASE_URL);
        MyLogger.info("ExchangesPage was opened");
        return this;
    }

    public ExchangesPage clickOnCountryDropdown() {
        waitForElementClickable(countryDropdownMenuLink);
        countryDropdownMenuLink.click();
        return this;
    }

    public void getNumberOfExchangesFromBadge(Countries country) {
        WebElement numberExchangesOnBadgeInDropdown = driver.
                findElement(By.xpath(String.format(COUNTRY_IN_BADGE_LOCATOR, country.getNameOfCountry())));
        numberOfExchangesInBadge = Integer.parseInt(numberExchangesOnBadgeInDropdown.getText());
    }

    public ExchangesPage scrollPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        return this;
    }

    public ExchangesPage selectCountryInDropdown(Countries country) {
        WebElement countryLinkInDropdown = driver.
                findElement(By.xpath(String.format(COUNTRY_IN_DROPDOWN_LOCATOR, country.getNameOfCountry())));
        waitForElementClickable(countryLinkInDropdown);
        getNumberOfExchangesFromBadge(country);
        countryLinkInDropdown.click();
        MyLogger.info(country.getNameOfCountry() + " country was chosen");
        return this;
    }

    public List<WebElement> getFromFilteredPageAllResultsWith(Countries country) {
        return driver.findElements(By.xpath(String.format(COUNTRY_ON_FILTERED_PAGE_LOCATOR, country.getNameOfCountry())));
    }

    public List<WebElement> getAllCountryLabelsFromFilteredPage() {
        return driver.findElements(By.xpath(COUNTRY_LABEL_ON_PAGE_LOCATOR));
    }

}
package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.businessObjects.Countries;
import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.menus.HeaderMenu;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class ExchangesPage extends BasePage {

    private static final String BASE_URL = TestDataReader.getApplicationUrl() + "exchanges/";
    private static final String COUNTRY_LABEL_ON_PAGE_LOCATOR = "//td[@class='table-ranking-Country']";
    private static final String COUNTRY_IN_BADGE_LOCATOR = "//span[contains(text(), '%s')]/../span[@class='badge badge-filter-count pull-right ng-binding']";
    private static final String COUNTRY_IN_DROPDOWN_LOCATOR = "//span[@class='pull-left ng-binding' and contains(text(), '%s')]";
    private static final String COUNTRY_ON_FILTERED_PAGE_LOCATOR = "//td[@class='table-ranking-Country']/div[contains(text(), '%s')]";
    private int numberOfExchangesInBadge;
    private HeaderMenu headerMenu;

    @FindBy(xpath = "//div[@class='btn-group btn-block dropdown']/button[contains(text(), 'Country')]")
    private WebElement countryDropdownMenuLink;

    @FindBy(xpath = "//div[@class='btn-group btn-block dropdown open']/button[contains(text(), 'Country')]/../ul")
    private WebElement countryDropdownMenu;

    @FindBy(xpath = "//span[@class='fa fa-close']")
    private BaseButton resetFiltersButton;

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
        return this;
    }

    public ExchangesPage selectCountryInDropdown(Countries country) {
        WebElement countryLinkInDropdown = driver.
                findElement(By.xpath(String.format(COUNTRY_IN_DROPDOWN_LOCATOR, country.getNameOfCountry())));
        waitForElementClickable(countryLinkInDropdown);
        getNumberOfExchangesFromBadge(country);
        countryLinkInDropdown.click();
        MyLogger.info(country.getNameOfCountry() + " country was chosen");
        scrollPage();
        waitForElementClickable(resetFiltersButton);
        return this;
    }

    public List<WebElement> getFromFilteredPageAllResultsWith(Countries country) {
        return driver.findElements(By.xpath(String.format(COUNTRY_ON_FILTERED_PAGE_LOCATOR, country.getNameOfCountry())));
    }

    public List<WebElement> getAllCountryLabelsFromFilteredPage() {
        return driver.findElements(By.xpath(COUNTRY_LABEL_ON_PAGE_LOCATOR));
    }

}
package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.service.TestDataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class ExchangesPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "exchanges/";
    private int numberOfExchangesInBadge;

    @FindBy(xpath = "//button[@class='btn btn-default btn-filter btn-block dropdown-toggle ng-binding' and contains(text(),'Country')]")
    private WebElement countryDropdownMenuLink;

    @FindBy(xpath = "//button[@class='btn btn-default btn-filter btn-block dropdown-toggle ng-binding' and contains(text(),'Country')]/../ul")
    private WebElement countryDropdownMenu;

    @FindBy(xpath = "//span[contains(text(), 'Denmark')]")
    private WebElement denmarkLinkInDropdown;

    @FindBy(xpath = "//span[@class='ng-binding' and contains(text(), 'Denmark')]")
    private WebElement denmarkExchangeResultOnFilteredPage;

    @FindBy(xpath = "//span[contains(text(), 'Denmark')]/../span[@class='badge badge-filter-count pull-right ng-binding']")
    private WebElement numberExchangesOnBadgeInDropdown;

    @FindBy(xpath = "//div[@class='feature-label' and contains(text(), 'Country')]")
    private WebElement countryLabelOnFilteredPage;

    public ExchangesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ExchangesPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public ExchangesPage clickOnCountryDropdown() {
        waitForElementClicable(countryDropdownMenuLink);
        countryDropdownMenuLink.click();
        return this;
    }

    public void getNumberOfExchangesInDenmarkFromBadge() {
        numberOfExchangesInBadge = Integer.parseInt(numberExchangesOnBadgeInDropdown.getText());
    }

    public ExchangesPage scrollToCountryInDropdown() {
        waitForElementVisible(countryDropdownMenu);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", countryDropdownMenu);
        return this;
    }

    public ExchangesPage selectCountryInDropdown() {
        waitForElementClicable(denmarkLinkInDropdown);
        getNumberOfExchangesInDenmarkFromBadge();
        denmarkLinkInDropdown.click();
        return this;
    }

    private List<WebElement> getAllResultsAfterClickingOnDenmarkFilter() {
        return driver.findElements(By.xpath("//span[@class='ng-binding' and contains(text(), 'Denmark')]"));
    }

    private List<WebElement> getAllCountryLabelsFromFilteredPage() {
        return driver.findElements(By.xpath("//div[@class='feature-label' and contains(text(), 'Country')]"));
    }

    public boolean isNumberExchangesOnFilteredPageCorrect() {
        return (getAllResultsAfterClickingOnDenmarkFilter().size() == numberOfExchangesInBadge);
    }

    public boolean isNumberCountriesOnFilteredPageCorrect() {
        return (getAllCountryLabelsFromFilteredPage().size() == numberOfExchangesInBadge);
    }
}
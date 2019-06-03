package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Countries;
import com.epam.tat21.crypto.service.TestDataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class ExchangesPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "exchanges/";
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
        return this;
    }

    public ExchangesPage clickOnCountryDropdown() {
        waitForElementClicable(countryDropdownMenuLink);
        countryDropdownMenuLink.click();
        return this;
    }

    public void getNumberOfExchangesFromBadge(Countries country) {
        WebElement numberExchangesOnBadgeInDropdown = driver.
                findElement(By.xpath("//span[contains(text(), '" + country.getNameOfCountry() +
                        "')]/../span[@class='badge badge-filter-count pull-right ng-binding']"));
        numberOfExchangesInBadge = Integer.parseInt(numberExchangesOnBadgeInDropdown.getText());
    }

    public ExchangesPage scrollPage() {
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return this;
    }

    public ExchangesPage selectCountryInDropdown(Countries country) {
        WebElement countryLinkInDropdown = driver.
                findElement(By.xpath("//span[contains(text(), '" + country.getNameOfCountry() + "')]"));
        waitForElementClicable(countryLinkInDropdown);
        getNumberOfExchangesFromBadge(country);
        countryLinkInDropdown.click();
        return this;
    }

    public List<WebElement> getFromFilteredPageAllResultsWith(Countries country) {
        return driver.findElements(By.xpath("//span[@class='ng-binding' and contains(text(), '" + country.getNameOfCountry() + "')]"));
    }

    public List<WebElement> getAllCountryLabelsFromFilteredPage() {
        return driver.findElements(By.xpath("//div[@class='feature-label' and contains(text(), 'Country')]"));
    }

}
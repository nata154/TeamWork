package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NewsPage extends HeaderPage {

    private static final String BASE_URL = TestDataReader.getApplicationUrl() + "news/";
    private static final String COINS_NEWS_XPATH = "//a[@href='/news/list/latest/?categories=";
    private static final String ARTICLE_TITLE_LOCATOR = "//a[@rel and @class='ng-binding']";
    private static final String FEED_LINK_LOCATOR = "//a[contains(text(), '%s')]";  /*"//img[contains(@ng-src, '%s')]/.." - xpath for image, if driver won't find element; //button[contains(text(), 'Feeds')]/../ul//a[contains(text()[position() = 2],'CryptoGlobe')]*/

    @FindBy(xpath = "//div[@class='col-md-12 list-container ng-isolate-scope']")
    private WebElement containerOfNews;

    @FindBy(xpath = "//button[contains(text(), 'Feeds')]")
    private WebElement feedsDropdownLink;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewsPage openPage() {
        driver.get(BASE_URL);
        MyLogger.info("NewsPage was opened");
        return this;
    }

    public NewsPage goToCoinNews(Coin coin) {
        moveToNewsTab();
        WebElement newsCoin = driver.findElement(By.xpath(COINS_NEWS_XPATH + coin.getAbbreviationCoin() + "']"));
        waitForElementVisible(newsCoin);
        MyLogger.info(coin.getNameOfCoin() + " news was pressed");
        newsCoin.click();
        return this;
    }

    public int getNumberOfNewsForCoin(Coin coin) {
        int currentCountOfNews = 0;
        waitForNewsVisibility(coin.getAbbreviationCoin());
        List<WebElement> news = containerOfNews.findElements(By.id("news_"));
        String xpathCategoriesOfNews = "//span[contains(text(),'";
        for (WebElement item : news) {
            waitForElementVisible(item);
            if (item.findElement(By.xpath(xpathCategoriesOfNews + coin.getAbbreviationCoin() + "')]")).isEnabled()) {
                currentCountOfNews++;
            }
        }
        return currentCountOfNews;
    }

    private void waitForNewsVisibility(String categories) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            String xpathOfNewsCategories = "//span[contains(text(),'" + categories + "')]";

            public Boolean apply(WebDriver d) {
                List<WebElement> newsBitcoin = containerOfNews.findElements(By.id("news_"));
                for (WebElement news : newsBitcoin) {
                    return news.findElement(By.xpath(xpathOfNewsCategories)).isEnabled();
                }
                return false;
            }
        });
    }

    public List<WebElement> getAllNewsArticleTitle() {
        MyLogger.info("Getting all news titles from the news page");
        return driver.findElements(By.xpath(ARTICLE_TITLE_LOCATOR));
    }

    public NewsPage clickOnFeedsDropdown() {
        waitForElementClickable(feedsDropdownLink);
        feedsDropdownLink.click();
        return this;
    }

    public List<WebElement> getAllFeedLinksFromDropdown() {
        return driver.findElements(By.xpath(FEED_LINK_LOCATOR));
    }

    public NewsPage clickOnFeedLink(String linkText) {
        driver.findElement(By.xpath(String.
                format(linkText, FEED_LINK_LOCATOR))).click();
        return this;
    }
}

package com.epam.tat21.crypto.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.elements.menus.HeaderMenu;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;

public class NewsPage extends BasePage {

    private static final String BASE_URL = TestDataReader.getApplicationUrl() + "news/";
    private static final String COINS_NEWS_XPATH = "//a[@href='/news/list/latest/?categories=";
    private static final String ARTICLE_TITLE_LOCATOR = "//a[@rel and @class='ng-binding']";
    private static final String FEED_LINK_LOCATOR = "//button[contains(text(), 'Feeds')]/../ul//a[contains(text()[position() = 2],'%s')]";
    private static int indexCounter = 0;
    private HeaderMenu headerMenu;

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
        headerMenu.moveToNewsTab();
        WebElement newsCoin = driver.findElement(By.xpath(COINS_NEWS_XPATH + coin.getAbbreviationCoin() + "']"));
        waitForElementVisible(newsCoin);
        MyLogger.info(coin.getNameOfCoin() + " news was pressed");
        newsCoin.click();
        return this;
    }
    
    public NewsPage goToCoinNews(String abbreviationCoin) {
        headerMenu.moveToNewsTab();
        WebElement newsCoin = driver.findElement(By.xpath(COINS_NEWS_XPATH + abbreviationCoin + "']"));
        waitForElementVisible(newsCoin);
        MyLogger.info(abbreviationCoin + " news was pressed");
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
    
    public int getNumberOfNewsForCoin(String coin) {
        int currentCountOfNews = 0;
        waitForNewsVisibility(coin);
        List<WebElement> news = containerOfNews.findElements(By.id("news_"));
        String xpathCategoriesOfNews = "//span[contains(text(),'";
        for (WebElement item : news) {
            waitForElementVisible(item);
            if (item.findElement(By.xpath(xpathCategoriesOfNews + coin + "')]")).isEnabled()) {
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
        waitForElementClickable(feedsDropdownLink);
        return driver.findElements(By.xpath(ARTICLE_TITLE_LOCATOR));
    }

    public NewsPage clickOnFeedsDropdown() {
        waitForElementClickable(feedsDropdownLink);
        feedsDropdownLink.click();
        return this;
    }

    /**
     * Cause some of the feeds on the page has the same linkText,
     * the method below use the list of WebElements and the static index counter
     * for clicking on the correct link.
     */

    public NewsPage clickOnFeedLink(String linkText) {
        List<WebElement> feedLinks = driver.findElements(By.xpath(String.
                format(FEED_LINK_LOCATOR, linkText)));
        if (feedLinks.size() == 1) {
            feedLinks.get(0).click();
            indexCounter = 0;
        } else {
            feedLinks.get(indexCounter).click();
            indexCounter += 1;
        }
        return this;
    }
}

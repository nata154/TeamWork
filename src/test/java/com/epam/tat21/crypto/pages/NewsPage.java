package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.service.TestDataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NewsPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "news/";

    private static final int COUNT_OF_NEWS = 50;

    @FindBy(xpath = "//a[@href='/news/list/latest/?categories=BTC']")
    private WebElement newsBitcoin;

    @FindBy(xpath = "//a[@href='/news/list/latest/?categories=ETH']")
    private WebElement newsEthereum;

    @FindBy(xpath = "//a[@href='/news/list/latest/?categories=LTC']")
    private WebElement newsLitecoin;

    @FindBy(xpath = "//a[@href='/news/list/latest/?categories=XMR']")
    private WebElement newsMonero;

    @FindBy(xpath = "//a[@href='/news/list/latest/?categories=ZEC']")
    private WebElement newsZCash;

    @FindBy(xpath = "//a[@href='/news/list/latest/?categories=XRP']")
    private WebElement newsXRP;

    @FindBy(xpath = "//div[@class='loader-ccc-logo']")
    private WebElement loaderIcon;

    @FindBy(xpath = "//div[@class='col-md-12 list-container ng-isolate-scope']")
    private WebElement containerOfNews;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewsPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public NewsPage goToBitcoinNews() {
        moveToNewsTab();
        waitForElementVisible(newsBitcoin);
        newsBitcoin.click();
        return this;
    }

    public NewsPage goToEthereumNews() {
        moveToNewsTab();
        waitForElementVisible(newsEthereum);
        newsEthereum.click();
        return this;
    }

    public NewsPage goToLitecoinNews() {
        moveToNewsTab();
        waitForElementVisible(newsLitecoin);
        newsLitecoin.click();
        return this;
    }

    public NewsPage goToMoneroNews() {
        moveToNewsTab();
        waitForElementVisible(newsMonero);
        newsMonero.click();
        return this;
    }

    public NewsPage goToZCashNews() {
        moveToNewsTab();
        waitForElementVisible(newsZCash);
        newsZCash.click();
        return this;
    }

    public NewsPage goToXRPNews() {
        moveToNewsTab();
        waitForElementVisible(newsXRP);
        newsXRP.click();
        return this;
    }

    public boolean checkCategoriesOfNewsBitcoin() {
        int currentCountOfNews = 0;
        waitForNewsVisibility("BTC");
        List<WebElement> newsBitcoin = containerOfNews.findElements(By.id("news_"));
        for (WebElement news : newsBitcoin) {
            waitForElementVisible(news);
            if (news.findElement(By.xpath("//span[contains(text(),'BTC')]")).isEnabled()) {
                currentCountOfNews++;
            }
        }
        return currentCountOfNews == COUNT_OF_NEWS;
    }

    public boolean checkCategoriesOfNewsEthereum() {
        int currentCountOfNews = 0;
        waitForNewsVisibility("ETH");
        List<WebElement> newsEthereum = containerOfNews.findElements(By.id("news_"));
        for (WebElement news : newsEthereum) {
            waitForElementVisible(news);
            if (news.findElement(By.xpath("//span[contains(text(),'ETH')]")).isEnabled()) {
                currentCountOfNews++;
            }
        }
        return currentCountOfNews == COUNT_OF_NEWS;
    }

    public boolean checkCategoriesOfNewsLitecoin() {
        int currentCountOfNews = 0;
        waitForNewsVisibility("LTC");
        List<WebElement> newsLitecoin = containerOfNews.findElements(By.id("news_"));
        for (WebElement news : newsLitecoin) {
            waitForElementVisible(news);
            if (news.findElement(By.xpath("//span[contains(text(),'LTC')]")).isEnabled()) {
                currentCountOfNews++;
            }
        }
        return currentCountOfNews == COUNT_OF_NEWS;
    }

    public boolean checkCategoriesOfNewsMonero() {
        int currentCountOfNews = 0;
        waitForNewsVisibility("XMR");
        List<WebElement> newsMonero = containerOfNews.findElements(By.id("news_"));
        for (WebElement news : newsMonero) {
            waitForElementVisible(news);
            if (news.findElement(By.xpath("//span[contains(text(),'XMR')]")).isEnabled()) {
                currentCountOfNews++;
            }
        }
        return currentCountOfNews == COUNT_OF_NEWS;
    }

    public boolean checkCategoriesOfNewsZCash() {
        int currentCountOfNews = 0;
        waitForNewsVisibility("ZEC");
        List<WebElement> newsZCash = containerOfNews.findElements(By.id("news_"));
        for (WebElement news : newsZCash) {
            waitForElementVisible(news);
            if (news.findElement(By.xpath("//span[contains(text(),'ZEC')]")).isEnabled()) {
                currentCountOfNews++;
            }
        }
        return currentCountOfNews == COUNT_OF_NEWS;
    }

    public boolean checkCategoriesOfNewsXRP() {
        int currentCountOfNews = 0;
        waitForNewsVisibility("XRP");
        List<WebElement> newsXRP = containerOfNews.findElements(By.id("news_"));
        for (WebElement news : newsXRP) {
            waitForElementVisible(news);
            if (news.findElement(By.xpath("//span[contains(text(),'XRP')]")).isEnabled()) {
                currentCountOfNews++;
            }
        }
        return currentCountOfNews == COUNT_OF_NEWS;
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
}

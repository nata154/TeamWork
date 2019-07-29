package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class NewsPageMobile extends BasePageMobile {

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ScrollView\").className(\"android.widget.TextView\").index(1)")
    private List<AndroidElement> feedLineInNewsItems;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sources\")")
    private AndroidElement sourcesLink;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ScrollView\")")
    private AndroidElement newsContainer;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ProgressBar\")")
    private AndroidElement refreshingBar;

    public NewsPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SourcesPageMobile goToSourcesPage() {
        MyLogger.info("Go to the sources page");
        WaitConditions.waitForClickableOfElement(sourcesLink, driver);
        sourcesLink.click();
        return new SourcesPageMobile(driver);
    }

    public List<AndroidElement> getFeedLinesInNewsItem() {
        MyLogger.info("Getting feed lines from news items");
        if (!newsContainer.isDisplayed()) {
            WaitConditions.waitForVisibilityOfElement(newsContainer, driver);
        } else if (refreshingBar.isDisplayed()) {
            WaitConditions.waitForInvisibilityOfAllElementsByXpath(driver, "//*[@class='android.widget.ProgressBar']");
        }
        return feedLineInNewsItems;
    }
}

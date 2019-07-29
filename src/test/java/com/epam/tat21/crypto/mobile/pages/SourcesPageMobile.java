package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

public class SourcesPageMobile extends BasePageMobile {

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ScrollView\").className(\"android.widget.TextView\").index(1)")
    private List<AndroidElement> feedItems;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").className(\"android.widget.TextView\").textMatches(\"(\\w+\\sall)\")")
    private AndroidElement selectingLine;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").className(\"android.widget.TextView\").text(\"\uE904\")")
    private AndroidElement backToNewsPage;

    public SourcesPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    private List<AndroidElement> getFeedItems() {
        MyLogger.info("Getting all feed items from the sources page");
        return feedItems;
    }

    public NewsPageMobile goBackToNewsPage() {
        MyLogger.info("Go back to the news page");
        backToNewsPage.click();
        return new NewsPageMobile(driver);
    }

    private void deselectAllFeeds() {
        MyLogger.info("Deselecting all feeds");
        if (selectingLine.getText().equals("Select all")) {
            selectingLine.click();
            new WebDriverWait(driver, 5).
                    until(ExpectedConditions.textToBePresentInElement(selectingLine, "Deselect all"));
            selectingLine.click();
        } else {
            selectingLine.click();
        }
    }

    public SourcesPageMobile clickOnConcreteFeed(String feedName) {
        deselectAllFeeds();
        MyLogger.info("Click on the " + feedName + " feed");
        Objects.requireNonNull(getFeedItems().stream().
                filter(item -> item.getText().equals(feedName)).
                findAny().orElse(null)).click();
        return this;
    }
}

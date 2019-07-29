package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

import java.util.List;

public class MainCryptoComparePageMobile extends BasePageMobile {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"News\")")
    private AndroidElement newsTab;

    public MainCryptoComparePageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public UserAccountPageMobile clickUserAccountIcon() {
        List<MobileElement> userImage = driver.findElements(By.className("android.view.ViewGroup"));
        userImage.get(4).click();
        MyLogger.info("Click icon and enter user account.");
        return new UserAccountPageMobile(driver);
    }

    public NewsPageMobile goToNewsPage() {
        MyLogger.info("Go to the news page");
        WaitConditions.waitForClickableOfElement(newsTab, driver);
        newsTab.click();
        return new NewsPageMobile(driver);
    }
}


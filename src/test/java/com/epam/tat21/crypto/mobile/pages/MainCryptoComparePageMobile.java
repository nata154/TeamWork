package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainCryptoComparePageMobile extends BasePageMobile {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Portfolio\")")
    private WebElement portfolioIcon;

    public MainCryptoComparePageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public UserAccountPageMobile clickUserAccountIcon() {
        //@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").index(0)")
        //has worse performance
        List<MobileElement> userImage = driver.findElements(By.className("android.widget.ImageView"));
        userImage.get(0).click();
        MyLogger.info("Click icon and enter user account.");
        return new UserAccountPageMobile(driver);
    }

    public PortfolioPageMobile clickPortfolioIcon() {
        portfolioIcon.click();
        MyLogger.info("Portfolio icon was clicked");
        return new PortfolioPageMobile(driver);
    }
}


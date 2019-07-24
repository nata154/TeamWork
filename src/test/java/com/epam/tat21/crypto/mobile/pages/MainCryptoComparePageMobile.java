package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainCryptoComparePageMobile extends BasePageMobile {

    @FindBy(xpath = "//android.widget.TextView[@text='Portfolio']")
    private WebElement portfolioIcon;

    public MainCryptoComparePageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public UserAccountPageMobile clickUserAccountIcon() {
        List<MobileElement> userImage = driver.findElements(By.className("android.view.ViewGroup"));
        userImage.get(4).click();
        MyLogger.info("Click icon and enter user account.");
        return new UserAccountPageMobile(driver);
    }

    public PortfolioPageMobile clickPortfolioIcon() {
        portfolioIcon.click();
        return new PortfolioPageMobile(driver);
    }
}


package com.epam.tat21.crypto.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePageMobile {
    protected AppiumDriver<MobileElement> driver;
    private static final int WAIT_FOR_ELEMENT_SECONDS = 15;

//    public abstract BasePageMobile openMobilePage();

    public BasePageMobile(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    protected void waitForElementVisible(WebElement element) {
//        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
//                .until(ExpectedConditions.visibilityOf(element));
//    }
//
//    protected void waitForElementClickable(WebElement element) {
//        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
//                .until(ExpectedConditions.elementToBeClickable(element));
//    }
}

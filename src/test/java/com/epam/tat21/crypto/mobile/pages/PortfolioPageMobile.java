package com.epam.tat21.crypto.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortfolioPageMobile extends BasePageMobile {

    @FindBy(xpath = "//android.widget.TextView[@text='\uF140']")
    private WebElement submenuArrow;


    public PortfolioPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
}

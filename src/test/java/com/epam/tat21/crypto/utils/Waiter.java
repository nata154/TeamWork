package com.epam.tat21.crypto.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    public Waiter() {}

    public static void waitForVisibilityOfAllElementsByXpath(WebDriver driver,String xPath, int timeoutInSeconds ) {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By
                                .xpath(xPath)));
    }

}

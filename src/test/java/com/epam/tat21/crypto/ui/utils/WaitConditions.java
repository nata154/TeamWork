package com.epam.tat21.crypto.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitConditions {

    private WaitConditions() {
    }

    public static void waitForVisibilityOfAllElementsByXpath(WebDriver driver, String xPath, int timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By
                                .xpath(xPath)));
    }

    public static void waitForAttributeToBe(WebDriver driver, WebElement element, String attribute, String valueOfAttribute) {
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .attributeToBe(element, attribute, valueOfAttribute));
    }

    public static void waitForVisibilityOfElement(WebElement element, WebDriver driver, int timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions
                        .visibilityOf(element));
    }

    public static void waitForClickableOfElement(WebElement element, WebDriver driver, int timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions
                        .elementToBeClickable(element));
    }

}

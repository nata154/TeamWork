package com.epam.tat21.crypto.ui.utils;

import com.epam.tat21.crypto.ui.service.GlobalConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitConditions {

    private static final int TIMEOUT_SECONDS = GlobalConstants.TIMEOUT_DURATION_IN_SECOND;

    private WaitConditions() {
    }

    public static void waitForVisibilityOfAllElementsByXpath(WebDriver driver, String xPath) {
        new WebDriverWait(driver, TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By
                                .xpath(xPath)));
    }

    public static void waitForAttributeToBe(WebDriver driver, WebElement element, String attribute, String valueOfAttribute) {
        new WebDriverWait(driver, TIMEOUT_SECONDS).until(ExpectedConditions
                .attributeToBe(element, attribute, valueOfAttribute));
    }

    public static void waitForVisibilityOfElement(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .visibilityOf(element));
    }

    public static void waitForClickableOfElement(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .elementToBeClickable(element));
    }

    public static void waitForInvisibilityOfAllElementsByXpath(WebDriver driver, String xPath) {
        new WebDriverWait(driver, TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(xPath)));
    }

}

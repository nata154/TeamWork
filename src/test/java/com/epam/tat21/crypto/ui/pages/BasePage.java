package com.epam.tat21.crypto.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class BasePage {

    protected WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_SECONDS = 15;


    public abstract BasePage openPage();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);

    }

    protected void waitForElementVisible(WebElement element) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

}

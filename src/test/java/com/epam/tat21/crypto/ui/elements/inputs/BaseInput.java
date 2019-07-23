package com.epam.tat21.crypto.ui.elements.inputs;

import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class BaseInput extends TypifiedElement {

    public BaseInput(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void inputText(String text) {
        MyLogger.info("Enter following text: " + text + " in " + getName());
        super.clear();
        super.sendKeys(text);
        assureInputedText(text, getWrappedElement(), DriverManager.getWebDriverFactory().getDriver());
    }

    private void assureInputedText(String expectedText, WebElement element, WebDriver driver) {
        if (getInputedText(element,driver).equals(expectedText)) {
            MyLogger.info("Inputed text in " + getName() + " is correct and contains all symbols.");
        } else {
            MyLogger.info("Wrong inputed text. Trying to reinput it.");
            while (!readValueForElement(element, driver).equals(expectedText)) {
                element.clear();
                element.sendKeys(expectedText);
            }
        }
    }

    private String readValueForElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsRequest = "return arguments[0].value;";
        String actualValue = (String) js.executeScript(jsRequest, element);
        MyLogger.info("get inputed text from line - " + actualValue);
        return actualValue;
    }

    private String getInputedText(WebElement element, WebDriver driver) {
        WaitConditions.waitForVisibilityOfElement(element, driver);
        return readValueForElement(element, driver);
    }

}

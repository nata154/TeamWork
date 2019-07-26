package com.epam.tat21.crypto.ui.elements.menus;

import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;

public class MdSelectDropdown extends TypifiedElement {

    public MdSelectDropdown(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void selectByValue(String value) {
        WebDriver driver = DriverManager.getWebDriverFactory().getDriver();
        WaitConditions.waitForClickableOfElement(getWrappedElement(), driver);
        getWrappedElement().click();
        List<WebElement> options = getWrappedElement().findElements(By.xpath(
                "//md-option[@value = '" + value + "']"));

        boolean matched = false;
        for (WebElement option : options) {
            if (option.isDisplayed()) {
                WaitConditions.waitForClickableOfElement(option, driver);
                option.click();
                matched = true;
            }
        }

        if (!matched) {
            throw new NoSuchElementException("Cannot locate option with value: " + value);
        }
    }

}
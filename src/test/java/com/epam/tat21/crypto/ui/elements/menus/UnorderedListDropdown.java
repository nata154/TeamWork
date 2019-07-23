package com.epam.tat21.crypto.ui.elements.menus;

import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;

public class UnorderedListDropdown extends TypifiedElement {

    public UnorderedListDropdown(WebElement wrappedElement) {
        super(wrappedElement);
    }

    private List<WebElement> getItems() {
        return getWrappedElement().findElements(By.tagName("li"));
    }

    public void chooseItemByIndex(int index) {
        WaitConditions.waitForClickableOfElement(getItems().get(index),
                DriverManager.getWebDriverFactory().getDriver());
        getItems().get(index).click();
    }

    public WebElement chooseItemByVisibleText(String text) {
        return getItems().stream().filter(item -> item.getText().equals(text)).findAny().get();
    }

}

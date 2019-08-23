package com.epam.tat21.crypto.ui.elements.menus;

import com.epam.tat21.crypto.ui.service.WebDriverAware;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;

public class UnorderedListDropdown extends TypifiedElement implements WebDriverAware {

    private WebDriver driver;

    public UnorderedListDropdown(WebElement wrappedElement) {
        super(wrappedElement);
    }

    private List<WebElement> getItems() {
        return getWrappedElement().findElements(By.tagName("li"));
    }

    public void chooseItemByIndex(int index) {
        List<WebElement> items = getItems();
        while (items.size() < 2) {
            items = getItems();
        }
        WaitConditions.waitForClickableOfElement(items.get(index),
                driver);
        items.get(index).click();
    }

    public WebElement chooseItemByVisibleText(String text) {
        return getItems().stream().filter(item -> item.getText().equals(text)).findAny().orElse(null);
    }

    @Override
    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

}

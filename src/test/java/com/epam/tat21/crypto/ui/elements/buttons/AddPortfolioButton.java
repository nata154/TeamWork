package com.epam.tat21.crypto.ui.elements.buttons;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

@FindBy(xpath = "//button[@ng-click='addPortfolioDialog()']")
public class AddPortfolioButton extends TypifiedElement {

    public AddPortfolioButton(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public void click() {
        super.click();
    }
}

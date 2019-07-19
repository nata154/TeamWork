package com.epam.tat21.crypto.ui.elements.buttons;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;


public class BaseButton extends TypifiedElement {

    public BaseButton(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public void click() {
        MyLogger.info("Click on the button " + getName());
        super.click();
    }
}

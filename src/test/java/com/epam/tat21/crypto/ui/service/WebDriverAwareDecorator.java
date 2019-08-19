package com.epam.tat21.crypto.ui.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

import java.lang.reflect.Field;

import static com.epam.tat21.crypto.ui.service.CustomElementLoader.createHtmlElement;
import static com.epam.tat21.crypto.ui.service.CustomElementLoader.createTypifiedElement;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.getElementName;

public class WebDriverAwareDecorator extends HtmlElementDecorator {
    private WebDriver driver;

    public WebDriverAwareDecorator(CustomElementLocatorFactory factory, WebDriver driver) {
        super(factory);
        this.driver = driver;
    }

    @Override
    protected <T extends HtmlElement> T decorateHtmlElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = decorateWebElement(loader, field);
        // create HtmlElement from the CustomElementLoader instead of ru.yandex.qatools.htmlelements.loader.HtmlElementLoader
        return createHtmlElement((Class<T>) field.getType(), elementToWrap, getElementName(field), driver);
    }


    @Override
    protected <T extends TypifiedElement> T decorateTypifiedElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = decorateWebElement(loader, field);
        // create TypifiedElement from the CustomElementLoader instead of ru.yandex.qatools.htmlelements.loader.HtmlElementLoader
        return createTypifiedElement((Class<T>) field.getType(), elementToWrap, getElementName(field), driver);

    }


}

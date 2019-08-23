package com.epam.tat21.crypto.ui.service;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

import java.lang.reflect.InvocationTargetException;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.newInstance;


public class CustomElementLoader {

    // copy-paste from ru.yandex.qatools.htmlelements.loader.HtmlElementLoader
    public static <T extends TypifiedElement> T createTypifiedElement(Class<T> elementClass, WebElement elementToWrap,
                                                                      String name, WebDriver driver) {
        try {
            T instance = newInstance(elementClass, elementToWrap);
            instance.setName(name);
            // custom code that set driver to simple TypifiedElement.
            if (instance instanceof WebDriverAware) {
                ((WebDriverAware) instance).setWebDriver(driver);
            }
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }


    public static <T extends HtmlElement> T createHtmlElement(Class<T> elementClass, WebElement elementToWrap,
                                                              String name, WebDriver driver) {
        try {
            T instance = newInstance(elementClass);
            instance.setWrappedElement(elementToWrap);
            instance.setName(name);
            // custom code that set driver to simple HtmlElement.
            if (instance instanceof WebDriverAware) {
                ((WebDriverAware) instance).setWebDriver(driver);
            }
            populatePageObject(instance, elementToWrap, driver);
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }

    public static void populatePageObject(Object page, SearchContext searchContext, WebDriver driver) {
        populatePageObject(page, new HtmlElementLocatorFactory(searchContext), driver);
    }


    public static void populatePageObject(Object page, CustomElementLocatorFactory locatorFactory, WebDriver driver) {
        // customize populate page object method to use another decorator WebDriverAwareDecorator instead of default. Will use to recursively initialize elements inside the HtmlElement.
        PageFactory.initElements(new WebDriverAwareDecorator(locatorFactory, driver), page);
    }
}

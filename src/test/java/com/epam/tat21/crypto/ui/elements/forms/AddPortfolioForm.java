package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.elements.menus.MdSelectDropdown;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//form[@name='newPortfolioForm']")
public class AddPortfolioForm extends HtmlElement {

    @FindBy(xpath = "//md-select[@ng-model='newPortfolio.Currency']")
    private MdSelectDropdown currencyDropdown;

    @FindBy(xpath = "//input[@ng-model='newPortfolio.Name']")
    private WebElement inputPortfolioName;

    @FindBy(xpath = "//textarea[@ng-model='newPortfolio.Description']")
    private WebElement textAreaDescription;

    @FindBy(xpath = "//span[contains(text(), 'Create')]")
    private WebElement createButton;


    public void selectCurrency(WebDriver driver, String currencyAbbreviation){
        WaitConditions.waitForClickableOfElement(currencyDropdown, driver, 5);
        currencyDropdown.selectByValue(currencyAbbreviation, driver);
    }

    public void inputPortfolioName(WebDriver driver, String name) {
        WaitConditions.waitForVisibilityOfElement(inputPortfolioName, driver, 5);
        MyLogger.info("Input the portfolio name");
        actionSendKeys(inputPortfolioName, name, driver);
        assureInputedText(getInputedText(inputPortfolioName, driver), name, inputPortfolioName, driver);
    }

    public void inputDescription(WebDriver driver, String text) {
        WaitConditions.waitForVisibilityOfElement(textAreaDescription, driver, 5);
        MyLogger.info("Input the portfolio description");
        actionSendKeys(textAreaDescription, text, driver);
        assureInputedText(getInputedText(textAreaDescription, driver), text, textAreaDescription, driver);
    }

    public void clickCreateButton(WebDriver driver){
        WaitConditions.waitForClickableOfElement(createButton, driver, 5);
        createButton.click();
    }

    private String readValueForElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsRequest = "return arguments[0].value;";
        String actualValue = (String) js.executeScript(jsRequest, element);
        MyLogger.info("get inputed text from line - " + actualValue);
        return actualValue;
    }

    private void actionSendKeys(WebElement element, String expectedName, WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(element, expectedName).build().perform();
    }

    private String getInputedText(WebElement element, WebDriver driver) {
        WaitConditions.waitForVisibilityOfElement(element, driver, 5);
        return readValueForElement(element, driver);
    }

    private void assureInputedText(String actualText, String expectedText, WebElement element, WebDriver driver) {
        if (actualText.equals(expectedText)) {
            MyLogger.info("Inputed text in the portfolio is correct and contains all letters.");
        } else {
            MyLogger.info("Wrong inputed text. Trying to reinput it.");
            element.clear();
            actionSendKeys(element, expectedText, driver);
            readValueForElement(element, driver);
        }
    }
}

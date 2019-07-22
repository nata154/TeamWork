package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.inputs.BaseInput;
import com.epam.tat21.crypto.ui.elements.menus.MdSelectDropdown;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//form[@name='newPortfolioForm']")
public class AddPortfolioForm extends HtmlElement {

    private WebDriver driver = DriverManager.getWebDriverFactory().getDriver();

    @FindBy(xpath = "//md-select[@ng-model='newPortfolio.Currency']")
    private MdSelectDropdown currencyDropdown;

    @FindBy(xpath = "//input[@ng-model='newPortfolio.Name']")
    private BaseInput inputPortfolioName;

    @FindBy(xpath = "//textarea[@ng-model='newPortfolio.Description']")
    private BaseInput textAreaDescription;

    @FindBy(xpath = "//span[contains(text(), 'Create')]")
    private BaseButton createButton;


    public void selectCurrency(String currencyAbbreviation) {
        WaitConditions.waitForClickableOfElement(currencyDropdown, driver, 5);
        currencyDropdown.selectByValue(currencyAbbreviation);
    }

    public void inputPortfolioName(String name) {
        WaitConditions.waitForVisibilityOfElement(inputPortfolioName, driver, 5);
        MyLogger.info("Input the portfolio name");
        inputPortfolioName.inputText(name);
    }

    public void inputDescription(String text) {
        WaitConditions.waitForVisibilityOfElement(textAreaDescription, driver, 5);
        MyLogger.info("Input the portfolio description");
        textAreaDescription.inputText(text);
    }

    public void clickCreateButton() {
        WaitConditions.waitForClickableOfElement(createButton, driver, 5);
        createButton.click();
    }

}

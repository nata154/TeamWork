package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.inputs.BaseInput;
import com.epam.tat21.crypto.ui.elements.menus.MdSelectDropdown;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Component
@FindBy(xpath = "//form[@name='newPortfolioForm']")
public class AddPortfolioForm extends HtmlElement {

    @Autowired
    private WebDriver driver;

    @FindBy(xpath = "//md-select[@ng-model='newPortfolio.Currency']")
    private WebElement currencyForPortfolio;

    @FindBy(xpath = "//md-select-menu[@class='_md md-overflow']")
    private MdSelectDropdown currencyDropdown;

    @FindBy(xpath = "//input[@ng-model='newPortfolio.Name']")
    private BaseInput inputPortfolioName;

    @FindBy(xpath = "//textarea[@ng-model='newPortfolio.Description']")
    private BaseInput textAreaDescription;

    @FindBy(xpath = "//span[contains(text(), 'Create')]")
    private BaseButton createButton;


    public void selectCurrency(String currencyAbbreviation) {
        WaitConditions.waitForClickableOfElement(currencyForPortfolio, driver);
        currencyForPortfolio.click();
        WaitConditions.waitForVisibilityOfElement(currencyDropdown, driver);
        currencyDropdown.selectByValue(currencyAbbreviation);
    }

    public void inputPortfolioName(String name) {
        WaitConditions.waitForVisibilityOfElement(inputPortfolioName, driver);
        MyLogger.info("Input the portfolio name");
        inputPortfolioName.inputText(name);
    }

    public void inputDescription(String text) {
        WaitConditions.waitForVisibilityOfElement(textAreaDescription, driver);
        MyLogger.info("Input the portfolio description");
        textAreaDescription.inputText(text);
    }

    public void clickCreateButton() {
        WaitConditions.waitForClickableOfElement(createButton, driver);
        createButton.click();
    }

}

package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.inputs.BaseInput;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//form[@name='newPortfolioForm']")
public class EditPortfolioForm extends HtmlElement {

    private WebDriver driver = DriverManager.getWebDriverFactory().getDriver();

    @FindBy(xpath = "//input[@ng-model='newPortfolio.Name']")
    private BaseInput inputPortfolioName;

    @FindBy(xpath = "//span[contains(text(), 'Update')]")
    private BaseButton updatePortfolioButton;

    @FindBy(xpath = "//span[contains(text(), 'Delete')]")
    private BaseButton deletePortfolioButton;

    public void changePortfolioName(String name) {
        WaitConditions.waitForVisibilityOfElement(inputPortfolioName, driver);
        MyLogger.info("Changing the portfolio name");
        inputPortfolioName.inputText(name);
    }

    public void clickUpdatePortfolioButton() {
        WaitConditions.waitForClickableOfElement(updatePortfolioButton, driver);
        updatePortfolioButton.click();
    }

    public void clickDeletePortfolioButton() {
        WaitConditions.waitForClickableOfElement(deletePortfolioButton, driver);
        deletePortfolioButton.click();
    }
}
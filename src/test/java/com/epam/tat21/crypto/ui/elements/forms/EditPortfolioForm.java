package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//form[@name='newPortfolioForm']")
public class EditPortfolioForm extends HtmlElement {

    @FindBy(xpath = "//input[@ng-model='newPortfolio.Name']")
    private WebElement inputPortfolioName;

    @FindBy(xpath = "//span[contains(text(), 'Update')]")
    private BaseButton updatePortfolioButton;

    @FindBy(xpath = "//span[contains(text(), 'Delete')]")
    private BaseButton deletePortfolioButton;

    public void changePortfolioName(WebDriver driver, String name) {
        WaitConditions.waitForVisibilityOfElement(inputPortfolioName, driver, 5);
        MyLogger.info("Changing the portfolio name");
        Actions action = new Actions(driver);
        action.sendKeys(inputPortfolioName, name).build().perform();
    }

    public void clickUpdatePortfolioButton(WebDriver driver) {
        WaitConditions.waitForClickableOfElement(updatePortfolioButton, driver, 5);
        updatePortfolioButton.click();
    }

    public void clickDeletePortfolioButton(WebDriver driver) {
        WaitConditions.waitForClickableOfElement(deletePortfolioButton, driver, 5);
        deletePortfolioButton.click();
    }
}

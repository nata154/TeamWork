package com.epam.tat21.crypto.ui.elements.toolbars;

import com.epam.tat21.crypto.ui.elements.menus.MdSelectDropdown;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//div[@class='toolbar-portfolio']")
public class PortfolioToolbar extends HtmlElement {

    @FindBy(xpath = "//md-select[@ng-model='activePortfolio.Currency']")
    private MdSelectDropdown currencyDropdown;

    @FindBy(xpath = "(//md-tab-content[contains(@class,'active')]//span[contains(text(), 'Coin')])[1]")
    private WebElement addCoinButton;

    @FindBy(xpath = "//md-tab-content[contains(@class,'active')]//button[@ng-click='editPortfolioDialog()']")
    private WebElement editPortfolioButton;

    public void selectCurrency(WebDriver driver, String currencyAbbreviation) {
        WaitConditions.waitForClickableOfElement(currencyDropdown, driver, 5);
        currencyDropdown.selectByValue(currencyAbbreviation, driver);
    }

    public void clickAddCoinButton(WebDriver driver) {
        WaitConditions.waitForClickableOfElement(addCoinButton, driver, 5);
        addCoinButton.click();
    }

    public void clickEditPortfolioButton(WebDriver driver) {
        WaitConditions.waitForClickableOfElement(editPortfolioButton, driver, 5);
        editPortfolioButton.click();
    }
}

package com.epam.tat21.crypto.ui.elements.toolbars;

import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.menus.MdSelectDropdown;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//div[@class='toolbar-portfolio']")
public class PortfolioToolbar extends HtmlElement {

    private WebDriver driver = DriverManager.getWebDriverFactory().getDriver();

    @FindBy(xpath = "//md-select[@ng-model='activePortfolio.Currency']")
    private MdSelectDropdown currencyDropdown;

    @FindBy(xpath = "(//md-tab-content[contains(@class,'active')]//span[contains(text(), 'Coin')])[1]")
    private BaseButton addCoinButton;

    @FindBy(xpath = "//md-tab-content[contains(@class,'active')]//button[@ng-click='editPortfolioDialog()']")
    private BaseButton editPortfolioButton;

    public void selectCurrency(String currencyAbbreviation) {
        WaitConditions.waitForClickableOfElement(currencyDropdown, driver);
        currencyDropdown.selectByValue(currencyAbbreviation);
    }

    public void clickAddCoinButton() {
        WaitConditions.waitForClickableOfElement(addCoinButton, driver);
        addCoinButton.click();
    }

    public void clickEditPortfolioButton() {
        WaitConditions.waitForClickableOfElement(editPortfolioButton, driver);
        editPortfolioButton.click();
    }
}

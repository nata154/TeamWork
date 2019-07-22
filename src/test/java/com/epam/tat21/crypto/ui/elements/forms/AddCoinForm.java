package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.inputs.BaseInput;
import com.epam.tat21.crypto.ui.elements.menus.UnorderedListDropdown;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//div[@class='md-dialog-content']")
public class AddCoinForm extends HtmlElement {

    private WebDriver driver = DriverManager.getWebDriverFactory().getDriver();

    @FindBy(xpath = "//input[@type='search']")
    private BaseInput searchCoinField;

    @FindBy(xpath = "//input[@name='amount']")
    private BaseInput amountField;

    @FindBy(xpath = "//input[@name='buyPrice']")
    private BaseInput buyPriceField;

    @FindBy(xpath = "//span[@class='ng-scope' and contains(text(), 'Add To Portfolio')]")
    private BaseButton addToPortfolioButton;

    @FindBy(xpath = "//ul[@ng-class='::menuClass']")
    private UnorderedListDropdown coinSuggestionsDropdown;

    public void selectCoinInDropdownByIndex(int index) {
        coinSuggestionsDropdown.chooseItemByIndex(index);
    }

    public void inputCoinInSearchField(Coin coin) {
        WaitConditions.waitForClickableOfElement(searchCoinField, driver, 5);
        searchCoinField.inputText(coin.getNameOfCoin());
        WaitConditions.waitForVisibilityOfElement(coinSuggestionsDropdown, driver, 5);
        selectCoinInDropdownByIndex(0);
    }

    public void inputAmountOfCoinInField(String amount) {
        WaitConditions.waitForClickableOfElement(amountField, driver, 5);
        amountField.inputText(amount);
    }

    public void inputBuyPriceOfCoinInField(String price) {
        WaitConditions.waitForClickableOfElement(buyPriceField, driver, 5);
        buyPriceField.inputText(price);
    }

    public void clickAddToPortfolioButton() {
        WaitConditions.waitForClickableOfElement(addToPortfolioButton,driver, 5);
        addToPortfolioButton.click();
    }
}

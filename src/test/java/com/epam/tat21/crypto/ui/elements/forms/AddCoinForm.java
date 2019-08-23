package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.inputs.BaseInput;
import com.epam.tat21.crypto.ui.elements.menus.UnorderedListDropdown;
import com.epam.tat21.crypto.ui.service.WebDriverAware;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//div[@class='md-dialog-content']")
public class AddCoinForm extends HtmlElement implements WebDriverAware {

    private WebDriver driver;

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
        WaitConditions.waitForClickableOfElement(searchCoinField, driver);
        searchCoinField.inputText(coin.getNameOfCoin() + " " + coin.getAbbreviationCoin());
        WaitConditions.waitForVisibilityOfElement(coinSuggestionsDropdown, driver);
        selectCoinInDropdownByIndex(0);
    }

    public void inputAmountOfCoinInField(String amount) {
        WaitConditions.waitForClickableOfElement(amountField, driver);
        amountField.inputText(amount);
    }

    public void inputBuyPriceOfCoinInField(String price) {
        WaitConditions.waitForClickableOfElement(buyPriceField, driver);
        buyPriceField.inputText(price);
    }

    public void clickAddToPortfolioButton() {
        WaitConditions.waitForClickableOfElement(addToPortfolioButton, driver);
        addToPortfolioButton.click();
    }

    @Override
    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }
}

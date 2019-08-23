package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.inputs.BaseInput;
import com.epam.tat21.crypto.ui.service.WebDriverAware;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//div[@class='md-dialog-content']")
public class EditCoinForm extends HtmlElement implements WebDriverAware {

    private WebDriver driver;

    @FindBy(xpath = "//input[@type='search']")
    private BaseInput searchCoinField;

    @FindBy(xpath = "//input[@name='amount']")
    private BaseInput amountField;

    @FindBy(xpath = "//input[@name='buyPrice']")
    private BaseInput buyPriceField;

    @FindBy(xpath = "//span[contains(text(), 'Update')]")
    private BaseButton updateCoinButton;

    @FindBy(xpath = "//span[contains(text(), 'Delete')]")
    private BaseButton deleteCoinButton;

    public void inputAmountOfCoinInField(String amount) {
        WaitConditions.waitForClickableOfElement(amountField, driver);
        amountField.inputText(amount);
    }

    public void inputBuyPriceOfCoinInField(String price) {
        WaitConditions.waitForClickableOfElement(buyPriceField, driver);
        buyPriceField.inputText(price);
    }

    public void clickUpdateCoinButton () {
        WaitConditions.waitForClickableOfElement(updateCoinButton, driver);
        updateCoinButton.click();
    }

    public void clickDeleteCoinButton () {
        WaitConditions.waitForClickableOfElement(deleteCoinButton, driver);
        deleteCoinButton.click();
    }

    @Override
    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }
}

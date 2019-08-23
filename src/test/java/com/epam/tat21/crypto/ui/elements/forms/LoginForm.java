package com.epam.tat21.crypto.ui.elements.forms;

import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.inputs.BaseInput;
import com.epam.tat21.crypto.ui.service.WebDriverAware;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@FindBy(xpath = "//div[@class='modal-content']")
public class LoginForm extends HtmlElement implements WebDriverAware {

    private WebDriver driver;

    @FindBy(xpath = "//input[@name='email']")
    private BaseInput emailField;

    @FindBy(xpath = "//input[@name='password']")
    private BaseInput passwordField;

    @FindBy(xpath = "//button[@value='Login']")
    private BaseButton loginButton;

    public void inputEmailInItsField(String email) {
        WaitConditions.waitForVisibilityOfElement(emailField, driver);
        emailField.inputText(email);
    }

    public void inputPasswordInItsField(String password) {
        WaitConditions.waitForVisibilityOfElement(passwordField, driver);
        passwordField.inputText(password);
    }

    public void clickLoginButton() {
        WaitConditions.waitForClickableOfElement(loginButton, driver);
        loginButton.click();
    }

    @Override
    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }
}

package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.elements.forms.LoginForm;
import com.epam.tat21.crypto.ui.elements.menus.HeaderMenu;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class MainCryptoComparePage extends BasePage {

    private final String BASE_URL = TestDataReader.getApplicationUrl();
    private LoginForm loginForm;
    private HeaderMenu headerMenu;

    public MainCryptoComparePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainCryptoComparePage openPage() {
        driver.navigate().to(BASE_URL);
        MyLogger.info("MainCryptoComparePage was opened");
        return this;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }

    public MainCryptoComparePage fillLoginForm(User user) {
        loginForm.inputEmailInItsField(user.getUserName());
        MyLogger.info("Email on login form was filled");
        loginForm.inputPasswordInItsField(user.getUserPassword());
        MyLogger.info("Password on login form was filled");
        return this;
    }

    public MainCryptoComparePage clickLoginButton() {
        loginForm.clickLoginButton();
        MyLogger.info("Login button was pressed");
        return this;
    }

    public MainCryptoComparePage login(User user) {
        headerMenu.clickLogInLink();
        fillLoginForm(user).clickLoginButton();
        WaitConditions.waitForInvisibilityOfAllElementsByXpath(driver, "//div[@modal-render]");
        return this;
    }


}

package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserDropdownMenuInHeader extends HeaderPage {

    @FindBy(xpath = "//a[@href='/quiz/']")
    private WebElement quizLine;

    @FindBy(xpath = "//a[starts-with(@href,'/profile/')]")
    private WebElement viewMyProfileLine;

    @FindBy(xpath = "//a[@href='/cryptopian/general']")
    private WebElement accountLine;

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right ng-scope']//a[@href='/cryptopian/api-keys']")
    private WebElement apiKeysLine;

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right ng-scope']//a[@href='/cryptopian/billing']")
    private WebElement billingLine;

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right ng-scope']//a[@class='ng-binding']")
    private WebElement turnOffLine;

    @FindBy(xpath = "//a[@ng-click='userManager.logout()']")
    private WebElement logoutLine;

    public UserDropdownMenuInHeader(WebDriver driver) {
        super(driver);
    }

    public UserDropdownMenuInHeader clickQuizLine() {
        waitForElementClickable(quizLine);
        quizLine.click();
        MyLogger.info("UserDropdownMenuInHeader was appeared");
        return this;
    }

    public ProfilePage clickViewMyProfile() {
        waitForElementClickable(viewMyProfileLine);
        viewMyProfileLine.click();
        return new ProfilePage(driver);
    }


    public UserAccountPage clickAccountLine() {
        waitForElementClickable(accountLine);

        accountLine.click();
        return new UserAccountPage(driver);
    }

    public UserDropdownMenuInHeader clickApiKeysLine() {
        waitForElementClickable(apiKeysLine);
        apiKeysLine.click();
        return this;
    }

    public UserDropdownMenuInHeader clickBillingLine() {
        waitForElementClickable(billingLine);
        billingLine.click();
        return this;
    }

    public UserDropdownMenuInHeader clickTurnOffLine() {
        waitForElementClickable(turnOffLine);
        turnOffLine.click();
        return this;
    }

    public UserDropdownMenuInHeader clickLogOutLine() {
        waitForElementClickable(logoutLine);
        logoutLine.click();
        return this;
    }
}

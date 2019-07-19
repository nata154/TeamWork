package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.businessObjects.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;


public class LoginPageMobile extends BasePageMobile{


    @AndroidFindBy(className="android.widget.TextView")
    private WebElement loginFunction;
//       <div class="_highlighter-box_619e8" style="z-index: 10; left: 63.8535px; top: 170.806px; width: 13.4063px; height: 6.55417px;"><div></div></div>

//    @FindBy(xpath="//div[@style='z-index: 9; left: 5.56111px; top: 128.402px; width: 131.878px; height: 15.6903px;']/div")
//    private WebElement emailField;
////    <div class="_highlighter-box_619e8" style="z-index: 9; left: 5.56111px; top: 128.402px; width: 131.878px; height: 15.6903px;"><div></div></div>
////<div class="_highlighter-box_619e8" style="z-index: 9; left: 5.56111px; top: 128.402px; width: 131.878px; height: 15.6903px;"><div></div></div>
//    //<div class="_highlighter-box_619e8" style="z-index: 9; left: 6.72778px; top: 155.34px; width: 159.544px; height: 18.9819px;"><div></div></div>
//
//    @FindBy(xpath = "//div[@style='z-index: 9; left: 5.56111px; top: 149.653px; width: 131.878px; height: 15.591px;']/div")
//    private WebElement passwordField;
//    //<div class="_highlighter-box_619e8" style="z-index: 9; left: 5.56111px; top: 149.653px; width: 131.878px; height: 15.591px;"><div></div></div>
//
//    @FindBy(xpath = "//div[@style='z-index: 10; left: 5.56111px; top: 242.008px; width: 131.878px; height: 15.9882px;']/div")
//    private WebElement loginButton;
////    <div class="_highlighter-box_619e8" style="z-index: 10; left: 5.56111px; top: 242.008px; width: 131.878px; height: 15.9882px;"><div></div></div>
//
    public LoginPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
//
//    @Override
//    public BasePageMobile openMobilePage() {
//        driver.navigate().to(BASE_URL);
//        MyLogger.info("MainCryptoComparePage was opened");
//        return this;
//    }
//
//    public LoginPageMobile fillLoginForm(User user) {
//       // waitForElementVisible(emailField);
//        emailField.click();
//        emailField.sendKeys(user.getUserName());
//        MyLogger.info("Email on login form was filled");
//        passwordField.click();
//        passwordField.sendKeys(user.getUserPassword());
//        MyLogger.info("Password  on login form was filled");
//        return this;
//    }
//
//    public LoginPageMobile clickLoginButton() {
//       // waitForElementVisible(loginButton);
//        loginButton.click();
//        MyLogger.info("Login button was pressed");
//        return this;
//    }

    public MainCryptoComparePageMobile login(User user) {
        loginFunction.click();
        //fillLoginForm(user).clickLoginButton();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@modal-render]")));
        return new MainCryptoComparePageMobile(driver);
    }
}

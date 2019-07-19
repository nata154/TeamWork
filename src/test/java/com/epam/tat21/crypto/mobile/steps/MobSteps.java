package com.epam.tat21.crypto.mobile.steps;

import com.epam.tat21.crypto.mobile.driver.MobileDriverFactory;
import com.epam.tat21.crypto.mobile.driver.MobileDriverForFarm;
import com.epam.tat21.crypto.mobile.driver.MobileLocalDriver;
import com.epam.tat21.crypto.mobile.pages.LoginPageMobile;
import com.epam.tat21.crypto.mobile.pages.MainCryptoComparePageMobile;
import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.service.UserCreator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public class MobSteps {

    private AppiumDriver<MobileElement> driver;

    public MobileDriverFactory getMobileDriverFactory() {
        if (driver == null) {
            switch (System.getProperty("mobile")) {
                case "local":
                    return new MobileLocalDriver();
                case "farm":
                    return new MobileDriverForFarm();
                default:
                    return new MobileLocalDriver();
            }
        }
        return new MobileLocalDriver();
    }

    public void startDevice() {
        driver = getMobileDriverFactory().getDriver();
    }

    public void closeDevice() {
        getMobileDriverFactory().closeDriver();
    }

    public void previewApp() {
        List<MobileElement> textViews=driver.findElements(By.className("android.widget.TextView"));
        textViews.get(1).click();

        User user = UserCreator.withCredentialsFromProperty();

        List<MobileElement> loginFields = driver.findElements(By.className("android.widget.EditText"));
        loginFields.get(1).click();
//        loginField.get(1).sendKeys(user.getUserName());
        loginFields.get(0).sendKeys(user.getUserName());

        loginFields.get(1).click();
        loginFields.get(1).sendKeys(user.getUserPassword());

        List<MobileElement> loginFunction = driver.findElements(By.className("android.widget.TextView"));
        loginFunction.get(0).click();
    }

//    @AndroidFindBy(className = "android.widget.TextView")
//    private AndroidElement loginFunction;

//    public MainCryptoComparePageMobile login(User user) {
//        loginFunction.click();
//        //fillLoginForm(user).clickLoginButton();
//        //new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@modal-render]")));
//        return new MainCryptoComparePageMobile(driver);
//    }

    //@Given("I login user")
    public MainCryptoComparePageMobile loginUser() {
        User user = UserCreator.withCredentialsFromProperty();
        return new LoginPageMobile(driver)
                .login(user);
    }
}

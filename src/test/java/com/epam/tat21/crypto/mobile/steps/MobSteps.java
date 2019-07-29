package com.epam.tat21.crypto.mobile.steps;

import com.epam.tat21.crypto.mobile.driver.MobileDriverManager;
import com.epam.tat21.crypto.mobile.pages.LoginPageMobile;
import com.epam.tat21.crypto.mobile.pages.MainCryptoComparePageMobile;
import com.epam.tat21.crypto.mobile.pages.NewsPageMobile;
import com.epam.tat21.crypto.mobile.pages.SourcesPageMobile;
import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.service.UserCreator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobSteps {

    private AppiumDriver<MobileElement> driver;

    public MobSteps() {
        this.driver = MobileDriverManager.getMobileDriverFactory().getDriver();
    }

    public void closeDevice() {
        MobileDriverManager.getMobileDriverFactory().closeDriver();
    }

    @Given("^I login user in crypto application$")
    public MainCryptoComparePageMobile loginUser() {
        User user = UserCreator.withCredentialsFromProperty();
        return new LoginPageMobile(driver)
                .login(user);
    }

    @When("^I click user account and log out$")
    public MainCryptoComparePageMobile clickUserAccountAndLogOut() {
        return new MainCryptoComparePageMobile(driver)
                .clickUserAccountIcon()
                .clickLogOutButton();
    }

    @Then("^I check log out - I see password field$")
    public boolean checkLogout() {
        return new LoginPageMobile(driver).isFieldPasswordVisible();
    }

    @When("^I go to the sources page from the news page$")
    public SourcesPageMobile goToSourcesPageFromNewsPage() {
        return new MainCryptoComparePageMobile(driver)
                .goToNewsPage()
                .goToSourcesPage();
    }

    @Then("^I choose a concrete ((\\w+\\s\\w+)|(\\w+)) item and click it$")
    public SourcesPageMobile chooseFeedItem(String feedName) {
        return new SourcesPageMobile(driver)
                .clickOnConcreteFeed(feedName);
    }

    @Then("^I go to the filtered news page$")
    public NewsPageMobile goToFilteredNewsPage() {
        return new SourcesPageMobile(driver)
                .goBackToNewsPage();
    }

    @And("^I check how were news filtered by ((\\w+\\s\\w+)|(\\w+))$")
    public boolean areNewsFilteredProperly(String feedName) {
        return new NewsPageMobile(driver)
                .getFeedLinesInNewsItem()
                .stream().allMatch(item -> item.getText().equals(feedName));
    }

}

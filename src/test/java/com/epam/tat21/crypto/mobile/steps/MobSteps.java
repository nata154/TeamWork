package com.epam.tat21.crypto.mobile.steps;

import com.epam.tat21.crypto.mobile.driver.MobileDriverManager;
import com.epam.tat21.crypto.mobile.pages.*;
import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.service.UserCreator;
import com.epam.tat21.crypto.ui.utils.RandomString;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;

public class MobSteps {

    private AppiumDriver<MobileElement> driver;

    private static final int COUNT_OF_SYMBOLS = 5;
    private Coin coin = Coin.BTC;
    private String currency = coin.getAbbreviationCoin();
    private String description = RandomString.getRandomString(COUNT_OF_SYMBOLS);
    private String portfolioName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
    private String changedName = RandomString.getRandomString(COUNT_OF_SYMBOLS);

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

    public boolean areNewsFilteredProperly(String feedName) {
        return new NewsPageMobile(driver)
                .getFeedLinesInNewsItem()
                .stream().allMatch(item -> item.getText().equals(feedName));
    }

    @And("^I check how were news filtered by ((\\w+\\s\\w+)|(\\w+))$")
    public void assertHowWereNewsFiltered(String feedName) {
        Assert.assertTrue(areNewsFilteredProperly(feedName));
    }

    public void createUserPortfolio(String portfolioName, String currency, String description) {
        new MainCryptoComparePageMobile(driver)
                .clickPortfolioIcon()
                .inputNewPortfolioValues(portfolioName, currency, description)
                .submitCreatingPortfolio();
    }

    @When("^I create new portfolio$")
    public void createUserPortfolioForBDD() {
        new MainCryptoComparePageMobile(driver)
                .clickPortfolioIcon()
                .inputNewPortfolioValues(portfolioName, currency, description)
                .submitCreatingPortfolio();
    }

    public PortfolioPageMobile changeUserPortfolioName(String changedName) {
        return new PortfolioPageMobile(driver)
                .clickButtonEdit()
                .changeNameOfPortfolio(changedName)
                .submitEditingPortfolio();
    }

    @And("^I change portfolio name$")
    public PortfolioPageMobile changeUserPortfolioNameForBDD() {
        return new PortfolioPageMobile(driver)
                .clickButtonEdit()
                .changeNameOfPortfolio(changedName)
                .submitEditingPortfolio();
    }

    public String getNameOfPortfolio() {
        return new PortfolioPageMobile(driver)
                .getCurrentPortfolioName();
    }

    @Then("^I check portfolio name$")
    public void checkNameOfPortfolioForBDD() {
        String currentPortfolioName = new PortfolioPageMobile(driver).getCurrentPortfolioName();
        Assert.assertEquals(currentPortfolioName, changedName);
    }

    @And("^I delete current portfolio$")
    public PortfolioPageMobile deleteUserPortfolio() {
        return new PortfolioPageMobile(driver)
                .clickButtonEdit()
                .clickButtonDelete();
    }

    public boolean isPortfolioDeleted(String changedName) {
        return new PortfolioPageMobile(driver)
                .isPortfolioWithSuchNameDeleted(changedName);
    }

    @Then("^I check deleting of portfolio with such name$")
    public void isPortfolioDeletedForBDD() {
        boolean isPortfolioDeleted = new PortfolioPageMobile(driver).isPortfolioWithSuchNameDeleted(changedName);
        Assert.assertTrue(isPortfolioDeleted, "Issue while deleting portfolio - portfolio with such name still exists.");
    }
}

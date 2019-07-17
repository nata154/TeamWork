package com.epam.tat21.crypto.ui.steps;

import static com.epam.tat21.crypto.ui.service.GlobalConstants.REGEX_FOR_SPACES;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.businessObjects.Countries;
import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.driver.DriverFactory;
import com.epam.tat21.crypto.ui.driver.LocalDriver;
import com.epam.tat21.crypto.ui.driver.RemoteDriver;
import com.epam.tat21.crypto.ui.driver.RemoteDriverSauceLabs;
import com.epam.tat21.crypto.ui.pages.CoinsPage;
import com.epam.tat21.crypto.ui.pages.ExchangesPage;
import com.epam.tat21.crypto.ui.pages.HeaderPage;
import com.epam.tat21.crypto.ui.pages.MainCryptoComparePage;
import com.epam.tat21.crypto.ui.pages.NewsPage;
import com.epam.tat21.crypto.ui.pages.PortfolioPage;
import com.epam.tat21.crypto.ui.pages.UserAccountPage;
import com.epam.tat21.crypto.ui.service.UserCreator;
import com.epam.tat21.crypto.ui.utils.MyLogger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

    private WebDriver driver;
    private ExchangesPage exchangesPage;
    private NewsPage newsPage;
    private PortfolioPage portfolioPage;

    public DriverFactory getWebDriverFactory() {
        if (driver == null) {
            switch (System.getProperty("driver")) {
                case "local":
                    return new LocalDriver();
                case "remote":
                    return new RemoteDriver();
                case "sauce":
                    return new RemoteDriverSauceLabs();
                default:
                    return new LocalDriver();
            }
        }
        return new LocalDriver();
    }

    public void openBrowser() {
        driver = getWebDriverFactory().getDriver();
    }

    public void closeBrowser() {
        getWebDriverFactory().closeDriver();
    }

    @Given("I login user")
    public MainCryptoComparePage loginUser() {
        User user = UserCreator.withCredentialsFromProperty();
        return new MainCryptoComparePage(driver)
                .openPage()
                .login(user);
    }

    @When("I go to User Account profile")
    public UserAccountPage goToUserAccountProfile() {
        new HeaderPage(driver).getDropdownUserMenuInHeader().clickAccountLine();
        return new UserAccountPage(driver).clickTabGeneral();
    }

    @And("Change And save ([^\"]*) , ([^\"]*) in User Account")
    public UserAccountPage changeAndSaveFirstNameSurnameInUserAccount(String firstNameToChange, String surnameToChange) {
        return new UserAccountPage(driver)
                .enterNewFirstNameAndSurname(firstNameToChange, surnameToChange)
                .clickButtonSaveChanges();
    }

    @And("Get info from in User Account")
    public String getInfoFromPopupWindowAfterSavingChangesInUserAccount() {
        return new UserAccountPage(driver).getInfoFromPopupWindow();
    }

    @When("I open Exchange page")
    public ExchangesPage openExchangePage() {
        return exchangesPage = new ExchangesPage(driver).
                openPage();
    }

    @And("Filter news by country$")
    public ExchangesPage filterByCountry(Countries country) {
        return exchangesPage.
                clickOnCountryDropdown().
                selectCountryInDropdown(country);
    }

    @And("Get from FilteredPage number of results$")
    public int getFromFilteredPageNumberOfResultsWith(Countries country) {
        return exchangesPage.getFromFilteredPageAllResultsWith(country).size();
    }

    @And("Get all country labels from Filtered Page")
    public int getAllCountryLabelsFromFilteredPage() {
        return exchangesPage.getAllCountryLabelsFromFilteredPage().size();
    }

    @And("Get number of Exchanges from country badge")
    public int getNumberOfExchangesFromCountryBadge() {
        return exchangesPage.getNumberOfExchangesInBadge();
    }

    @When("I open News page")
    public NewsPage openNewsPage() {
        return newsPage = new NewsPage(driver).
                openPage();
    }

    @And("Filter news by coin$")
    public NewsPage filterByCoin(Coin coin) {
        return newsPage.goToCoinNews(coin);
    }

    @And("Get all coin news from FilteredPage$")
    public int getAllCoinNewsFromFilteredPage(Coin coin) {
        return newsPage.getNumberOfNewsForCoin(coin);
    }

    /**
     * The method below, if the page contains more than 50 titles,
     * returns a subarray of only 50 latest titles, because the api response
     * always contains only 50.
     */
    @And("Get latest news title items from page")
    public String[] getLatestNewsTitleItemsFromPage() {
        List<WebElement> newsTitles = newsPage.getAllNewsArticleTitle();
        if (newsTitles.size() <= 50) {
            MyLogger.info("Getting " + newsTitles.size() + " news titles from page");
            //get the text from news titles, fill a sorted array by them and replace from them two and more spaces and no-break spaces
            return newsTitles.stream().map(newsTitle -> newsTitle.getText().replaceAll(REGEX_FOR_SPACES, " ")).sorted().toArray(String[]::new);
        } else {
            MyLogger.info("Getting only 50 news titles from page, because the page contains " + newsTitles.size());
            //get the text from news titles, fill a sorted subarray by them and replace from them two and more spaces and no-break spaces
            return IntStream.range(0, 50).mapToObj((i -> newsTitles.get(i).getText().replaceAll(REGEX_FOR_SPACES, " "))).sorted().toArray(String[]::new);
        }
    }

    @When("Create User Portfolio ([^\"]*) , ([^\"]*) , ([^\"]*)")
    public PortfolioPage createUserPortfolio(String name, String currency, String description) {
        return portfolioPage = new HeaderPage(driver).
                goToMyPortfolioFromPortfolioTab().
                addPortfolioForm().
                createNewPortfolio(name, currency, description);
    }

    @Then("([^\"]*) is Portfolio present")
    public boolean isPortfolioPresent(String name) {
        return new PortfolioPage(driver).
                getElementPortfolio(name).
                isEnabled();
    }

    @When("Change User Portfolio name to ([^\"]*)")
    public PortfolioPage changeUserPortfolioName(String name) {
        return portfolioPage.
                getEditPortfolioForm().
                editUserPortfolio(name);
    }

    @When("Delete User Portfolio")
    public PortfolioPage deleteUserPortfolio() {
        return portfolioPage.
                getEditPortfolioForm().
                deleteUserPortfolio().
                confirmDeletion();
    }

    @Then("([^\"]*) is Portfolio delete")
    public boolean isPortfolioDelete() {
        return portfolioPage.
                isPortfolioDelete();
    }

    @When("I open Coins page")
    public CoinsPage openCoinsPage() {
        return new CoinsPage(driver).
                openPage();
    }
    
    public PortfolioPage addCoinToUserPortfolio(Coin coin, String amount, String price) {
    	return portfolioPage.
    			getAddCoinForm().
    			addCoinInPortfolio(coin, amount, price);
	}
    
    public boolean isCoinAdded() {
		return portfolioPage.
				isCoinAdded();
	}
    
    public PortfolioPage changeAmountOfCoins(String amount) {
		return portfolioPage.
				getEditCoinForm().
				editAmountOfCoin(amount);      
    }
    
    public boolean isAmountOfCoinChanged() {
        return portfolioPage.
               isAmountOfCoinChanged();
    }
    
    public PortfolioPage deleteCoinFromUserPortfolio() {
		return portfolioPage.
				getEditCoinForm().
				deleteCoinFromPortfolio().
				confirmCoinDeletion();
    }
    
    public boolean isCoinDelete() {
        return portfolioPage.
        		isCoinDelete();
    }
}

package com.epam.tat21.crypto.ui.steps;

import static com.epam.tat21.crypto.ui.service.GlobalConstants.REGEX_FOR_SPACES;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.businessObjects.Countries;
import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.pages.CoinsPage;
import com.epam.tat21.crypto.ui.pages.ExchangesPage;
import com.epam.tat21.crypto.ui.pages.HeaderPage;
import com.epam.tat21.crypto.ui.pages.MainCryptoComparePage;
import com.epam.tat21.crypto.ui.pages.NewsPage;
import com.epam.tat21.crypto.ui.pages.PortfolioPage;
import com.epam.tat21.crypto.ui.pages.UserAccountPage;
import com.epam.tat21.crypto.ui.service.UserCreator;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.RandomString;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {
	
    private static final int COUNT_OF_GENERATED_POSTFIX_LETTERS = 3;
    private static final int COUNT_OF_SYMBOLS = 5;
    private static final int LIMIT = 1000;
    private static final int COUNT_OF_NEWS = 50;
	private Coin coin = Coin.BTC;
	private String currency = coin.getAbbreviationCoin();
	private String portfolioName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
	private String changedName = RandomString.getRandomString(COUNT_OF_SYMBOLS);
	
    private WebDriver driver;
    private ExchangesPage exchangesPage;
    private NewsPage newsPage;
    private PortfolioPage portfolioPage;

    public Steps() {
        this.driver = DriverManager.getWebDriverFactory().getDriver();
    }

    public void closeBrowser() {
        DriverManager.getWebDriverFactory().closeDriver();
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

    public UserAccountPage changeAndSaveFirstNameSurnameInUserAccount(String firstNameToChange, String surnameToChange) {
        return new UserAccountPage(driver)
                .enterNewFirstNameAndSurname(firstNameToChange, surnameToChange)
                .clickButtonSaveChanges();
    }
    
    @And("Change And save First name and Surname in User Account")
    public UserAccountPage changeAndSaveDataInUserAccount() {
    	String firstNameToChange = RandomString.generateRandomUserFirstName(COUNT_OF_GENERATED_POSTFIX_LETTERS);
        String surnameToChange = RandomString.generateRandomUserSurname(COUNT_OF_GENERATED_POSTFIX_LETTERS);
        return new UserAccountPage(driver)
                .enterNewFirstNameAndSurname(firstNameToChange, surnameToChange)
                .clickButtonSaveChanges();
    }
    
    @And("Get info from in User Account")
    public String getInfoFromPopupWindowAfterSavingChangesInUserAccount() {
        return new UserAccountPage(driver).getInfoFromPopupWindow();
    }

    @Given("I open Exchange page")
    public ExchangesPage openExchangePage() {
        return exchangesPage = new ExchangesPage(driver).
                openPage();
    }
    
    public ExchangesPage filterByCountry(Countries country) {
        return exchangesPage.
                clickOnCountryDropdown().
                selectCountryInDropdown(country);
    }

    @When("Filter news by ([^\"]*)$")
    public ExchangesPage filterByCountry(String country) {
        return exchangesPage.
                clickOnCountryDropdown().
                selectCountryInDropdown(country);
    }
    
    public int getFromFilteredPageNumberOfResultsWith(Countries country) {
        return exchangesPage.getFromFilteredPageAllResultsWith(country).size();
    }

    @And("Get from FilteredPage number of results$")
    public int getFromFilteredPageNumberOfResultsWith(String country) {
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
    
    @Then("Is number of results equals number from ([^\"]*) label")
    public void isExchangesNumberOnFilteredPageEqualNumberFromLabel(String country) {
    	int numberOfResultWithCountry = getFromFilteredPageNumberOfResultsWith(country);
    	int numberOfAllCountryLabels = getAllCountryLabelsFromFilteredPage();
    	Assert.assertEquals(numberOfResultWithCountry, numberOfAllCountryLabels);
    }
    
    @Then("Is number of results equals number from ([^\"]*) badge")
    public void isExchangesNumberOnFilteredPageEqualNumberFromBadge(String country) {
    	int numberOfResultWithCountry = getFromFilteredPageNumberOfResultsWith(country);
        int numberFromCountryBadge = getNumberOfExchangesFromCountryBadge();
        Assert.assertEquals(numberOfResultWithCountry, numberFromCountryBadge);
    }

    @Given("I open News page")
    public NewsPage openNewsPage() {
        return newsPage = new NewsPage(driver).
                openPage();
    }
    
    public NewsPage filterByCoin(Coin coin) {
        return newsPage.goToCoinNews(coin);
    }

    @When("Sort news by ([^\"]*)")
    public NewsPage filterByCoin(String abbreviationCoin) {
        return newsPage.goToCoinNews(abbreviationCoin);
    }
    
    public int getAllCoinNewsFromFilteredPage(Coin coin) {
        return newsPage.getNumberOfNewsForCoin(coin);
    }

    @And("Get all coin news from FilteredPage$")
    public int getAllCoinNewsFromFilteredPage(String abbreviationCoin) {
        return newsPage.getNumberOfNewsForCoin(abbreviationCoin);
    }
    
    @Then("Are the news sorted by ([^\"]*)")
    public void areTheNewsSortedByCoin(String abbreviationCoin) {
    	int numberOfResultWithCoin = getAllCoinNewsFromFilteredPage(abbreviationCoin);
    	 Assert.assertEquals(numberOfResultWithCoin, COUNT_OF_NEWS);
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

    public PortfolioPage createUserPortfolio(String name, String currency, String description) {
        return portfolioPage = new HeaderPage(driver).
                goToMyPortfolioFromPortfolioTab().
                addPortfolioForm().
                createNewPortfolio(name, currency, description);
    }
    
    public boolean isPortfolioPresent(String name) {
        return new PortfolioPage(driver).
                getElementPortfolio(name).
                isEnabled();
    }
    
    @When("Create User Portfolio")
    public PortfolioPage createUserPortfolio() {
    	String description = RandomString.getRandomString(COUNT_OF_SYMBOLS);
		
        return portfolioPage = new HeaderPage(driver).
                goToMyPortfolioFromPortfolioTab().
                addPortfolioForm().
                createNewPortfolio(portfolioName, currency, description);
    }
    
    @Then("Is Portfolio present")
    public void isPortfolioPresent() {
       boolean portfolioPresence = new PortfolioPage(driver).
                getElementPortfolio(portfolioName).
                isEnabled();
       assertTrue(portfolioPresence);
    }
    
    @Then("Is Portfolio with changed name present")
    public void isPortfolioWithChangedNamePresent() {
        boolean presenceChangedPortfolio = new PortfolioPage(driver).
                getElementPortfolio(changedName).
                isEnabled();
        assertTrue(presenceChangedPortfolio);
    }

    public PortfolioPage changeUserPortfolioName(String name) {
        return portfolioPage.
                getEditPortfolioForm().
                editUserPortfolio(name);
    }
    
    @When("Change User Portfolio name")
    public PortfolioPage changeUserPortfolioName() {
        return portfolioPage.
                getEditPortfolioForm().
                editUserPortfolio(changedName);
    }

    @When("Delete User Portfolio")
    public PortfolioPage deleteUserPortfolio() {
        return portfolioPage.
                getEditPortfolioForm().
                deleteUserPortfolio().
                confirmDeletion();
    }
    
    public boolean isPortfolioDelete() {
        return portfolioPage.
                isPortfolioDelete();
    }

    @Then("Is Portfolio delete")
    public void isPortfolioDeleted() {
        boolean deletingPortfolio = portfolioPage.
                isPortfolioDelete();
        assertTrue(deletingPortfolio);
    }

    @When("I open Coins page")
    public CoinsPage openCoinsPage() {
        return new CoinsPage(driver).
                openPage();
    }
    
    @Then("I check updating User Account")
    public void checkUpdatingUserAccount() {
    	String infoInPopup = getInfoFromPopupWindowAfterSavingChangesInUserAccount();
    	String expectedInfoInPopupWhenSuccessfully = "Successfully updated your details";
    	 Assert.assertEquals(infoInPopup, expectedInfoInPopupWhenSuccessfully,
                "Wrong text in pop-up after saving changes in user general tab");
    }


    public PortfolioPage addCoinToUserPortfolio(Coin coin, String amount, String price) {
        return portfolioPage.
                getAddCoinForm().
                addCoinInPortfolio(coin, amount, price);
    }
    
    @And ("Add Coin to user portfolio")
    public PortfolioPage addCoinToUserPortfolio() {
    	Coin coin = Coin.BTC;
    	String amount = RandomString.getRandomNumber(LIMIT); 
    	String price = RandomString.getRandomNumber(LIMIT);
        return portfolioPage.
                getAddCoinForm().
                addCoinInPortfolio(coin, amount, price);
    }
    
    public boolean isCoinAdded() {
        return portfolioPage.
                isCoinAdded();
    }

    @Then("Is Coin added")
    public void isCoinAddedToPortfolio() {
        boolean addedCoin = portfolioPage.
                isCoinAdded();
        assertTrue(addedCoin);
    }

    public PortfolioPage changeAmountOfCoins(String amount) {
        return portfolioPage.
                getEditCoinForm().
                editAmountOfCoin(amount);
    }
    
    @And("Change amount of coins")
    public PortfolioPage changeAmountOfCoins() {
    	String changedAmount = RandomString.getRandomNumber(LIMIT);
        return portfolioPage.
                getEditCoinForm().
                editAmountOfCoin(changedAmount);
    }
    
    public boolean isAmountOfCoinChanged() {
        return portfolioPage.
                isAmountOfCoinChanged();
    }

    @Then("Is amount of coins changed")
    public void isAmountOfCoinChangedInPortfolio() {
        boolean changingAmount = portfolioPage.
                isAmountOfCoinChanged();
        assertTrue(changingAmount);
    }

    @And("Delete coin from user portfolio")
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

    @Then("Is coin deleted")
    public void isCoinDeleted() {
        boolean deletingCoin = portfolioPage.
                isCoinDelete();
        assertTrue(deletingCoin);
    }
}

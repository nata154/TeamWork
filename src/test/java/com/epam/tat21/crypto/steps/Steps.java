package com.epam.tat21.crypto.steps;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Countries;
import com.epam.tat21.crypto.bo.User;
import com.epam.tat21.crypto.driver.DriverFactory;
import com.epam.tat21.crypto.driver.LocalDriver;
import com.epam.tat21.crypto.driver.RemoteDriver;
import com.epam.tat21.crypto.driver.RemoteDriverSauceLabs;
import com.epam.tat21.crypto.pages.*;
import com.epam.tat21.crypto.service.UserCreator;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.IntStream;

import static com.epam.tat21.crypto.service.GlobalConstants.REGEX_FOR_SPACES;

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

    public MainCryptoComparePage loginUser() {
        User user = UserCreator.withCredentialsFromProperty();
        return new MainCryptoComparePage(driver)
                .openPage()
                .login(user);
    }

    public UserAccountPage goToUserAccountProfile() {
        new HeaderPage(driver).getDropdownUserMenuInHeader().clickAccountLine();
        return new UserAccountPage(driver).clickTabGeneral();
    }

    public UserAccountPage changeAndSaveFirstNameSurnameInUserAccount(String firstNameToChange, String surnameToChange) {
        return new UserAccountPage(driver)
                .enterNewFirstNameAndSurname(firstNameToChange, surnameToChange)
                .clickButtonSaveChanges();
    }

    public String getInfoFromPopupWindowAfterSavingChangesInUserAccount() {
        return new UserAccountPage(driver).getInfoFromPopupWindow();
    }

    public ExchangesPage openExchangePage() {
        return exchangesPage = new ExchangesPage(driver).
                openPage();
    }

    public ExchangesPage filterByCountry(Countries country) {
        return exchangesPage.
                clickOnCountryDropdown().
                selectCountryInDropdown(country);
    }

    public int getFromFilteredPageNumberOfResultsWith(Countries country) {
        return exchangesPage.getFromFilteredPageAllResultsWith(country).size();
    }

    public int getAllCountryLabelsFromFilteredPage() {
        return exchangesPage.getAllCountryLabelsFromFilteredPage().size();
    }

    public int getNumberOfExchangesFromCountryBadge() {
        return exchangesPage.getNumberOfExchangesInBadge();
    }

    public NewsPage openNewsPage() {
        return newsPage = new NewsPage(driver).
                openPage();
    }

    public NewsPage filterByCoin(Coin coin) {
        return newsPage.goToCoinNews(coin);
    }

    public int getAllCoinNewsFromFilteredPage(Coin coin) {
        return newsPage.getNumberOfNewsForCoin(coin);
    }

    /**
     * The method below, if the page contains more than 50 titles,
     * returns a subarray of only 50 latest titles, because the api response
     * always contains only 50.
     */

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

    public PortfolioPage changeUserPortfolioName(String name) {
        return portfolioPage.
                getEditPortfolioForm().
                editUserPortfolio(name);
    }

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

    public MainCryptoComparePage clickLogOut() {
        return portfolioPage.getDropdownUserMenuInHeader().clickLogOutLine();
    }
}

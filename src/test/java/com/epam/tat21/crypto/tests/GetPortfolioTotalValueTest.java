package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.bo.User;
import com.epam.tat21.crypto.pages.MainCryptoComparePage;
import com.epam.tat21.crypto.pages.PortfolioPage;
import com.epam.tat21.crypto.service.UserCreator;
import com.epam.tat21.crypto.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetPortfolioTotalValueTest extends CommonConditions {

    private User user;

    @BeforeClass
    public void setUp() {
        steps = new Steps();
        steps.openBrowser();
    }

    @Test
    public void getPortfolioTotalValueTest() throws InterruptedException {
        MainCryptoComparePage mainPage = new MainCryptoComparePage(steps.getDriver());
        mainPage.openPage();
        user = UserCreator.withCredentialsFromProperty();
        mainPage.login(user);
        Thread.sleep(5000);
        PortfolioPage portfolioPage = mainPage.goToPortfolioPage();
        portfolioPage.getAllPortfolioTabs();
        System.out.println("TOTAL VALUE OF PORTFOLIO - "+portfolioPage.getPortfolioTotalValue("BITTREX"));
        Assert.assertEquals(0,0);

        /*
        * На странице PortfolioPage присутствует метод getPortfolioTotalValue, в который передается имя нужного портфолио.
        * В начале работы данный метод создает класс PortfolioKeeper, который забирает со страницы все элементы, в которых
        * указаны имена портфолио и создает экземпляр класса
        *
        *
        *
        * */


    }

    @AfterClass
    public void tearDown() {
        // steps.closeBrowser();
    }
}

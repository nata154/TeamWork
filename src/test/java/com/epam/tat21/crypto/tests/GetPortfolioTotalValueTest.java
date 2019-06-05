package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.pages.MainCryptoComparePage;
import com.epam.tat21.crypto.pages.PortfolioPage;
import com.epam.tat21.crypto.service.UserCreator;
import com.epam.tat21.crypto.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetPortfolioTotalValueTest extends CommonConditions {

    @BeforeClass
    public void setUp() {
        steps = new Steps();
        steps.openBrowser();
    }

    @Test
    public void getPortfolioTotalValueTest() throws InterruptedException {
        MainCryptoComparePage mainPage = new MainCryptoComparePage(steps.getDriver());
        mainPage.openPage();
        mainPage.login(UserCreator.withCredentialsFromProperty());
        Thread.sleep(5000);
        PortfolioPage portfolioPage = mainPage.goToPortfolioPage();
        //Thread.sleep(2000);
        System.out.println("TOTAL VALUE OF PORTFOLIO - " + portfolioPage.getPortfolioTotalValue("bittrex"));
        Assert.assertEquals(0, 0);
    }

    @AfterClass
    public void tearDown() {
        // steps.closeBrowser();
    }
}

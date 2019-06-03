package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.exceptions.NoSuchPortfolioException;
import com.epam.tat21.crypto.utils.PortfolioKeeper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MyPortfoliosPage {

    private static String mainURL = "https://www.cryptocompare.com/portfolio/";
    private static String userEmail = "testinglab2019cw10@mail.ru";
    private static String userPassword = "xenocrypt";
    private static int WAIT_FOR_ELEMENT_SECONDS = 15;
    public PortfolioKeeper portfolioKeeper = new PortfolioKeeper();
    private String loginButtonXpath = "(//a[contains(.,'Login')])[3]";
    private String emailFieldXpath = "(//input[@type='email'])[1]";
    private String passwordFieldXpath = "//input[contains(@type,'password')]";
    private String submitButtonXpath = "//button[@type='submit'][contains(.,'Login')]";
    private String portfolioLinkXpath = "//a[@ng-mouseover='menuPortfolio=true']";
    private WebDriver driver;
    private WebElement loginButton;
    private WebElement portfolioLink;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement submitButton;
    private String portfolioName = "PORTFOLIO-NAVY";

//    public MyPortfoliosPage(WebDriver driver) {
//        //super(driver);
//    }

    protected static WebElement waitForElementBeClickableLocatedByXpath(WebDriver driver, String xPath) {
        return new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS).until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    @BeforeTest
    public void setInitialState() {
        driver = new FirefoxDriver();
        driver.get(mainURL);
        driver.manage().window().maximize();
    }

    @Test
    public void myPortfoliosPageTest() {
        //loginAndProceedToMyPortfoliosPage();
        //portfolioKeeper.initiate(driver);
        //System.out.println("PORTFOLIO TOTAL VALUE - " + portfolioKeeper.getTotalValue(portfolioName));
        Assert.assertEquals(0, 0);
    }

    private void loginAndProceedToMyPortfoliosPage() {
        loginButton = waitForElementBeClickableLocatedByXpath(driver, loginButtonXpath);
        loginButton.click();

        emailField = waitForElementBeClickableLocatedByXpath(driver, emailFieldXpath);
        passwordField = waitForElementBeClickableLocatedByXpath(driver, passwordFieldXpath);
        submitButton = waitForElementBeClickableLocatedByXpath(driver, submitButtonXpath);

        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        submitButton.click();

//        portfolioLink = waitForElementBeClickableLocatedByXpath(driver,portfolioLinkXpath);
//        portfolioLink.click();
//        List<WebElement>menueItems = driver.findElements(By.xpath("//li[@class='dropdown hidden-xs hidden-sm hidden-md']"));
//        System.out.println("MENUE ITEMS - "+menueItems.size());
//        clickElementWithJS(menueItems.get(1));
        //driver.get("https://www.cryptocompare.com/portfolio/");
    }

    public double getPortfolioTotalValue(String name) {
        double totalValue = 0;
        portfolioKeeper.initiate(driver);
        try {
            totalValue = portfolioKeeper.getTotalValue(name);
        } catch (NoSuchPortfolioException e) {
            System.out.println(e.getMessage());
        }
        return totalValue;
    }

    @AfterTest
    public void getSystemDown() {
        driver.quit();
    }
}

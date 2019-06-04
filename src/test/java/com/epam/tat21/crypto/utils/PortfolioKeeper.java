package com.epam.tat21.crypto.utils;

import com.epam.tat21.crypto.exceptions.NoSuchPortfolioException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PortfolioKeeper {

    private WebDriver driver;

    private String portfolioElementTagName = "md-tab-item";
    //private String portfolioElementXpath = "//md-tab-it
    // em/span[1]";
    private String portfolioElementXpath = "//md-tab-item";
    //private String portfolioElementXpath = "//span[@class='ng-binding ng-scope']";
    //md-tab-item/span[1]

    // <span ng-bind-html="portfolioData.Name" class="ng-binding ng-scope">BITTREX</span>

    //private String portfolioElementXpath = "//span[@ng-bind-html='portfolioData.Name']|//span[@class='ng-binding ng-scope']";
    //css="div[class='ajax_enabled'] [style='display:block']"
    private String portfolioElementCSS = "span[ng-bind-html='portfolioData.Name'][class='ng-binding ng-scope']";

     /*




    <md-tab-item tabindex="-1"
     class="md-tab ng-scope ng-isolate-scope md-ink-ripple"
      role="tab" aria-disabled="false"
       aria-selected="false"
       aria-controls="tab-content-1"
       style="max-width: 750px;"
       ng-class="{ 'md-active':    tab.isActive(), 'md-focused':   tab.hasFocus(), 'md-disabled':  tab.scope.disabled }"
       ng-click="$mdTabsCtrl.select(tab.getIndex())"
        ng-repeat="tab in $mdTabsCtrl.tabs"
         ng-disabled="tab.scope.disabled"
          md-scope="::tab.parent"
           md-tabs-template="::tab.label"
       md-swipe-right="$mdTabsCtrl.previousPage()"
        md-swipe-left="$mdTabsCtrl.nextPage()">
									<span class="ng-binding ng-scope" ng-bind-html="portfolioData.Name">BITTREX</span>
									<!-- ngIf: portfolioData.Access=='Public' --><span class="fa fa-globe ng-scope" ng-if="portfolioData.Access=='Public'"></span><!-- end ngIf: portfolioData.Access=='Public' -->
									<!-- ngIf: portfolioData.Access=='Private' -->
								<div class="md-ripple-container"></div></md-tab-item>





    */


    public PortfolioKeeper() {}

    private ArrayList<SimplePortfolio>portfoliosList = new ArrayList<>();

    public List<WebElement> getAllPortfolioTabs() {
        return driver.findElements(By.xpath(portfolioElementXpath));
    }

    public void initiate(WebDriver driver) {

        this.driver = driver;

        WebElement clicker = driver.findElements(By.xpath(portfolioElementXpath)).get(1);
        clicker.click();

        // WebElement waitingStub = waitForElementBeClickableLocatedByXpath(driver,"//a[contains(.,'Read our guide: Portfolio FAQ')]");

        //List<WebElement> portfolioElements = driver.findElements(By.tagName(portfolioElementTagName));
        List<WebElement> portfolioElements = driver.findElements(By.xpath(portfolioElementXpath));
        //System.out.println("AMOUNT OF PORTFOLIOS: - " + portfolioElements.size());

        //System.out.println("Taking names.....");


        for (int i = 0; i < getAllPortfolioTabs().size(); ++i) {
            SimplePortfolio portfolio = new SimplePortfolio(portfolioElements.get(i).getText(), portfolioElements.get(i));
            portfoliosList.add(portfolio);
            System.out.println("-" + portfolioElements.get(i).getText() + "-");
        }
    }

    public double getTotalValue(String portfolioName) throws NoSuchPortfolioException {

        double totalValue =0;
        for (int i=0;i<portfoliosList.size();++i) {
            //System.out.println("COMPARING-" + portfolioName + "- WITH -" + portfoliosList.get(i).getPortfolioName() + "-");
            if (portfolioName.equals(portfoliosList.get(i).getPortfolioName())) {
                //System.out.println("PORTFOLIO " + portfoliosList.get(i).getPortfolioName() + " HAS BEEN CHOSEN TO OPERATE ON POSITION " + (i + 1));
                    portfoliosList.get(i).getPortfolioLink().click();
                    portfoliosList.get(i).initiate(driver);
                    totalValue = portfoliosList.get(i).getTotalValue();
            } //else throw new NoSuchPortfolioException();
        }

        return totalValue;
    }



    /*
     * TODO
     * - переписать исключения про нет портфолио эксепшн
     * - добавить исключение - нет монет в портфолио эксепшн
     * - переписать парсер в класс, а код отрезающих функций в методы
     *
     * */


    protected static WebElement waitForElementBeClickableLocatedByXpath(WebDriver driver, String xPath) {
        return new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }
}

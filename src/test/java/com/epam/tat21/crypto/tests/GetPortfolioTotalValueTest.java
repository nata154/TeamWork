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
         * указаны имена портфолио, помещает их в список portfolioElements, на основании которого создается необходимое количество
         * объектов класса SimplePortfolio - с именем портфолио, взятого из списка портфолио на странице и веб-элемента
         * как такового(как кликабельный объект).
         *
         * Далее, в экземпляре класса portfolioKeeper вызывается метод getTotalValue, в который передается имя
         * необходимого портфолио. Этот метод ищет среди всех объектов SimplePortfolio то, с которым совпадает
         * переданное в метод имя и вызывает клик этого элемента - т.е. как бы нужный элемент кликает сам себя.
         *
         * Далее страница переключается на нужное портфолио, и происходит сбор дальнейшей информации -
         * количества монет в данном портфолио. По аналогичной логике создается список объектов
         * класса SimpleCoin, в которые через конструктор передаются имя монеты и значение TotalValue.
         *
         * Далее происходит подсчет всех значений TotalValue у всех монет, вызывая метод getCoinTotalValue у каждой монеты,
         * суммируется и возвращается в нужный метод.

         *
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

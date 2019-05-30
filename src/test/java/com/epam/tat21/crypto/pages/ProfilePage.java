package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.service.TestDataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "profile/" + TestDataReader.getProfileName() + "/";
    private String locatorUserLineInHeader = "//ul[@class='nav navbar-nav navbar-right ng-scope']//span[contains(text(), '" + TestDataReader.getProfileName() + "')]";

    @FindBy(xpath = "//a[@href='/quiz/']")
    private WebElement quizLine;

    @FindBy(xpath = "//a[starts-with(@href,'/profile/')]")
    private WebElement viewMyProfileLine;

    @FindBy(xpath = "//a[@href='/cryptopian/general']")
    private WebElement accountLine;

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right ng-scope']//a[@href='/cryptopian/api-keys']")
    private WebElement apiKeysLine;

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right ng-scope']//a[@href='/cryptopian/billing']")
    private WebElement billingLine;

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right ng-scope']//a[@class='ng-binding']")
    private WebElement turnOffLine;

    @FindBy(xpath = "//a[@ng-click='userManager.logout()']")
    private WebElement logoutLine;

    @FindBy(xpath = "//a[@href='#/activity']")
    private WebElement activityTabInUserProfile;

    @FindBy(xpath = "//a[@href='#/posts']")
    private WebElement postsTabInUserProfile;

    @FindBy(xpath = "//a[@href='#/reviews']")
    private WebElement reviewsTabInUserProfile;

    @FindBy(xpath = "//a[@href='#/following']")
    private WebElement followingTabInUserProfile;

    @FindBy(xpath = "//a[@href='#/followers']")
    private WebElement followersTabInUserProfile;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HeaderPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public ProfilePage clickUserLineInHeader() {
        driver.findElement(By.xpath(locatorUserLineInHeader)).click();
        return this;
    }

    public ProfilePage clickQuizLine() {
        waitForElementClicable(quizLine);
        quizLine.click();
        return this;
    }

    public ProfilePage clickViewMyProfile() {
        waitForElementClicable(viewMyProfileLine);
        viewMyProfileLine.click();
        return this;
    }

    public ProfilePage clickAccountLine() {
        waitForElementClicable(accountLine);
        accountLine.click();
        return this;
    }

    public ProfilePage clickApiKeysLine() {
        waitForElementClicable(apiKeysLine);
        apiKeysLine.click();
        return this;
    }

    public ProfilePage clickBillingLine() {
        waitForElementClicable(billingLine);
        billingLine.click();
        return this;
    }

    public ProfilePage clickTurnOffLine() {
        waitForElementClicable(turnOffLine);
        turnOffLine.click();
        return this;
    }

    public ProfilePage clickLogOutLine() {
        waitForElementClicable(logoutLine);
        logoutLine.click();
        return this;
    }

    public ProfilePage clickActivityTabInUserProfile() {
        waitForElementClicable(activityTabInUserProfile);
        activityTabInUserProfile.click();
        return this;
    }

    public ProfilePage clickPostsTabInUserProfile() {
        waitForElementClicable(postsTabInUserProfile);
        postsTabInUserProfile.click();
        return this;
    }

    public ProfilePage clickReviewsTabInUserProfile() {
        waitForElementClicable(reviewsTabInUserProfile);
        reviewsTabInUserProfile.click();
        return this;
    }

    public ProfilePage clickFollowingTabInUserProfile() {
        waitForElementClicable(followingTabInUserProfile);
        followingTabInUserProfile.click();
        return this;
    }

    public ProfilePage clickFollowersTabInUserProfile() {
        waitForElementClicable(followersTabInUserProfile);
        followersTabInUserProfile.click();
        return this;
    }
}

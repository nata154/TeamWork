package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "profile/" + TestDataReader.getProfileName() + "/";

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
    public ProfilePage openPage() {
        driver.navigate().to(BASE_URL);
        MyLogger.info("ProfilePage was opened");
        return this;
    }

    public ProfilePage clickActivityTabInUserProfile() {
        waitForElementClickable(activityTabInUserProfile);
        activityTabInUserProfile.click();
        return this;
    }

    public ProfilePage clickPostsTabInUserProfile() {
        waitForElementClickable(postsTabInUserProfile);
        postsTabInUserProfile.click();
        return this;
    }

    public ProfilePage clickReviewsTabInUserProfile() {
        waitForElementClickable(reviewsTabInUserProfile);
        reviewsTabInUserProfile.click();
        return this;
    }

    public ProfilePage clickFollowingTabInUserProfile() {
        waitForElementClickable(followingTabInUserProfile);
        followingTabInUserProfile.click();
        return this;
    }

    public ProfilePage clickFollowersTabInUserProfile() {
        waitForElementClickable(followersTabInUserProfile);
        followersTabInUserProfile.click();
        return this;
    }
}

package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.elements.menus.HeaderMenu;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class ProfilePage extends BasePage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "profile/" + TestDataReader.getProfileName() + "/";
    private HeaderMenu headerMenu;

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

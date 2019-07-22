package com.epam.tat21.crypto.mobile.steps.stepDefinition;

import com.epam.tat21.crypto.mobile.pages.MainCryptoComparePageMobile;
import com.epam.tat21.crypto.mobile.service.PreConditionsOfMobileTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition extends PreConditionsOfMobileTest {

    @Given("^I login user in crypto application$")
    public MainCryptoComparePageMobile ILoginUser() {
        return mobSteps.loginUser();
    }

    @When("^I click user account and log out$")
    public MainCryptoComparePageMobile IClickUserAccountAndLogOut() {
        return mobSteps.clickUserAccountAndLogOut();
    }

    @Then("^I check log out - I see password field$")
    public boolean checkLogout() {
        return mobSteps.checkLogout();
    }
}

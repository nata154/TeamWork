package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.api.apisteps.ApiSteps;
import com.epam.tat21.crypto.steps.Steps;
import com.epam.tat21.crypto.utils.TestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

//import com.epam.tat21.crypto.utils.jira.JIRATestNGListener;

@Listeners({TestListener.class})
public class CommonConditions {

    protected Steps steps;
    protected ApiSteps apiSteps;

    @BeforeClass
    public void setUp() {
        steps = new Steps();
        apiSteps = new ApiSteps();
        steps.openBrowser();
    }

    @AfterClass
    public void tearDown() {
        steps.closeBrowser();
    }

}

package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.steps.Steps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class CommonConditions {

    protected Steps steps;
    private static final String TEAMWORK_APP_URL = "https://www.cryptocompare.com";

    @BeforeClass
    public void setUp() {
        steps = new Steps();
        steps.openBrowser(TEAMWORK_APP_URL);
    }

    @AfterClass
    public void tearDown() {
        steps.closeBrowser();
    }

}

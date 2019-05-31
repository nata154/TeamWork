package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.steps.Steps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class CommonConditions {

    protected Steps steps;

    @BeforeClass
    public void setUp() {
        steps = new Steps();
        steps.openBrowser();
    }

    @AfterClass
    public void tearDown() {
        steps.closeBrowser();
    }

}

package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.steps.Steps;
import com.epam.tat21.crypto.utils.TestListener;
import com.epam.testng.JIRATestNGListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class, JIRATestNGListener.class})
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

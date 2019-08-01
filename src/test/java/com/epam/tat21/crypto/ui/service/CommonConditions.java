package com.epam.tat21.crypto.ui.service;

import com.epam.tat21.crypto.api.steps.ApiSteps;
import com.epam.tat21.crypto.ui.steps.Steps;
import com.epam.tat21.crypto.ui.utils.TestListener;
import com.epam.testng.JIRATestNGListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class, JIRATestNGListener.class})
public class CommonConditions {

    protected Steps steps;
    protected ApiSteps apiSteps;

    @BeforeClass
    public void setUp() {
        steps = new Steps();
        apiSteps = new ApiSteps();
    }

    @AfterClass
    public void tearDown() {
        steps.closeBrowser();
        steps = null;
        apiSteps = null;
    }
}

package com.epam.tat21.crypto.mobile.service;

import com.epam.tat21.crypto.mobile.steps.MobSteps;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class PreConditionsOfMobileTest {

    protected MobSteps mobSteps;

    @BeforeSuite
    public void setUp() {
        mobSteps = new MobSteps();
    }

    @AfterSuite
    public void tearDown() {
        mobSteps.closeDevice();
    }

}

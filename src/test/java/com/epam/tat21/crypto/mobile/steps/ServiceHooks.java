package com.epam.tat21.crypto.mobile.steps;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ServiceHooks {

    protected MobSteps mobSteps;

    @BeforeSuite()
    public void setUp() {
        mobSteps = new MobSteps();
   }

    @AfterSuite
    public void stopBrowser() {
        mobSteps.closeDevice();
    }
}

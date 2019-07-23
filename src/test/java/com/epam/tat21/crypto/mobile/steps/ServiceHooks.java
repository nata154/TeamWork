package com.epam.tat21.crypto.mobile.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks {

    protected MobSteps mobSteps;

    @Before
    public void setUp() {
        mobSteps = new MobSteps();
   }

    @After
    public void stopBrowser() {
        mobSteps.closeDevice();
    }
}

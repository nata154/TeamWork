package com.epam.tat21.crypto.mobile.steps;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks {

    protected MobSteps mobSteps;

    @Before
    public void setUp() {
        MyLogger.info("Running device");
        mobSteps = new MobSteps();
   }

    @After
    public void stopBrowser() {
        MyLogger.info("Close device");
        mobSteps.closeDevice();
    }
}

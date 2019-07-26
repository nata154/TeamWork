package com.epam.tat21.crypto.ui.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ScenarioHooks {
protected Steps steps;
	
	@Before
    public void beforeScenario(){
		 steps = new Steps();
    }

    @After
    public void afterScenario(){
    	 steps.closeBrowser();
    }
}

package com.epam.tat21.crypto.ui.utils;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions( 
		features = "src/test/resources/features",
		glue = {"com.epam.tat21.crypto.ui.steps"}
		)

public class TestRunner extends AbstractTestNGCucumberTests{
}

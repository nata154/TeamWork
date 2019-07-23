package com.epam.tat21.crypto.mobile.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        strict = true,
        features = "src/test/resources/features/LogIn.feature",
        glue = "com.epam.tat21.crypto.mobile.steps",
        plugin = { "json:target/cucumber-report.json",
                "html:target/cucumber-report"
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {
}


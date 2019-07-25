package com.epam.tat21.crypto.mobile;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.epam.tat21.crypto.mobile.steps",
        plugin = { "json:target/cucumber-report.json",
                "html:target/cucumber-report"
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {
}


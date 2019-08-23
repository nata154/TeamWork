package com.epam.tat21.crypto.ui.service;

import com.epam.tat21.crypto.api.steps.ApiSteps;
import com.epam.tat21.crypto.ui.steps.Steps;
import com.epam.tat21.crypto.ui.utils.TestListener;
import com.epam.testng.JIRATestNGListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;

@ContextConfiguration(classes = SpringConfiguration.class)
@Listeners({TestListener.class, JIRATestNGListener.class})
public class CommonConditions extends AbstractTestNGSpringContextTests {

    @Autowired
    protected Steps steps;
    @Autowired
    protected ApiSteps apiSteps;


    @AfterClass
    public void tearDown() {
        steps.closeBrowser();
        //steps = null;
        apiSteps = null;
    }
}

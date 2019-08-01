package com.epam.tat21.crypto.ui.utils;

import com.epam.tat21.crypto.ui.driver.DriverManager;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        super.onTestFailure(iTestResult);
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        super.onTestSkipped(iTestResult);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        super.onTestSuccess(iTestResult);
    }

    private synchronized void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    TestDataReader.getScreenshotFolderPath()
                            + DateUtils.getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            MyLogger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

}

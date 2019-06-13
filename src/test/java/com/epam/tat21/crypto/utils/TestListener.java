package com.epam.tat21.crypto.utils;

import com.epam.tat21.crypto.driver.DriverProvider;
import com.epam.tat21.crypto.service.TestDataReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
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

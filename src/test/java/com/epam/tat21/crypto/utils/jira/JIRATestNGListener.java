package com.epam.tat21.crypto.utils.jira;

import com.epam.tat21.crypto.utils.jira.utilsForJira.resultconstructor.TestResultConstructor;
import com.epam.tat21.crypto.utils.jira.utilsForJira.resultconstructor.TestResultConstructorManager;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

import java.util.List;

public class JIRATestNGListener extends TestListenerAdapter implements IReporter{

    private TestResultConstructor testResultConstructor = TestResultConstructorManager.getInstance();

    @Override
    public void onFinish(ITestContext iTestContext) {
        super.onFinish(iTestContext);
        super.getSkippedTests().forEach(iTestResult -> testResultConstructor.putTestResultData(iTestResult, iTestResult.getStatus()));
        super.getPassedTests().forEach(iTestResult -> testResultConstructor.putTestResultData(iTestResult, iTestResult.getStatus()));
        super.getFailedTests().forEach(iTestResult -> testResultConstructor.putTestResultData(iTestResult, iTestResult.getStatus()));
        super.getFailedButWithinSuccessPercentageTests().forEach(iTestResult -> testResultConstructor.putTestResultData(iTestResult, iTestResult.getStatus()));
        testResultConstructor.proceedExcludedTestMethod(iTestContext);
    }

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        testResultConstructor.saveResultData();
    }
}

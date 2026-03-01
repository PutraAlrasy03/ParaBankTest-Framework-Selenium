package com.bank.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportingListener implements ITestListener {
    
    ExtentReports extent;
    ExtentTest test;
    String reportName;

    @Override
    public void onStart(ITestContext context) {
        // 1. Generate a unique timestamp for the file name (e.g., 2026.03.01.11.30.00)
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "ExtentReport_" + timestamp + ".html";
        
        // 2. Tell it where to save (Creates a new 'reports' folder inside 'target')
        String reportPath = System.getProperty("user.dir") + "/target/reports/" + reportName;
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        
        // 3. Make it beautiful! (Title, Report Name, and Dark Theme)
        spark.config().setDocumentTitle("ParaBank Automation Portfolio");
        spark.config().setReportName("UI & API Hybrid Test Execution");
        spark.config().setTheme(Theme.DARK); 
        
        extent = new ExtentReports();
        extent.attachReporter(spark);
        
        // 4. Attach System/Environment Info to the dashboard
        extent.setSystemInfo("Application", "ParaBank Demo");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Automation Engineer", "Putra Alrasy"); // 
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Creates a new test entry in the report
        test = extent.createTest(result.getMethod().getMethodName());
        
        // Groups the tests by their Class Name (e.g., "LoginTests" or "HybridTests")
        test.assignCategory(result.getMethod().getRealClass().getSimpleName()); 
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed Successfully!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed!");
        // This grabs the exact error (like the locator issue you found earlier) and puts it in the report
        test.log(Status.FAIL, result.getThrowable()); 
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped!");
        test.log(Status.SKIP, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // This is the most important line! It actually writes the data to the HTML file.
        extent.flush(); 
    }
}
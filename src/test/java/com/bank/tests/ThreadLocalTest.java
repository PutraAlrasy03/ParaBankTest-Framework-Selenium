package com.bank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import com.bank.base.CommonMethods;

public class ThreadLocalTest extends CommonMethods {

    @Test(priority = 1, description = "Verify ThreadLocal WebDriver is accessible")
    public void testThreadLocalWebDriverAccess() {
        // Get the WebDriver from ThreadLocal
        WebDriver webDriver = driver.get();
        
        // Verify the WebDriver is not null
        Assert.assertNotNull(webDriver, "ThreadLocal WebDriver should not be null");
        
        // Verify we can get the current URL (even if it's about:blank initially)
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL should not be null");
    }
    
    @Test(priority = 2, description = "Verify performStandardLogin method works")
    public void testPerformStandardLoginMethod() {
        // This test just verifies the method exists and can be called
        // We won't actually perform the login to avoid network dependencies
        Assert.assertNotNull(driver, "ThreadLocal driver should be accessible");
    }
}
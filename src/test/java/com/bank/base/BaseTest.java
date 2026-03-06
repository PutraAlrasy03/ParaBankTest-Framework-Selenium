package com.bank.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;

public class BaseTest {
    
    // ThreadLocal ensures each test thread gets its own WebDriver instance for parallel execution
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setup() {
        // 1. Setup UI (Selenium) - Create WebDriver instance for this thread
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        driver.set(webDriver);

        // 2. Setup API (RestAssured)
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank";
    }

    @AfterMethod
    public void teardown() {
        // Clean up WebDriver and remove ThreadLocal reference
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit(); // Closes the browser after EVERY test
            driver.remove(); // Critical: Remove ThreadLocal reference to prevent memory leaks
        }
    }
}

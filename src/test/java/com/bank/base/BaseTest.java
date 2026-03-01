package com.bank.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;

public class BaseTest {
    
    // Protected means test classes that extend BaseTest can use this driver
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // 1. Setup UI (Selenium)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 2. Setup API (RestAssured)
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank";
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Closes the browser after EVERY test
        }
    }
}
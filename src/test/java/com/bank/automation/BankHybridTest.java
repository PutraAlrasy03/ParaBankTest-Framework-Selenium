package com.bank.automation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BankHybridTest {
    
    WebDriver driver;

    @BeforeMethod
    public void setupUI() {
        // Selenium 4 automatically downloads and sets up ChromeDriver for you!
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1, description = "UI Test: Register a new user on ParaBank")
    public void testUI_RegisterNewUser() {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
        
        // Use a timestamp to generate a unique username every time the test runs
        String uniqueUsername = "user" + System.currentTimeMillis();
        
        // Fill out the registration form
        driver.findElement(By.id("customer.firstName")).sendKeys("John");
        driver.findElement(By.id("customer.lastName")).sendKeys("Doe");
        driver.findElement(By.id("customer.address.street")).sendKeys("123 Main St");
        driver.findElement(By.id("customer.address.city")).sendKeys("Tech City");
        driver.findElement(By.id("customer.address.state")).sendKeys("CA");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("90210");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("555-1234");
        driver.findElement(By.id("customer.ssn")).sendKeys("000-00-0000");
        driver.findElement(By.id("customer.username")).sendKeys(uniqueUsername);
        driver.findElement(By.id("customer.password")).sendKeys("SecurePass123!");
        driver.findElement(By.id("repeatedPassword")).sendKeys("SecurePass123!");
        
        // Click Register
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        
        // Assert that the success message appears
        String successMessage = driver.findElement(By.xpath("//div[@id='rightPanel']/p")).getText();
        Assert.assertTrue(successMessage.contains("Your account was created successfully"), 
                "Registration failed or success message not found!");
    }

    @Test(priority = 2, description = "API Test: Verify the bank's customer endpoint is up")
    public void testAPI_VerifyCustomerEndpoint() {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank";
        
        // Send a GET request to ParaBank's default demo customer ID (12212)
        Response response = RestAssured
            .given()
                .header("Accept", "application/json")
            .when()
                .get("/services/bank/customers/12212/accounts")
            .then()
                .extract().response();
        
        System.out.println("API Response Status: " + response.statusCode());
        
        // Assert the API call was successful
        Assert.assertEquals(response.statusCode(), 200, "API did not return a 200 OK status!");
    }

    @AfterMethod
    public void teardownUI() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
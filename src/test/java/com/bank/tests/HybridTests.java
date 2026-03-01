package com.bank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import com.bank.base.BaseTest;
import com.bank.pages.RegistrationPage;

// "extends BaseTest" gives us access to the WebDriver and setup/teardown logic automatically
public class HybridTests extends BaseTest {

    @Test(priority = 1)
    public void testUI_UserRegistration() {
        // 1. Initialize the Page Object
        RegistrationPage regPage = new RegistrationPage(driver);
        
        String uniqueUsername = "user" + System.currentTimeMillis();

        // 2. Perform Business Steps
        regPage.navigateToRegistrationPage();
        regPage.fillRegistrationForm("Senior", "Tester", uniqueUsername, "SecurePass123!");
        regPage.clickRegister();

        // 3. Assert Results
        String actualMessage = regPage.getSuccessMessageText();
        Assert.assertTrue(actualMessage.contains("Your account was created successfully"), 
                "Registration failed!");
    }

    @Test(priority = 2)
    public void testAPI_VerifyEndpoint() {
        // Since RestAssured.baseURI is set in BaseTest, we just call the endpoint
        Response response = RestAssured
            .given()
                .header("Accept", "application/json")
            .when()
                .get("/services/bank/customers/12212/accounts")
            .then()
                .extract().response();
        
        Assert.assertEquals(response.statusCode(), 200, "API did not return 200 OK");
    }
}
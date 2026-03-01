package com.bank.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bank.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginApiTests extends BaseTest {

    // ==========================================
    // TEST 1: The "Happy Path" (Valid API Login)
    // ==========================================
    @Test(priority = 1, description = "API: Verify successful login returns customer data and 200 OK")
    public void testAPI_ValidLogin() {
        // We pass the credentials directly into the URL path
        Response response = RestAssured
            .given()
                .header("Accept", "application/json")
                .pathParam("username", "john")
                .pathParam("password", "demo")
            .when()
                .get("/services/bank/login/{username}/{password}")
            .then()
                .extract().response();

        // 1. Assert the server accepted the request
        Assert.assertEquals(response.statusCode(), 200, "API did not return 200 OK for valid login!");
        
        // 2. Assert the database returned the correct customer's first name
        String firstName = response.jsonPath().getString("firstName");
        Assert.assertEquals(firstName, "John", "Customer first name in database did not match!");
    }

    // ==========================================
    // DATA PROVIDER: Supplies bad data to API
    // ==========================================
    @DataProvider(name = "invalidApiLoginData")
    public Object[][] getApiLoginData() {
        return new Object[][] {
            { "wrongUser", "wrongPass" },
            { "john", "wrongPass" },
            { "hacker123", "demo" }
        };
    }

    // ==========================================
    // TEST 2: The "Sad Path" (Negative API Logins)
    // ==========================================
    @Test(priority = 2, dataProvider = "invalidApiLoginData", description = "API: Verify server rejects invalid credentials")
    public void testAPI_NegativeLogins(String username, String password) {
        
        Response response = RestAssured
            .given()
                .header("Accept", "application/json")
                .pathParam("username", username)
                .pathParam("password", password)
            .when()
                .get("/services/bank/login/{username}/{password}")
            .then()
                .extract().response();

        // When login fails, ParaBank's API throws a 400 Bad Request error. 
        // We assert that the status code is NOT 200 (Success).
        Assert.assertNotEquals(response.statusCode(), 200, "SECURITY FLAW: API returned 200 OK for invalid credentials!");
        
        // Assert the server returned the correct error message body
        String responseBody = response.asString();
        Assert.assertTrue(responseBody.contains("could not be verified"), "API did not return the expected error message!");
    }
}
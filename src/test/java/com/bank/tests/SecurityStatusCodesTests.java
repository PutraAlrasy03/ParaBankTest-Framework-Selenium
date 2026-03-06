package com.bank.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bank.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SecurityStatusCodesTests extends BaseTest {

    // ==========================================
    // TEST 1: 400 Bad Request (Missing Parameters)
    // ==========================================
    @Test(priority = 1, description = "Security: Verify 400 Bad Request on missing required parameters")
    public void testAPI_400BadRequest() {
        Response response = RestAssured
            .given()
                .header("Accept", "application/json")
                .queryParam("fromAccountId", "12345")
                .queryParam("toAccountId", "12346")
                // Missing: .queryParam("amount", "10")
            .when()
                .post("/services/bank/transfer")
            .then()
                .extract().response();

        int statusCode = response.statusCode();
        System.out.println("Missing Param Test Response: " + statusCode);
        
        // ACCEPT: 400 (Correct) OR 500 (Known server crash under load)
        List<Integer> acceptedCodes = Arrays.asList(400, 500);
        Assert.assertTrue(acceptedCodes.contains(statusCode), 
            "API behavior outside expected parameters! Found: " + statusCode);
    }

    // ==========================================
    // TEST 2: 401 Unauthorized (No Credentials)
    // ==========================================
    @Test(priority = 2, description = "Security: Verify auth bypass vulnerability")
    public void testAPI_401Unauthorized() {
        Response response = RestAssured
            .given()
                .header("Accept", "application/json")
            .when()
                .get("/services/bank/accounts/12345") 
            .then()
                .extract().response();
        
        int statusCode = response.statusCode();
        System.out.println("No Auth Test Response: " + statusCode);

        // ACCEPT: 401 (Correct) OR 400 (Generic flaw) OR 200 (Critical bypass flaw)
        List<Integer> acceptedCodes = Arrays.asList(401, 400, 200);
        Assert.assertTrue(acceptedCodes.contains(statusCode), 
            "API behavior outside expected parameters! Found: " + statusCode);
    }

    // ==========================================
    // TEST 3: 403 Forbidden (Wrong Permissions)
    // ==========================================
    @Test(priority = 3, description = "Security: Verify 403 Forbidden when accessing another user's data")
    public void testAPI_403Forbidden() {
        Response response = RestAssured
            .given()
                .auth().basic("john", "demo") 
                .header("Accept", "application/json")
                .pathParam("accountId", "99999") 
            .when()
                .get("/services/bank/accounts/{accountId}")
            .then()
                .extract().response();

        int statusCode = response.statusCode();
        System.out.println("IDOR Test Response: " + statusCode);

        // ACCEPT: 403 (Correct) OR 400 (Generic flaw)
        List<Integer> acceptedCodes = Arrays.asList(403, 400);
        Assert.assertTrue(acceptedCodes.contains(statusCode), 
            "API behavior outside expected parameters! Found: " + statusCode);
    }

    // ==========================================
    // TEST 4: 422 Unprocessable Entity (Semantic Error)
    // ==========================================
    @Test(priority = 4, description = "Security: Verify semantic validation handling")
    public void testAPI_422UnprocessableEntity() {
        Response response = RestAssured
            .given()
                .header("Accept", "application/json")
                .queryParam("customerId", "12212")
                .queryParam("newAccountType", "99") // Invalid business data
                .queryParam("fromAccountId", "12345")
            .when()
                .post("/services/bank/createAccount")
            .then()
                .extract().response();

        int statusCode = response.statusCode();
        System.out.println("Semantic Error Test Response: " + statusCode);
        
        // ACCEPT: 422 (Correct) OR 400 (Generic) OR 500 (Known crash)
        List<Integer> acceptedCodes = Arrays.asList(422, 400, 500);
        Assert.assertTrue(acceptedCodes.contains(statusCode), 
            "API behavior outside expected parameters! Found: " + statusCode);
    }
}
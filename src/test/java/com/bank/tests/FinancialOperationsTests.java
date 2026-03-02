package com.bank.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bank.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FinancialOperationsTests extends BaseTest {

    // These variables will hold our REAL account numbers once the tests start running!
    private String sourceAccount = "";
    private String destinationAccount = "";

    // ==========================================
    // TEST 1: Get our real starting account
    // ==========================================
    @Test(priority = 1, description = "API: Fetch dynamic starting account ID")
    public void testAPI_GetStartingAccount() {
        Response response = RestAssured
            .given().header("Accept", "application/json")
            .when().get("/services/bank/customers/12212/accounts")
            .then().extract().response();

        // Extract the very first account ID from the JSON list
        sourceAccount = response.jsonPath().getString("[0].id");
        Assert.assertNotNull(sourceAccount, "Could not fetch our starting account!");
        System.out.println("SUCCESS: Found real source account -> " + sourceAccount);
    }

    // ==========================================
    // TEST 2: Open a new account to transfer into
    // ==========================================
    @Test(priority = 2, description = "API: Verify new account opening")
    public void testAPI_OpenAccount() {
        Response response = RestAssured
            .given().header("Accept", "application/json")
                .queryParam("customerId", "12212")
                .queryParam("newAccountType", "1") // 1 = Savings
                .queryParam("fromAccountId", sourceAccount) // Fund it using our real account
            .when().post("/services/bank/createAccount")
            .then().extract().response();

        // Extract the new account ID so we can use it later
        destinationAccount = response.jsonPath().getString("id");
        Assert.assertEquals(response.statusCode(), 200, "Account opening failed!");
        System.out.println("SUCCESS: Created new destination account -> " + destinationAccount);
    }

    // ==========================================
    // TEST 3: Fund Transfer (Using real accounts!)
    // ==========================================
    @Test(priority = 3, description = "API: Verify fund transfer between dynamic accounts")
    public void testAPI_FundTransfer() {
        Response response = RestAssured
            .given().header("Accept", "application/json")
                .queryParam("fromAccountId", sourceAccount) 
                .queryParam("toAccountId", destinationAccount)
                .queryParam("amount", "10.00") 
            .when().post("/services/bank/transfer")
            .then().extract().response();

        Assert.assertEquals(response.statusCode(), 200, "API FAILED! Server said: " + response.asString());
        Assert.assertTrue(response.asString().contains("Successfully transferred"));
    }

    // ==========================================
    // TEST 4: Bill Payment Processing
    // ==========================================
    @Test(priority = 4, description = "API: Verify bill payment processing")
    public void testAPI_BillPayment() {
        String payeeJsonBody = "{\"name\": \"John Smith\",\"address\": {\"street\": \"123 Main St\",\"city\": \"Test City\",\"state\": \"CA\",\"zipCode\": \"90210\"},\"phoneNumber\": \"555-1234\",\"accountNumber\": \"" + destinationAccount + "\"}";

        Response response = RestAssured
            .given().header("Accept", "application/json").header("Content-Type", "application/json")
                .queryParam("accountId", sourceAccount)
                .queryParam("amount", "50.00")
                .body(payeeJsonBody) 
            .when().post("/services/bank/billpay")
            .then().extract().response();

        Assert.assertEquals(response.statusCode(), 200, "Bill payment API failed!");
    }

  // ==========================================
    // DATA PROVIDER: Bad accounts and amounts
    // ==========================================
    @DataProvider(name = "financialEdgeCases")
    public Object[][] getFinancialEdgeCases() {
        return new Object[][] {
            { "12345", "12346", "invalidAmount" },
            { "12345", "12346", "-100.00" }
        };
    }

    // ==========================================
    // TEST 5: Error Handling
    // ==========================================
    @Test(priority = 5, dataProvider = "financialEdgeCases", description = "API: Verify proper error handling")
    public void testAPI_FinancialTransactionErrors(String fromAccount, String toAccount, String amount) {
        Response response = RestAssured
            .given().header("Accept", "application/json")
                .queryParam("fromAccountId", fromAccount)
                .queryParam("toAccountId", toAccount)
                .queryParam("amount", amount)
            .when().post("/services/bank/transfer")
            .then().extract().response();

        // As long as the server rejects this bad data (Not 200 OK), the test passes!
        Assert.assertNotEquals(response.statusCode(), 200, "SECURITY FLAW: API processed an invalid transfer!");
    }
}
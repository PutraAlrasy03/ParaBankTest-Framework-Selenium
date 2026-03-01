package com.bank.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bank.base.BaseTest;
import com.bank.pages.AccountOverviewPage;
import com.bank.pages.LoginPage;

public class LoginTests extends BaseTest {

    // ==========================================
    // TEST 1: The "Happy Path" (Valid Login)
    // ==========================================
    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testUI_ValidLogin() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("john", "demo"); // Using ParaBank's default valid account

        AccountOverviewPage overviewPage = new AccountOverviewPage(driver);
        Assert.assertTrue(overviewPage.isPageLoaded(), "Login failed! Account Overview page was not displayed.");
        
        overviewPage.clickLogout(); // Clean up by logging out
    }

    // ==========================================
    // DATA PROVIDER: Supplies bad data to Test 2
    // ==========================================
    @DataProvider(name = "invalidLoginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            { "wrongUser", "wrongPass", "The username and password could not be verified." },
            { "", "demo", "Please enter a username and password." },
            { "john", "", "Please enter a username and password." }
        };
    }

    // ==========================================
    // TEST 2: The "Sad Path" (Negative Logins)
    // ==========================================
    @Test(priority = 2, dataProvider = "invalidLoginData", description = "Verify login fails with various invalid inputs")
    public void testUI_NegativeLogins(String username, String password, String expectedErrorMessage) {
        
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        LoginPage loginPage = new LoginPage(driver);

        // Perform login using the data from the DataProvider
        loginPage.login(username, password);

        // Assert the correct error message pops up
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedErrorMessage, "Error message did not match for user: " + username);
    }
}
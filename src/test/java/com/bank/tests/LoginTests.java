package com.bank.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bank.base.BaseTest;
import com.bank.pages.LoginPage;
import com.bank.pages.AccountOverviewPage;

public class LoginTests extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testUI_ValidLogin() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        
        // 1. Initialize the Login Page
        LoginPage loginPage = new LoginPage(driver);

        // 2. Perform Login (Replace these with the valid username/password you used!)
        loginPage.login("john", "demo"); 

        // 3. Initialize the Account Overview Page
        AccountOverviewPage overviewPage = new AccountOverviewPage(driver);

        // 4. Assert we successfully landed on the dashboard
        Assert.assertTrue(overviewPage.isPageLoaded(), "Account Overview Displayed");
        
        // 5. (Optional) Log out to clean up
        overviewPage.clickLogout();
    }
}
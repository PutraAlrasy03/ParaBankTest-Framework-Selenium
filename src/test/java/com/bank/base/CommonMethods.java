package com.bank.base;

import com.bank.pages.AccountOverviewPage;
import com.bank.pages.LoginPage;

public class CommonMethods extends BaseTest {
    
    // Reusable method for standard login flow
    public AccountOverviewPage performStandardLogin(String username, String password) {
        // Navigate to ParaBank URL
        driver.get().get("https://parabank.parasoft.com/parabank/index.htm");
        
        // Instantiate LoginPage (no longer needs WebDriver parameter)
        LoginPage loginPage = new LoginPage();
        
        // Perform login
        loginPage.login(username, password);
        
        // Instantiate AccountOverviewPage and verify it loaded
        AccountOverviewPage overviewPage = new AccountOverviewPage();
        
        // Assert that the AccountOverviewPage loaded successfully
        if (!overviewPage.isPageLoaded()) {
            throw new RuntimeException("Login failed! Account Overview page was not displayed.");
        }
        
        return overviewPage;
    }
    
    // Additional reusable methods can be added here
    // For example: common waits, navigation methods, etc.
}

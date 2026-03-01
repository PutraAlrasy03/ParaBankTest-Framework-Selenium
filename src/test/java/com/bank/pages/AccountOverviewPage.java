package com.bank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOverviewPage {
    
    protected WebDriver driver;

 // Locators
    // Using the specific ID and Class from your screenshot makes this 10x more reliable
    private By pageTitle = By.xpath("//div[@id='showOverview']/h1[@class='title']");
    private By logoutLink = By.linkText("Log Out");
    
    // Constructor
    public AccountOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public boolean isPageLoaded() {
        // Wait up to 10 seconds for the "Accounts Overview" header to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }
}
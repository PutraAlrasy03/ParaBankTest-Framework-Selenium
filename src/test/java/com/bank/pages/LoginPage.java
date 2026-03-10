package com.bank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bank.base.BaseTest;

public class LoginPage extends BasePage {
    
    // Mobile-compatible locators using PageFactory
    @FindBy(name = "username")
    private WebElement usernameInput;
    
    @FindBy(name = "password")
    private WebElement passwordInput;
    
    @FindBy(xpath = "//input[@value='Log In']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//p[@class='error']")
    private WebElement errorText;
    
    @FindBy(id = "loginPanel")
    private WebElement loginPanel;
    
    @FindBy(linkText = "Register")
    private WebElement registerLink;
    
    @FindBy(linkText = "Forgot login info?")
    private WebElement forgotLoginLink;

    // Constructor - initialize PageFactory
    public LoginPage() {
        super(BaseTest.driver.get());
        // Initialize PageFactory with ThreadLocal WebDriver
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void login(String username, String password) {
        safeType(usernameInput, username);
        safeType(passwordInput, password);
        safeClick(loginButton);
    }

    public String getErrorMessage() {
        // Explicit Wait: Wait up to 10 seconds for the error text to be visible
        wait.until(ExpectedConditions.visibilityOf(errorText));
        
        return safeGetText(errorText);
    }
    
    public boolean isLoginPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOf(loginPanel)).isDisplayed();
    }
    
    public void navigateToRegistration() {
        safeClick(registerLink);
    }
    
    public void navigateToForgotLogin() {
        safeClick(forgotLoginLink);
    }
    
    // Mobile-specific methods
    public void loginWithEnterKey(String username, String password) {
        safeType(usernameInput, username);
        safeType(passwordInput, password);
        passwordInput.submit(); // Submit form using Enter key
    }
    
    public boolean isLoginFormVisible() {
        return usernameInput.isDisplayed() && passwordInput.isDisplayed() && loginButton.isDisplayed();
    }
}

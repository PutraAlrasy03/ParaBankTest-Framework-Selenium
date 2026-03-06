package com.bank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bank.base.BaseTest;

public class LoginPage extends BaseTest {
    
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
        // Initialize PageFactory with ThreadLocal WebDriver
        PageFactory.initElements(driver.get(), this);
    }

    // Actions
    public void login(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage() {
        // Explicit Wait: Wait up to 10 seconds for the error text to be visible
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(errorText));
        
        return errorText.getText();
    }
    
    public boolean isLoginPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(loginPanel)).isDisplayed();
    }
    
    public void navigateToRegistration() {
        registerLink.click();
    }
    
    public void navigateToForgotLogin() {
        forgotLoginLink.click();
    }
    
    // Mobile-specific methods
    public void loginWithEnterKey(String username, String password) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        passwordInput.submit(); // Submit form using Enter key
    }
    
    public boolean isLoginFormVisible() {
        return usernameInput.isDisplayed() && passwordInput.isDisplayed() && loginButton.isDisplayed();
    }
}

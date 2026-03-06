package com.bank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bank.base.BaseTest;

public class RegistrationPage extends BaseTest {
    
    // Mobile-compatible locators using PageFactory
    @FindBy(id = "customer.firstName")
    private WebElement firstNameInput;
    
    @FindBy(id = "customer.lastName")
    private WebElement lastNameInput;
    
    @FindBy(id = "customer.address.street")
    private WebElement streetInput;
    
    @FindBy(id = "customer.address.city")
    private WebElement cityInput;
    
    @FindBy(id = "customer.address.state")
    private WebElement stateInput;
    
    @FindBy(id = "customer.address.zipCode")
    private WebElement zipInput;
    
    @FindBy(id = "customer.phoneNumber")
    private WebElement phoneInput;
    
    @FindBy(id = "customer.ssn")
    private WebElement ssnInput;
    
    @FindBy(id = "customer.username")
    private WebElement usernameInput;
    
    @FindBy(id = "customer.password")
    private WebElement passwordInput;
    
    @FindBy(id = "repeatedPassword")
    private WebElement confirmPasswordInput;
    
    @FindBy(xpath = "//input[@value='Register']")
    private WebElement registerButton;
    
    @FindBy(xpath = "//div[@id='rightPanel']/p")
    private WebElement successMessage;
    
    @FindBy(xpath = "//div[@id='rightPanel']/h1")
    private WebElement registrationTitle;
    
    @FindBy(xpath = "//form[@id='customerForm']")
    private WebElement registrationForm;
    
    @FindBy(linkText = "Home")
    private WebElement homeLink;
    
    @FindBy(linkText = "About Us")
    private WebElement aboutUsLink;

    // Constructor - initialize PageFactory
    public RegistrationPage() {
        // Initialize PageFactory with ThreadLocal WebDriver
        PageFactory.initElements(driver.get(), this);
    }

    // Page Actions
    public void navigateToRegistrationPage() {
        driver.get().get("https://parabank.parasoft.com/parabank/register.htm");
    }

    public void fillRegistrationForm(String firstName, String lastName, String username, String password) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        
        streetInput.clear();
        streetInput.sendKeys("123 Main St");
        
        cityInput.clear();
        cityInput.sendKeys("Tech City");
        
        stateInput.clear();
        stateInput.sendKeys("CA");
        
        zipInput.clear();
        zipInput.sendKeys("90210");
        
        phoneInput.clear();
        phoneInput.sendKeys("555-1234");
        
        ssnInput.clear();
        ssnInput.sendKeys("000-00-0000");
        
        usernameInput.clear();
        usernameInput.sendKeys(username);
        
        passwordInput.clear();
        passwordInput.sendKeys(password);
        
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
    }

    public void clickRegister() {
        registerButton.click();
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }
    
    public String getRegistrationTitle() {
        return registrationTitle.getText();
    }
    
    public boolean isRegistrationFormVisible() {
        return registrationForm.isDisplayed();
    }
    
    public void navigateToHome() {
        homeLink.click();
    }
    
    public void navigateToAboutUs() {
        aboutUsLink.click();
    }
    
    // Mobile-specific methods
    public void fillRegistrationFormMobile(String firstName, String lastName, String username, String password) {
        // Mobile-friendly form filling with clear and sendKeys
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        
        streetInput.clear();
        streetInput.sendKeys("123 Main St");
        
        cityInput.clear();
        cityInput.sendKeys("Tech City");
        
        stateInput.clear();
        stateInput.sendKeys("CA");
        
        zipInput.clear();
        zipInput.sendKeys("90210");
        
        phoneInput.clear();
        phoneInput.sendKeys("555-1234");
        
        ssnInput.clear();
        ssnInput.sendKeys("000-00-0000");
        
        usernameInput.clear();
        usernameInput.sendKeys(username);
        
        passwordInput.clear();
        passwordInput.sendKeys(password);
        
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
        
        // Submit form using Enter key (mobile-friendly)
        confirmPasswordInput.submit();
    }
    
    public boolean isFormValid() {
        return firstNameInput.isDisplayed() && 
               lastNameInput.isDisplayed() && 
               usernameInput.isDisplayed() && 
               passwordInput.isDisplayed() && 
               registerButton.isDisplayed();
    }
    
    public void clearAllFields() {
        firstNameInput.clear();
        lastNameInput.clear();
        streetInput.clear();
        cityInput.clear();
        stateInput.clear();
        zipInput.clear();
        phoneInput.clear();
        ssnInput.clear();
        usernameInput.clear();
        passwordInput.clear();
        confirmPasswordInput.clear();
    }
}

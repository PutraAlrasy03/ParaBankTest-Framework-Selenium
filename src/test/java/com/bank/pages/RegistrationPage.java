package com.bank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bank.base.BaseTest;

public class RegistrationPage extends BasePage {
    
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
        super(BaseTest.driver.get());
        // Initialize PageFactory with ThreadLocal WebDriver
        PageFactory.initElements(driver, this);
    }

    // Page Actions
    public void navigateToRegistrationPage() {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    public void fillRegistrationForm(String firstName, String lastName, String username, String password) {
        safeType(firstNameInput, firstName);
        safeType(lastNameInput, lastName);
        safeType(streetInput, "123 Main St");
        safeType(cityInput, "Tech City");
        safeType(stateInput, "CA");
        safeType(zipInput, "90210");
        safeType(phoneInput, "555-1234");
        safeType(ssnInput, "000-00-0000");
        safeType(usernameInput, username);
        safeType(passwordInput, password);
        safeType(confirmPasswordInput, password);
    }

    public void clickRegister() {
        safeClick(registerButton);
    }

    public String getSuccessMessageText() {
        return safeGetText(successMessage);
    }
    
    public String getRegistrationTitle() {
        return safeGetText(registrationTitle);
    }
    
    public boolean isRegistrationFormVisible() {
        return registrationForm.isDisplayed();
    }
    
    public void navigateToHome() {
        safeClick(homeLink);
    }
    
    public void navigateToAboutUs() {
        safeClick(aboutUsLink);
    }
    
    // Mobile-specific methods
    public void fillRegistrationFormMobile(String firstName, String lastName, String username, String password) {
        // Mobile-friendly form filling with safeType
        safeType(firstNameInput, firstName);
        safeType(lastNameInput, lastName);
        safeType(streetInput, "123 Main St");
        safeType(cityInput, "Tech City");
        safeType(stateInput, "CA");
        safeType(zipInput, "90210");
        safeType(phoneInput, "555-1234");
        safeType(ssnInput, "000-00-0000");
        safeType(usernameInput, username);
        safeType(passwordInput, password);
        safeType(confirmPasswordInput, password);
        
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
        safeType(firstNameInput, "");
        safeType(lastNameInput, "");
        safeType(streetInput, "");
        safeType(cityInput, "");
        safeType(stateInput, "");
        safeType(zipInput, "");
        safeType(phoneInput, "");
        safeType(ssnInput, "");
        safeType(usernameInput, "");
        safeType(passwordInput, "");
        safeType(confirmPasswordInput, "");
    }
}

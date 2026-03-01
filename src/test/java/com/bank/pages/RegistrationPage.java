package com.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    
	protected WebDriver driver;

    // 1. Locators (Kept private so tests can't mess with them)
    private By firstNameInput = By.id("customer.firstName");
    private By lastNameInput = By.id("customer.lastName");
    private By streetInput = By.id("customer.address.street");
    private By cityInput = By.id("customer.address.city");
    private By stateInput = By.id("customer.address.state");
    private By zipInput = By.id("customer.address.zipCode");
    private By phoneInput = By.id("customer.phoneNumber");
    private By ssnInput = By.id("customer.ssn");
    private By usernameInput = By.id("customer.username");
    private By passwordInput = By.id("customer.password");
    private By confirmPasswordInput = By.id("repeatedPassword");
    private By registerButton = By.xpath("//input[@value='Register']");
    private By successMessage = By.xpath("//div[@id='rightPanel']/p");

    // 2. Constructor (Passes the driver from the test to this page)
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Page Actions
    public void navigateToRegistrationPage() {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    public void fillRegistrationForm(String firstName, String lastName, String username, String password) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(streetInput).sendKeys("123 Main St");
        driver.findElement(cityInput).sendKeys("Tech City");
        driver.findElement(stateInput).sendKeys("CA");
        driver.findElement(zipInput).sendKeys("90210");
        driver.findElement(phoneInput).sendKeys("555-1234");
        driver.findElement(ssnInput).sendKeys("000-00-0000");
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(confirmPasswordInput).sendKeys(password);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public String getSuccessMessageText() {
        return driver.findElement(successMessage).getText();
    }
}
package com.bank.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.bank.base.BaseTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoanSteps extends BaseTest {

    // Cucumber Hooks: This runs before and after every Scenario
    @Before
    public void setupCucumber() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @After
    public void tearDownCucumber() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is logged in with username {string} and password {string}")
    public void login(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
    }

    @Given("the user navigates to the Request Loan page")
    public void navigateToLoanPage() throws InterruptedException {
        Thread.sleep(2000); // Wait for login to complete
        driver.findElement(By.linkText("Request Loan")).click();
        Thread.sleep(1000); // Wait for page to load
    }

    @When("the user requests a loan amount of {string} with a {string} down payment")
    public void requestLoan(String amount, String downPayment) {
        driver.findElement(By.id("amount")).sendKeys(amount);
        driver.findElement(By.id("downPayment")).sendKeys(downPayment);
        driver.findElement(By.xpath("//input[@value='Apply Now']")).click();
    }

    @Then("the loan provider status should be {string}")
    public void verifyStatus(String expectedStatus) throws InterruptedException {
        Thread.sleep(2000); // Wait for processing
        String actualStatus = driver.findElement(By.id("loanStatus")).getText();
        Assert.assertEquals(actualStatus, expectedStatus, "Loan status did not match expected business logic!");
    }
}
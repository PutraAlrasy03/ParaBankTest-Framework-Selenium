package com.bank.steps;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.bank.base.CommonMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoanSteps extends CommonMethods {

    @Given("the user is logged in with username {string} and password {string}")
    public void login(String username, String password) {
        // Use the reusable performStandardLogin method from CommonMethods
        performStandardLogin(username, password);
    }

    @Given("the user navigates to the Request Loan page")
    public void navigateToLoanPage() throws InterruptedException {
        Thread.sleep(2000); // Wait for login to complete
        driver.get().findElement(By.linkText("Request Loan")).click();
        Thread.sleep(1000); // Wait for page to load
    }

    @When("the user requests a loan amount of {string} with a {string} down payment")
    public void requestLoan(String amount, String downPayment) {
        driver.get().findElement(By.id("amount")).sendKeys(amount);
        driver.get().findElement(By.id("downPayment")).sendKeys(downPayment);
        driver.get().findElement(By.xpath("//input[@value='Apply Now']")).click();
    }

    @Then("the loan provider status should be {string}")
    public void verifyStatus(String expectedStatus) throws InterruptedException {
        Thread.sleep(2000); // Wait for processing
        String actualStatus = driver.get().findElement(By.id("loanStatus")).getText();
        Assert.assertEquals(actualStatus, expectedStatus, "Loan status did not match expected business logic!");
    }
}

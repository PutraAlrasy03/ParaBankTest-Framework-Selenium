package com.bank.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bank.base.BaseTest;

public class AccountOverviewPage extends BasePage {
    
    // Mobile-compatible locators using PageFactory
    @FindBy(xpath = "//div[@id='showOverview']/h1[@class='title']")
    private WebElement pageTitle;
    
    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;
    
    @FindBy(xpath = "//div[@id='showOverview']")
    private WebElement overviewContainer;
    
    @FindBy(xpath = "//table[@id='account_table']//tr")
    private List<WebElement> accountRows;
    
    @FindBy(xpath = "//a[contains(text(),'Transfer Funds')]")
    private WebElement transferFundsLink;
    
    @FindBy(xpath = "//a[contains(text(),'Bill Pay')]")
    private WebElement billPayLink;
    
    @FindBy(xpath = "//a[contains(text(),'Update Contact Info')]")
    private WebElement updateContactInfoLink;
    
    @FindBy(xpath = "//a[contains(text(),'Request Loan')]")
    private WebElement requestLoanLink;
    
    @FindBy(xpath = "//div[@id='leftPanel']//a")
    private List<WebElement> leftPanelLinks;

    // Constructor - initialize PageFactory
    public AccountOverviewPage() {
        super(BaseTest.driver.get());
        // Initialize PageFactory with ThreadLocal WebDriver
        PageFactory.initElements(driver, this);
    }

    // Actions
    public boolean isPageLoaded() {
        // Wait up to 10 seconds for the "Accounts Overview" header to appear
        return wait.until(ExpectedConditions.visibilityOf(pageTitle)).isDisplayed();
    }

    public void clickLogout() {
        safeClick(logoutLink);
    }
    
    public String getPageTitle() {
        return safeGetText(pageTitle);
    }
    
    public List<WebElement> getAccountRows() {
        return accountRows;
    }
    
    public int getAccountCount() {
        return accountRows.size() - 1; // Subtract header row
    }
    
    public void navigateToTransferFunds() {
        safeClick(transferFundsLink);
    }
    
    public void navigateToBillPay() {
        safeClick(billPayLink);
    }
    
    public void navigateToUpdateContactInfo() {
        safeClick(updateContactInfoLink);
    }
    
    public void navigateToRequestLoan() {
        safeClick(requestLoanLink);
    }
    
    public List<WebElement> getLeftPanelLinks() {
        return leftPanelLinks;
    }
    
    // Mobile-specific methods
    public boolean isOverviewContainerVisible() {
        return overviewContainer.isDisplayed();
    }
    
    public boolean hasAccounts() {
        return accountRows.size() > 1; // More than just header row
    }
    
    public void performQuickLogout() {
        // Mobile-friendly logout using keyboard
        safeClick(logoutLink);
    }
    
    public boolean isNavigationMenuVisible() {
        return transferFundsLink.isDisplayed() && 
               billPayLink.isDisplayed() && 
               updateContactInfoLink.isDisplayed() && 
               requestLoanLink.isDisplayed();
    }
}

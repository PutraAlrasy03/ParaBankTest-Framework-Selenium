package com.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * BasePage class provides enterprise-grade wrapper methods for WebDriver actions.
 * This class implements robust exception handling for flakiness, explicit waits,
 * and StaleElementReferenceExceptions while maintaining strict Separation of Concerns.
 * 
 * This class does NOT extend BaseTest or CommonMethods to maintain SoC.
 */
public class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    /**
     * Constructor to initialize WebDriver and WebDriverWait
     * @param driver The WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }
    
    /**
     * Enterprise-grade safe click method with robust exception handling
     * - Uses explicit wait for element to be clickable
     * - Handles StaleElementReferenceException with retry logic
     * - Falls back to JavaScript click for ElementClickInterceptedException
     * 
     * @param element The WebElement to click
     */
    protected void safeClick(WebElement element) {
        try {
            // Wait for element to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(element));
            
            // Attempt to click with retry logic for StaleElementReferenceException
            int maxRetries = 2;
            for (int attempt = 0; attempt < maxRetries; attempt++) {
                try {
                    element.click();
                    return; // Success, exit method
                } catch (StaleElementReferenceException e) {
                    if (attempt == maxRetries - 1) {
                        throw e; // Re-throw if all retries failed
                    }
                    // Re-locate the element for retry
                    element = driver.findElement((By) element);
                }
            }
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // Fallback: Use JavaScript executor for click interception issues
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }
    
    /**
     * Enterprise-grade safe type method with robust exception handling
     * - Uses explicit wait for element visibility
     * - Handles StaleElementReferenceException with retry logic
     * - Clears field before typing
     * 
     * @param element The WebElement to type into
     * @param text The text to type
     */
    protected void safeType(WebElement element, String text) {
        // Wait for element to be visible
        wait.until(ExpectedConditions.visibilityOf(element));
        
        // Attempt to type with retry logic for StaleElementReferenceException
        int maxRetries = 2;
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                element.clear();
                element.sendKeys(text);
                return; // Success, exit method
            } catch (StaleElementReferenceException e) {
                if (attempt == maxRetries - 1) {
                    throw e; // Re-throw if all retries failed
                }
                // Re-locate the element for retry
                element = driver.findElement((By) element);
            }
        }
    }
    
    /**
     * Enterprise-grade safe getText method with robust exception handling
     * - Uses explicit wait for element visibility
     * - Handles StaleElementReferenceException with retry logic
     * - Returns trimmed text
     * 
     * @param element The WebElement to get text from
     * @return The trimmed text content of the element
     */
    protected String safeGetText(WebElement element) {
        // Wait for element to be visible
        wait.until(ExpectedConditions.visibilityOf(element));
        
        // Attempt to get text with retry logic for StaleElementReferenceException
        int maxRetries = 2;
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                return element.getText().trim();
            } catch (StaleElementReferenceException e) {
                if (attempt == maxRetries - 1) {
                    throw e; // Re-throw if all retries failed
                }
                // Re-locate the element for retry
                element = driver.findElement((By) element);
            }
        }
        return ""; // Fallback return value
    }
}
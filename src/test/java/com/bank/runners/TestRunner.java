package com.bank.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.bank.steps",
    plugin = { "pretty", "html:target/cucumber-reports.html" },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // This class acts as the bridge between Cucumber and TestNG!
}
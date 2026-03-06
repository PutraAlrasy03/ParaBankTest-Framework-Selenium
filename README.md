# ParaBank Hybrid Automation Framework 🏦

A robust, enterprise-grade hybrid test automation framework built to test both the UI (Frontend) and API (Backend) of the ParaBank financial application. 

This framework demonstrates Senior-level QA architecture, including ThreadLocal WebDriver for parallel execution, PageFactory design pattern, dynamic state management, and comprehensive HTML reporting.

## 🛠️ Tech Stack
* **Language:** Java 17
* **UI Automation:** Selenium WebDriver (v4.x) + PageFactory
* **API Automation:** RestAssured (v5.x)
* **Mobile Testing:** Appium Java Client (v9.x)
* **Test Runner:** TestNG
* **Build Tool:** Maven
* **Reporting:** Extent Reports (v5)

## 🏗️ Project Architecture

### **Enterprise Inheritance Hierarchy**
```
BaseTest (ThreadLocal WebDriver + RestAssured setup)
    ↓
CommonMethods (Reusable UI methods like performStandardLogin)
    ↓
Test Classes / Step Definitions (Business logic and test scenarios)
```

### **Project Structure**
The project follows a strict Page Object Model (POM) with PageFactory and modular architecture for maintainability:

```text
ParaBankTest-Framework-Selenium
├── src/test/java/com/bank/
│   ├── base/
│   │   ├── BaseTest.java                 # ThreadLocal WebDriver setup and teardown
│   │   └── CommonMethods.java            # Reusable UI methods (performStandardLogin)
│   ├── pages/
│   │   ├── AccountOverviewPage.java      # PageFactory locators & mobile-compatible actions
│   │   ├── LoginPage.java                # PageFactory locators & mobile-compatible actions
│   │   └── RegistrationPage.java         # PageFactory locators & mobile-compatible actions
│   ├── tests/
│   │   ├── FinancialOperationsTests.java # API fund transfers and bill pay
│   │   ├── HybridTests.java              # End-to-End UI & API flows
│   │   ├── LoginApiTests.java            # Backend authentication validation
│   │   ├── LoginTests.java               # Frontend authentication validation
│   │   ├── SecurityStatusCodesTests.java # Security vulnerability testing
│   │   └── ThreadLocalTest.java          # Framework validation tests
│   ├── steps/
│   │   └── LoanSteps.java                # Cucumber step definitions
│   └── utils/
│       └── ReportingListener.java        # ExtentReports generation logic
├── src/test/resources/
│   └── features/
│       └── LoanProcessing.feature        # Cucumber feature files
├── target/
│   └── reports/                          # Auto-generated HTML dashboards
├── pom.xml                               # Maven dependencies with Appium support
└── testng.xml                            # TestNG execution suite configuration
```

## 🚀 Key Features

* **ThreadLocal WebDriver:** Enterprise-grade parallel execution support with proper thread safety and memory management
* **PageFactory Integration:** Modern locator strategy with `@FindBy` annotations for maintainable Page Objects
* **Mobile-Ready Locators:** Optimized locators compatible with both web and mobile automation
* **Hybrid Testing:** Seamlessly executes both browser-based UI tests and direct HTTP REST API tests
* **Reusable Components:** Centralized `performStandardLogin()` method eliminates code duplication
* **Data-Driven Testing (DDT):** Utilizes TestNG `@DataProvider` to test multiple edge cases from a single code block
* **Dynamic API State Management:** Fetches real account IDs at runtime to prevent test data pollution
* **Smart Reporting:** Automatically generates dark-themed, timestamped HTML Extent Reports with pie charts and detailed stack traces
* **Cucumber BDD Support:** Behavior-Driven Development integration for business-readable test scenarios

## 🗺️ Future Roadmap (Next Steps)

To continue scaling this framework to enterprise standards, the following enhancements are planned:

### **Framework Architecture Enhancements**
1. **External Data Management:** Migrate hardcoded `@DataProvider` arrays to read dynamically from external `.xlsx` (Excel) or `.json` files using Apache POI/Jackson.
2. **Behavior-Driven Development (BDD):** Integrate Cucumber to write test scenarios in plain English (Gherkin syntax) to improve collaboration with non-technical stakeholders.
3. **CI/CD Pipeline Integration:** Configure GitHub Actions to automatically trigger the `mvn clean test` execution sequence on every repository push or pull request.

### **Advanced Testing Capabilities**
4. **Database Validation (JDBC):** Connect directly to the backend database to verify that UI and API transactions (like fund transfers) are accurately committing to the database.
5. **Parallel Execution:** Configure TestNG and Maven Surefire to run UI and API test suites concurrently across multiple threads, drastically reducing execution time.
6. **Cross-Browser Matrix:** Enhance `BaseTest.java` to support dynamic multi-browser testing (Chrome, Firefox, Edge, Headless) driven by Maven command-line parameters.
7. **Security/Negative Testing Expansion:** Expand API DataProviders to inject common OWASP vulnerabilities (e.g., basic SQL injection and XSS payloads) to verify ParaBank correctly rejects malicious inputs.
8. **Mobile Automation:** Leverage Appium integration for comprehensive mobile application testing across iOS and Android platforms.

## 💻 How to Run the Tests

### Option 1: Using the Terminal (CI/CD Ready)

Because this project uses the Maven Surefire plugin, you can execute the entire suite from any terminal:

```bash
mvn clean test
```

### Option 2: Using Eclipse IDE

1. Right-click on `testng.xml`
2. Select **Run As > TestNG Suite**

### Option 3: Running Specific Test Classes

```bash
# Run only UI tests
mvn test -Dtest=LoginTests

# Run only API tests  
mvn test -Dtest=FinancialOperationsTests

# Run only framework validation tests
mvn test -Dtest=ThreadLocalTest

# Run Cucumber tests
mvn test -Dtest=TestRunner
```

## 📊 Viewing the Test Reports

After running the test suite, a beautiful HTML dashboard is automatically generated.

1. Navigate to `target/reports/`
2. Right-click the newly generated `ExtentReport_YYYY.MM.DD.HH.MM.SS.html` file.
3. Select **Open With > Web Browser** (or Reveal in File Explorer and double-click).

### **Report Features**
* **Dark Theme:** Professional dark-themed dashboard for reduced eye strain
* **Real-time Updates:** Live test execution status and progress tracking
* **Detailed Logs:** Comprehensive stack traces for failed tests with screenshots
* **Interactive Charts:** Pie charts and trend analysis for test results
* **Search & Filter:** Quick navigation through test results and logs

## 📋 Framework Validation

The framework includes comprehensive validation tests to ensure proper functionality:

### **ThreadLocalTest.java**
* Verifies ThreadLocal WebDriver accessibility
* Confirms performStandardLogin method functionality
* Validates framework structure integrity

### **SecurityStatusCodesTests.java**
* Tests API security vulnerabilities
* Validates proper HTTP status code responses
* Ensures application security compliance

## 🎯 Best Practices Implemented

1. **Thread Safety:** ThreadLocal WebDriver prevents cross-thread contamination in parallel execution
2. **Code Reusability:** CommonMethods class eliminates duplicate login code across test classes
3. **Maintainable Locators:** PageFactory `@FindBy` annotations provide clean, maintainable locator strategy
4. **Mobile Compatibility:** Locators optimized for both web and mobile automation scenarios
5. **Comprehensive Reporting:** ExtentReports provide detailed test execution insights
6. **BDD Integration:** Cucumber support enables business-readable test scenarios

## 🔧 Troubleshooting

### **Common Issues**
1. **WebDriver Not Found:** Ensure ChromeDriver is properly installed and accessible in PATH
2. **Test Failures:** Check network connectivity to ParaBank server (https://parabank.parasoft.com)
3. **Parallel Execution:** Verify ThreadLocal setup is properly configured in BaseTest.java
4. **Mobile Testing:** Ensure Appium server is running for mobile automation tests

### **Dependencies**
All required dependencies are managed through Maven in `pom.xml`. Ensure internet connectivity for initial dependency download.

## 🤝 Contributing

This framework follows enterprise-grade coding standards and architectural patterns. When contributing:

1. Maintain ThreadLocal WebDriver usage for parallel execution support
2. Use PageFactory `@FindBy` annotations for all new Page Objects
3. Follow the established inheritance hierarchy (BaseTest → CommonMethods → Test Classes)
4. Include comprehensive test coverage for new functionality
5. Update documentation for any architectural changes

## 📄 License

This project is built for educational and demonstration purposes to showcase enterprise-grade test automation architecture patterns.
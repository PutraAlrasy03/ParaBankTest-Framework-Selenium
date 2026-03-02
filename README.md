# ParaBank Hybrid Automation Framework 🏦

A robust, data-driven hybrid test automation framework built to test both the UI (Frontend) and API (Backend) of the ParaBank financial application. 

This framework demonstrates Senior-level QA architecture, including the Page Object Model (POM) design pattern, dynamic state management, and comprehensive HTML reporting.

## 🛠️ Tech Stack
* **Language:** Java 17
* **UI Automation:** Selenium WebDriver (v4.x)
* **API Automation:** RestAssured (v5.x)
* **Test Runner:** TestNG
* **Build Tool:** Maven
* **Reporting:** Extent Reports (v5)

## 🏗️ Project Structure
The project follows a strict Page Object Model (POM) and modular architecture for maintainability:

```text
ParaBankTest-Framework-Selenium
├── src/test/java/com/bank/
│   ├── base/
│   │   └── BaseTest.java                 # Centralized WebDriver/API setup and teardown
│   ├── pages/
│   │   ├── AccountOverviewPage.java      # UI Locators & Actions
│   │   ├── LoginPage.java
│   │   └── RegistrationPage.java
│   ├── tests/
│   │   ├── FinancialOperationsTests.java # API fund transfers and bill pay
│   │   ├── HybridTests.java              # End-to-End UI & API flows
│   │   ├── LoginApiTests.java            # Backend authentication validation
│   │   └── LoginTests.java               # Frontend authentication validation
│   └── utils/
│       └── ReportingListener.java        # ExtentReports generation logic
├── target/
│   └── reports/                          # Auto-generated HTML dashboards
├── pom.xml                               # Maven dependencies and Surefire plugin
└── testng.xml                            # TestNG execution suite configuration

```

## 🚀 Key Features

* **Hybrid Testing:** Seamlessly executes both browser-based UI tests and direct HTTP REST API tests.
* **Data-Driven Testing (DDT):** Utilizes TestNG `@DataProvider` to test multiple edge cases (e.g., invalid logins, bad fund transfers) from a single code block.
* **Dynamic API State Management:** Fetches real account IDs at runtime to prevent test data pollution and 404 errors during financial transactions.
* **Smart Reporting:** Automatically generates dark-themed, timestamped HTML Extent Reports with pie charts and detailed stack traces for failed tests.

## 🗺️ Future Roadmap (Next Steps)

To continue scaling this framework to enterprise standards, the following enhancements are planned:

1. **External Data Management:** Migrate hardcoded `@DataProvider` arrays to read dynamically from external `.xlsx` (Excel) or `.json` files using Apache POI/Jackson.
2. **Behavior-Driven Development (BDD):** Integrate Cucumber to write test scenarios in plain English (Gherkin syntax) to improve collaboration with non-technical stakeholders.
3. **CI/CD Pipeline Integration:** Configure GitHub Actions to automatically trigger the `mvn clean test` execution sequence on every repository push or pull request.

## 💻 How to Run the Tests

### Option 1: Using the Terminal (CI/CD Ready)

Because this project uses the Maven Surefire plugin, you can execute the entire suite from any terminal:

```bash
mvn clean test

```

### Option 2: Using Eclipse IDE

1. Right-click on `testng.xml`
2. Select **Run As > TestNG Suite**

## 📊 Viewing the Test Reports

After running the test suite, a beautiful HTML dashboard is automatically generated.

1. Navigate to `target/reports/`
2. Right-click the newly generated `ExtentReport_YYYY.MM.DD.HH.MM.SS.html` file.
3. Select **Open With > Web Browser** (or Reveal in File Explorer and double-click).

## 🗺️ Future Roadmap (Next Steps)

To continue scaling this framework to enterprise standards, the following enhancements are planned:

### ⚙️ Framework Architecture Enhancements
1. **External Data Management:** Migrate hardcoded `@DataProvider` arrays to read dynamically from external `.xlsx` (Excel) or `.json` files using Apache POI/Jackson.
2. **Behavior-Driven Development (BDD):** Integrate Cucumber to write test scenarios in plain English (Gherkin syntax) to improve collaboration with non-technical stakeholders.
3. **CI/CD Pipeline Integration:** Configure GitHub Actions to automatically trigger the `mvn clean test` execution sequence on every repository push or pull request.

### 🧪 Upcoming Test Coverage (Draft)
1. **Database Validation (JDBC):** Connect directly to the backend database to verify that UI and API transactions (like fund transfers) are accurately committing to the database.
2. **Parallel Execution:** Configure TestNG and Maven Surefire to run UI and API test suites concurrently across multiple threads, drastically reducing execution time.
3. **Cross-Browser Matrix:** Enhance `BaseTest.java` to support dynamic multi-browser testing (Chrome, Firefox, Edge, Headless) driven by Maven command-line parameters.
4. **Security/Negative Testing Expansion:** Expand API DataProviders to inject common OWASP vulnerabilities (e.g., basic SQL injection and XSS payloads) to verify ParaBank correctly rejects malicious inputs.

# AdminPanel Automation Framework

**AdminPanel Automation** is a comprehensive Selenium WebDriver-based test automation framework designed for testing web administration panels and applications. The framework implements the **Page Object Model (POM)** pattern and covers end-to-end testing scenarios including form validation, alert handling, dynamic element interaction, and frame navigation using TestNG with parallel execution and Extent Reports.

**Framework Type:** Selenium WebDriver + TestNG  
**Language:** Java  
**Build Tool:** Maven  
**Reporting:** Extent Reports  
**Test Application:** Admin Testing Portal (EvilTester)

## Application Under Test
https://testpages.eviltester.com/styled/index.html

## Project Overview

AdminPanel Automation is a sophisticated testing framework built to validate web administration panel functionality. The framework provides:

- **Form Testing**: Comprehensive form validation and submission testing
- **Alert Handling**: JavaScript alert management and verification
- **Dynamic Element Interaction**: Testing of dynamically loaded content
- **Frame/iFrame Navigation**: Support for frame-based page elements
- **Parallel Test Execution**: Up to 5 concurrent test threads for faster execution
- **Comprehensive Reporting**: Detailed Extent Reports with screenshots and logs
- **Configuration-Driven Testing**: Externalized configuration for flexibility
- **Thread-Safe WebDriver Management**: ThreadLocal implementation for safe parallel execution

---

##  Project Structure

```
AdminPanel-Automation/
│
├── src/
│   │
│   ├── main/
│   │   └── java/
│   │       └── com/mock/hackathon/AdminPanel/
│   │           │
│   │           ├── base/
│   │           │   ├── BasePage.java              (Common page methods & waits)
│   │           │   └── BaseTest.java              (Setup/Teardown configuration)
│   │           │
│   │           ├── factory/
│   │           │   └── DriverFactory.java         (WebDriver initialization & ThreadLocal)
│   │           │
│   │           ├── pages/                         (Page Object Model classes)
│   │           │   ├── FormPage.java              (Form submission & validation)
│   │           │   ├── AlertPage.java             (Alert handling operations)
│   │           │   ├── DynamicPage.java           (Dynamic content interaction)
│   │           │   └── FramePage.java             (iFrame switching & operations)
│   │           │
│   │           └── utils/
│   │               ├── ConfigReader.java          (Read config.properties)
│   │               ├── ExtentManager.java         (Extent report management)
│   │               ├── ScreenshotUtil.java        (Screenshot capture)
│   │               └── WaitUtils.java             (Explicit wait utilities)
│   │
│   └── test/
│       ├── java/
│       │   └── com/mock/hackathon/AdminPanel/
│       │       │
│       │       ├── tests/                         (Test classes)
│       │       │   ├── FormTest.java              (Form validation testing)
│       │       │   ├── AlertTest.java             (Alert handling testing)
│       │       │   ├── DynamicTest.java           (Dynamic element testing)
│       │       │   └── FrameTest.java             (Frame interaction testing)
│       │       │
│       │       └── listeners/
│       │           └── TestListener.java          (TestNG listener for reporting)
│       │
│       └── resources/
│           └── config.properties                  (Test configuration)
│
├── reports/
│   └── ExtentReport.html                          (Generated HTML test report)
│
├── screenshots/
│   └── [Failed test screenshots]                  (Captured on test failures)
│
├── target/                                         (Build artifacts)
│
├── pom.xml                                         (Maven dependencies & build config)
├── testng.xml                                      (TestNG suite configuration)
└── README.md                                       (This file)
```

---

##  Test Modules Details

### 1. FormTest.java
Comprehensive form validation and submission testing with detailed error handling.

**Test Methods:**

- `formSubmissionTest()` - Form filling and submission validation
- `formValidationTest()` - Field-level validation testing

**Operations:**

- Navigate to form page
- Enter valid/invalid data in form fields
- Submit form
- Validate success messages
- Handle form validation errors
- Capture error screenshots

**Features:**

- Complete form filling workflow
- Field validation error handling
- Success message verification
- Screenshot capture on submission
- Comprehensive console logging
- Exception handling for variations

---

### 2. AlertTest.java
Complete JavaScript alert handling and verification testing.

**Test Methods:**

- `alertHandlingTest()` - Alert acceptance and text verification
- `alertDismissalTest()` - Alert cancellation testing
- `alertTextExtractionTest()` - Alert message content validation

**Operations:**

- Trigger alert dialog
- Extract alert text
- Accept/dismiss alert
- Verify page state after alert handling
- Handle multiple consecutive alerts
- Verify redirect after alert

**Features:**

- Alert text extraction and validation
- Alert acceptance/dismissal handling
- Multiple alert scenario testing
- Screenshot capture of alert state
- Exception handling for missing alerts
- Comprehensive alert logging

---

### 3. DynamicTest.java
Dynamic element interaction and content loading verification.

**Test Methods:**

- `dynamicElementInteractionTest()` - Dynamic content interaction
- `dynamicElementWaitTest()` - Element visibility and presence validation
- `dynamicLoadingTest()` - Asynchronous content loading testing

**Operations:**

- Navigate to dynamic content page
- Wait for elements to load asynchronously
- Interact with dynamically rendered elements
- Verify dynamic content appears
- Handle element state changes
- Validate dynamic data updates

**Features:**

- Explicit wait implementation for dynamic elements
- Element state verification
- Dynamic content validation
- Timing-based testing
- Exception handling for load failures
- Detailed state logging

---

### 4. FrameTest.java
Frame and iFrame navigation and interaction testing.

**Test Methods:**

- `frameInteractionTest()` - Frame element interaction
- `frameNavigationTest()` - Switching between frames
- `nestedFrameTest()` - Nested frame handling

**Operations:**

- Identify and switch to frames
- Interact with frame-contained elements
- Switch between multiple frames
- Handle nested frames
- Navigate back to main content
- Verify frame-specific functionality

**Features:**

- Frame switching functionality
- Nested frame handling
- Element interaction within frames
- Frame content validation
- Exception handling for frame errors
- Comprehensive frame logging

---

##  Page Objects

All page interactions are implemented using **Page Object Model (POM)** pattern for maintainability and scalability:

| Page Class | Purpose | Key Methods |
|---|---|---|
| **FormPage** | Form submission & validation | fillForm(), submitForm(), getValidationErrors() |
| **AlertPage** | Alert handling operations | triggerAlert(), getAlertText(), acceptAlert(), dismissAlert() |
| **DynamicPage** | Dynamic content interaction | waitForDynamicElement(), getUpdatedContent(), verifyDynamicLoad() |
| **FramePage** | iFrame switching & operations | switchToFrame(), switchToParentFrame(), interactWithFrameElement() |

---

##  Base Classes

### BaseTest.java
Provides setup and teardown for all tests.

**Methods:**

- `setUp()` (Before each test)
  - Initialize the configuration reader to load properties
  - Initialize WebDriver instance using DriverFactory
  - Navigate to the test application URL from configuration
  - Apply implicit waits as configured
  - Prepare the driver for test execution

- `tearDown()` (After each test)
  - Gracefully close the WebDriver instance
  - Remove driver from ThreadLocal storage
  - Clean up all browser resources
  - Log test completion status

**How It Works:**

The `setUp()` method runs before every test method automatically. It retrieves the WebDriver that was initialized by DriverFactory (using ThreadLocal for thread safety in parallel execution) and navigates to the base URL configured in the properties file. The `tearDown()` method runs after every test method to ensure clean shutdown of the browser, which is critical for maintaining system resources and preventing memory leaks, especially during parallel test execution.

---

### BasePage.java
Common methods and utilities for all page objects.

**Methods:**

- `waitForElement(By locator)` - Wait for element visibility
- `click(By locator)` - Click with scroll and JS fallback
- `type(By locator, String value)` - Type with clear
- `getText(By locator)` - Extract element text

**Features:**

- WebDriverWait integration for explicit waits
- Configuration-driven timeout values
- Explicit waits for element stability
- Intelligent scrolling to center the element in view
- Fallback to JavaScript click if regular click fails
- Element clearing before typing to avoid duplicate text

**How Page Objects Use BasePage:**

Each page object class inherits from BasePage to gain access to these common methods. For example, when you create a FormPage class, you can directly use the inherited methods like `type()` to enter text into form fields, `click()` to interact with buttons, and `getText()` to retrieve displayed information. This inheritance approach eliminates code duplication and ensures consistent element interaction patterns across all page objects. The methods internally handle all the complexity of waiting, scrolling, and error handling, so page objects can focus on page-specific logic only.

---

##  Technologies & Dependencies

### Core Technologies
- **Java 8+**: Programming language
- **Selenium WebDriver 4.34.0**: Browser automation
- **TestNG 7.10.2**: Test framework and annotations
- **Maven 3.6+**: Build automation tool

### Key Dependencies

| Dependency | Version | Purpose |
|---|---|---|
| Selenium Java | 4.34.0 | WebDriver and element interaction |
| TestNG | 7.10.2 | Test framework and annotations |
| WebDriverManager | 5.9.2 | Automatic Chrome driver management |
| Apache POI | 5.4.1 | Excel file operations |
| Extent Reports | 5.1.1+ | HTML test reporting |
| Commons-IO | 2.15.1 | File I/O operations |

---

##  Setup & Installation

### Prerequisites
- Java JDK 8 or higher installed
- Maven 3.6+ installed
- Chrome browser installed (for Selenium WebDriver)
- Git (optional, for cloning the repository)

### Installation Steps

1. **Clone or Download the Project**
   ```bash
   git clone <repository-url>
   cd AdminPanel-Automation
   ```

2. **Install Dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify Setup**
   ```bash
   mvn -v
   java -version
   ```

4. **Verify Selenium Setup**
   ```bash
   mvn -Dtest=FormTest test
   ```

---

## Configuration Management

### config.properties
Contains externalized test configuration.

**Location:** `src/main/resources/config.properties`

**What It Contains:**

The configuration file stores all the settings your tests need to run. This includes the browser type (Chrome or Firefox), whether to run in headless mode (without opening a visible browser window), the URL of the test application, and timeout values for how long to wait for elements. You can also add custom test data here like usernames, passwords, or test amounts.

**Purpose:**

By keeping all configuration in one external file, you can change how tests run without modifying any code. For example, you could switch from Chrome to Firefox, point tests to a different environment, or adjust wait times - all by editing this single file. This makes the framework flexible for different testing needs and environments.

**What You Can Modify:**

- Browser selection (Chrome or Firefox)
- Headless mode toggle (run without visible browser)
- Test application URL
- Element wait timeouts
- Test data (credentials, amounts, etc.)
- Any custom settings your tests need

---

##  Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn clean test -Dtest=FormTest
```

### Run Multiple Test Classes
```bash
mvn clean test -Dtest=FormTest,AlertTest,DynamicTest
```

### Run Specific Test Method
```bash
mvn clean test -Dtest=FormTest#formSubmissionTest
```

### Run Using TestNG XML
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run with Maven Surefire Plugin
```bash
mvn clean verify
```

### Run in Parallel Mode
Update `testng.xml` and run:
```bash
mvn clean test
```

---

## Test Execution & Reporting

### Extent Report
**Location:** `reports/ExtentReport.html`

**Contains:**
- Test execution summary (Pass/Fail/Skip)
- Test timeline and duration
- Screenshots on test failure
- Detailed execution logs
- Error stack traces
- Browser and system information

### Screenshots
**Location:** `screenshots/`

**Captured On:**
- Test failure
- Alert scenarios
- Form validation errors
- Dynamic element state changes

**File Naming:** `{TestClassName}_{MethodName}.png`

### Console Output
Comprehensive logging includes:
- Test start/end messages
- Element interaction logs
- Alert handling details
- Form submission status
- Validation results
- Exception messages
- Screenshot file paths

---

##  Key Features

 **Page Object Model (POM)** - Maintainable and scalable test structure  
 **ThreadLocal WebDriver** - Thread-safe parallel test execution  
 **Explicit Waits** - Reliable element interaction with configurable timeouts  
 **Parallel Execution** - Run up to 5 tests simultaneously  
 **Alert Handling** - Comprehensive JavaScript alert management  
 **Frame Support** - iFrame and nested frame handling  
 **Dynamic Elements** - Async content and dynamic element testing  
 **Form Validation** - Complete form submission and validation testing  
 **Screenshot Capture** - Automatic screenshots on test failure  
 **TestNG Listener** - Cross-cutting concern handling  
 **Extent Reports** - Rich HTML reporting with detailed logs  
 **Configuration-Driven** - Externalized configuration for flexibility  
 **Error Handling** - Fallback mechanisms for robustness  
 **Comprehensive Logging** - Detailed console and report logging  

---

## Design Patterns Used

- **Page Object Model (POM)** - Separation of test logic and page locators
- **Singleton Pattern** - ExtentManager for single report instance
- **Factory Pattern** - DriverFactory for WebDriver initialization
- **Base Class Pattern** - BasePage & BaseTest for code reuse
- **ThreadLocal Pattern** - Thread-safe WebDriver management
- **Listener Pattern** - TestListener for cross-cutting concerns

---

##  Best Practices Implemented

 **Explicit Waits** - Used for all element interactions  
 **Configuration Externalization** - Properties file for flexibility  
 **Meaningful Naming** - Self-documenting test and method names  
 **DRY Principle** - Reusable methods in BasePage and utilities  
 **Error Handling** - Try-catch for alert and exception scenarios  
 **Comprehensive Logging** - Detailed console output for debugging  
 **Thread Safety** - ThreadLocal for parallel execution  
 **Screenshot Management** - Automatic capture on failure  
 **Clean Code** - Well-organized packages and classes  
 **Separation of Concerns** - Clear layering (pages, tests, utils, base)  

---

##  Common Test Scenarios

### 1. Form Testing Flow
- Navigate to form page
- Fill form fields with valid data
- Submit form
- Verify success message
- Handle validation errors
- Test with invalid data
- Verify error messages

### 2. Alert Handling Flow
- Trigger JavaScript alert
- Extract alert text
- Accept/dismiss alert
- Verify page state after alert
- Handle multiple alerts
- Test alert on form validation

### 3. Dynamic Element Testing Flow
- Navigate to dynamic content page
- Wait for elements to load
- Interact with dynamic elements
- Verify content updates
- Handle element state changes
- Test async content loading

### 4. Frame Interaction Flow
- Identify frames on page
- Switch to target frame
- Interact with frame elements
- Return to parent frame
- Handle nested frames
- Verify frame-specific content

---

## TestNG Suite Configuration

**File:** `testng.xml`

**Purpose:**

The TestNG suite configuration file defines how your tests are organized and executed. It specifies which test classes should run, whether tests run sequentially or in parallel, and how many threads to use for concurrent execution. The file also registers listeners that handle reporting, screenshot capture, and other cross-cutting concerns.

**Key Configuration Elements:**

- **Suite Name**: Identifies your test suite
- **Parallel Execution Mode**: Specifies whether tests run one at a time or simultaneously
- **Thread Count**: Number of concurrent tests (up to 5 in this framework for parallel execution)
- **Listeners**: TestListener class that captures screenshots and generates reports
- **Test Modules**: Groups of test classes organized by functionality (Form Tests, Alert Tests, Dynamic Tests, etc.)

**What It Does:**

When you run `mvn clean test`, Maven reads this file to understand which tests to execute and how. It starts multiple threads based on the configuration, ensuring each test runs independently with its own WebDriver instance (managed by ThreadLocal). This enables fast parallel test execution while maintaining test isolation and preventing interference between concurrent tests.

---

##  Troubleshooting

| Issue | Solution |
|---|---|
| **WebDriver not initialized** | Check WebDriverManager setup, verify Chrome is installed, check config.properties |
| **Element not found** | Verify locators in page objects, increase timeout, check for dynamic elements, use browser dev tools |
| **Alert not handled** | Ensure alert presence before accepting, add try-catch, verify alert trigger timing |
| **Timeout exceptions** | Increase timeout value in config.properties, verify element is actually loading |
| **Configuration not loaded** | Check config.properties file exists, verify path is correct, check file permissions |
| **Screenshot not captured** | Ensure screenshots/ folder exists, verify write permissions, check ScreenshotUtil implementation |
| **Parallel test failures** | Check ThreadLocal driver management, ensure test independence, verify thread count |
| **Frame switching fails** | Verify frame element exists, check frame ID/name/index, ensure proper frame hierarchy |
| **Headless mode issues** | Set `headless=false` in config.properties for visual debugging |
| **Report not generated** | Verify ExtentManager initialization, check TestListener implementation, verify report path |

---

##  Extending the Framework

### Add New Test Class

1. Create a new test class in the `tests/` package that extends `BaseTest`
2. Declare page object instances as class variables
3. Create page object instances in a BeforeMethod or directly use them in test methods
4. Use page objects to interact with application elements
5. Use assertions to validate expected results
6. Add the new test class name to the testng.xml suite configuration file

**What Happens:**

When you extend BaseTest, your new test class automatically inherits the setUp() and tearDown() methods, so the WebDriver is automatically initialized before each test and cleaned up after. You then create page objects within your test methods and use their methods to perform actions and validations. TestNG annotations like @Test mark which methods are actual test cases that should be executed.

### Add New Page Object

1. Create a new page class in the `pages/` package that extends `BasePage`
2. Define page locators as private static variables using By (e.g., By.id, By.xpath, By.cssSelector)
3. Create public methods that represent user actions on that page
4. Use inherited BasePage methods (type(), click(), getText(), waitForElement()) within your methods
5. Keep methods focused on single responsibilities (one action per method)
6. Import this page object into your test classes when needed

**What Happens:**

When you create a new page object, you're essentially creating a blueprint of that page. Each element on the page gets its own locator defined at the top. Then you write methods that represent what users can do on that page (like fillForm(), submitForm(), verifyMessage()). By keeping all page-specific information in one place, your tests become cleaner and more maintainable. If a page element changes location, you only need to update the locator in one place instead of multiple test files.

### Add New Configuration Properties

1. Open the `src/main/resources/config.properties` file
2. Add a new line with your property in the format: `propertyName=propertyValue`
3. In your test classes or utilities, use `ConfigReader.getProperty("propertyName")` to retrieve the value
4. The property can be anything - test data, URLs, credentials, timeouts, or flags

**Why This Matters:**

By keeping configuration external in a properties file, you can change test parameters without recompiling code. For example, you could have different properties files for different environments (dev, staging, production) or different test data sets. This makes the framework flexible and reusable across different scenarios. Anyone can modify test settings without needing to understand Java code.


## Notes and Considerations

- All tests use explicit waits for better reliability
- Screenshot capture is automatic on test failure
- Console output provides detailed execution flow
- Framework supports multiple browsers (Chrome, Firefox)
- Configuration values can be changed without code modification
- Thread-safe WebDriver management enables parallel execution
- All locators are defined in respective page objects
- Framework is designed for EvilTester Admin Portal
- Compatible with CI/CD pipelines (Jenkins, GitHub Actions, etc.)

---

##  Document Information

**Last Updated:** April 30, 2026  
**Framework Version:** 1.0  
**Selenium Version:** 4.34.0  
**TestNG Version:** 7.10.2  
**Java Version:** 8+  
**Status:** Production Ready

## Author
Bhavya Sree Kasa

# Selenium Automation Project for Sauce Demo E-Commerce Website

This project is designed to automate several functionalities of the Sauce Demo e-commerce website using Selenium WebDriver. The automation framework is built with scalability, reusability, and maintainability in mind, leveraging key testing concepts such as the Page Object Model (POM), TestNG, and data-driven testing with TestNG’s `@DataProvider`.

## Key Features

### 1. Page Object Model (POM)
The framework follows the Page Object Model design pattern to ensure separation of concerns. Each web page is represented by a dedicated class, encapsulating its web elements and user interactions. This structure enhances the maintainability and reusability of the test code.

### 2. TestNG
TestNG is used as the test management framework for:
- Organizing and running test cases
- Managing test setup and teardown (`@BeforeMethod`, `@AfterMethod`)
- Parallel test execution
- Generating detailed test reports

### 3. Data-Driven Testing
The framework implements data-driven testing using TestNG’s `@DataProvider`. This allows tests to be executed with multiple sets of input data, improving test coverage and flexibility.

## Technologies and Tools

- **Selenium WebDriver**: Browser automation
- **Java**: Programming language
- **TestNG**: Test management and reporting
- **Page Object Model (POM)**: Framework design pattern for maintainability

## Automated Functionalities

The automated functionalities for this project include:
- **Login functionality**: Automating the login process using valid and invalid credentials.
- **Product functionality**: Interacting with product listings, including adding products to the cart.
- **Product details info**: Verifying details of individual products.
- **Burger menu**: Testing navigation and accessibility of the burger menu.
- **Cart functionality**: Managing items in the shopping cart.
- **Footer verification**: Ensuring footer elements are present and functioning.

## Getting Started

### Prerequisites

- Java JDK
- Maven
- ChromeDriver or other WebDriver executables
- IDE (Eclipse, IntelliJ)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/selenium-demo-qa.git
2. Install dependencies using Maven:
   mvn install
3. Run tests with TestNG:
   mvn test

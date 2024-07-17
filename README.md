# Firstname Lastname Teladoc Challenge

## Overview

This project is a UI automation challenge for Teladoc. It automates scenarios on the [Way2Automation Web Tables](http://www.way2automation.com/angularjs-protractor/webtables/) page using a BDD framework.

## Project Structure

- `src/test/java`: Contains the step definitions and test runner classes.
- `src/test/resources/features`: Contains the feature files written in Gherkin syntax.

## Prerequisites

- Java JDK 8 
- Maven
- A web browser (Chrome)
- [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/) or [GeckoDriver](https://github.com/mozilla/geckodriver) for Selenium

## Setup

1. Clone the repository:
       git clone https://github.com/yourusername/firstname_lastname_teladoc_challenge.git
     

2. Install dependencies using Maven:
  
    mvn clean install
    ```

3. Update the WebDriver path in the `StepDefinitions.java` file if necessary.

## Running the Tests

To execute the tests, use the following Maven command:

mvn test

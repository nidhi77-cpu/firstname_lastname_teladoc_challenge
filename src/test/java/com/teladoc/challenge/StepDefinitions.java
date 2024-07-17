package com.teladoc.challenge;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.Assert.*;

public class StepDefinitions {
	private WebDriver driver;

	@Given("I navigate to the web tables page")
	public void i_navigate_to_the_web_tables_page() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-extensions");
		options.addArguments("--remote-allow-origins=*");

		try {
			driver = new ChromeDriver(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (driver != null) {
			driver.manage().window().maximize();
			driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
		} else {
			System.out.println("Failed to initialize ChromeDriver.");
		}
	}

	@When("I add a user with first name {string}, last name {string}, username {string}, password {string}, customer {string}, role {string}, email {string}, and phone {string}")
	public void i_add_a_user(String firstName, String lastName, String username, String password, String customer,
			String role, String email, String phone) {
		driver.findElement(By.xpath("//button[@type='add']")).click();
		driver.findElement(By.name("FirstName")).sendKeys(firstName);
		driver.findElement(By.name("LastName")).sendKeys(lastName);
		driver.findElement(By.name("UserName")).sendKeys(username);
		driver.findElement(By.name("Password")).sendKeys(password);
		driver.findElement(By.name("Email")).sendKeys(email);
		driver.findElement(By.name("Mobilephone")).sendKeys(phone);

		// Select customer
		List<WebElement> customerOptions = driver.findElements(By.xpath("//input[@name='optionsRadios']"));
		for (WebElement option : customerOptions) {
			if (option.findElement(By.xpath(".//..")).getText().equals(customer)) {
				option.click();
				break;
			}
		}

		// Select role
		WebElement roleDropdown = driver.findElement(By.name("RoleId"));
		roleDropdown.findElement(By.xpath("//option[. = '" + role + "']")).click();

		driver.findElement(By.xpath(".//button[text()='Save']")).click();
	}

	@Then("I should see the user {string} in the table")
	public void i_should_see_the_user_in_the_table(String username) {
		List<WebElement> rows = driver.findElements(By.xpath("//table[@table-title='Smart Table example']//tbody//tr"));
		boolean userFound = rows.stream().anyMatch(row -> row.getText().contains(username));
		assertTrue("User " + username + " should be in the table", userFound);
	}

	@When("I delete the user {string}")
	public void i_delete_the_user(String username) {
		List<WebElement> rows = driver.findElements(By.xpath("//table[@table-title='Smart Table example']//tbody//tr"));
		for (WebElement row : rows) {
			if (row.getText().contains(username)) {
				row.findElement(By.xpath(".//td//button//i[@class='icon icon-remove']")).click();
				driver.findElement(By.xpath(".//button[text()='OK']")).click();
				break;
			}
		}
	}

	@Then("I should not see the user {string} in the table")
	public void i_should_not_see_the_user_in_the_table(String username) {
		List<WebElement> rows = driver.findElements(By.xpath("//table[@table-title='Smart Table example']//tbody//tr"));
		boolean userFound = rows.stream().anyMatch(row -> row.getText().contains(username));
		assertFalse("User " + username + " should not be in the table", userFound);
		driver.quit();
	}
}

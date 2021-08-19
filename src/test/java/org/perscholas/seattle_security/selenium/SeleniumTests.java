package org.perscholas.seattle_security.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeleniumTests {
	
	private WebDriver driver;
	
	@Autowired
	public SeleniumTests(WebDriver driver) {
		this.driver = driver;
	}
	
	@Test
	void testLoginPage() {
		// Opens the login page for this web application
		driver.get("http://localhost:8080/");
		assertEquals("Login Page", driver.getTitle());
	}
	
	@Test
	void testLogin() throws InterruptedException {
		driver.get("http://localhost:8080/");
		
		WebElement usernameInput = driver.findElement(By.cssSelector(
				"body > form > div:nth-child(1) > input[type=text]"));
		usernameInput.sendKeys("John");
		
		WebElement passwordInput = driver.findElement(By.cssSelector(
				"body > form > div:nth-child(2) > input[type=text]"));
		passwordInput.sendKeys("john1234");
		
		driver.findElement(By.cssSelector(
				"body > form > div:nth-child(3) > input[type=submit]")).click();
		
		WebElement welcomeMessage = driver.findElement(By.cssSelector("body > h2"));
		
		assertEquals("Home Page", driver.getTitle());
		assertTrue(welcomeMessage.getText().contains("John"));
	}
}
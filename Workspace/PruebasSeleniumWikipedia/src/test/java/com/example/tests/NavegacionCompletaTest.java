package com.example.tests;

import static org.junit.Assert.fail;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

public class NavegacionCompletaTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new EventFiringWebDriver(new FirefoxDriver());
		((EventFiringWebDriver)driver).register(new CapturaDeImagenesAnteErrores());
		
		baseUrl = "https://www.w3schools.com";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void testNavegacionCompleta() throws Exception {
		driver.get(baseUrl + "/html");
		
		//Priemra interaccion
		driver.findElement(By.linkText("HTML Forms")).click();
		driver.findElements(By.partialLinkText("Try it Yourself")).get(2).click();

		String parentWindow = driver.getWindowHandle();

		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				
				driver.switchTo().frame("iframeResult");
				driver.findElement(By.xpath("(//input[@name='gender'])[2]")).click();
				driver.findElement(By.xpath("(//input[@name='gender'])[3]")).click();
				driver.findElement(By.name("gender")).click();				
				
				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}

		//Segunda interaccion
		driver.findElement(By.linkText("HTML Form Elements")).click();
		driver.findElements(By.partialLinkText("Try it Yourself")).get(0).click();
		
		handles = driver.getWindowHandles();
		
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				
				driver.switchTo().frame("iframeResult");
		
				new Select(driver.findElement(By.name("cars"))).selectByVisibleText("Fiat");
				driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
				
				Assert.assertEquals("cars=fiat", driver.findElement(By.cssSelector("div.w3-container")).getText());
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}

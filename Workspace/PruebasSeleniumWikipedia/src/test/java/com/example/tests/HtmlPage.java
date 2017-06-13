package com.example.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class HtmlPage extends LoadableComponent<HtmlPage> {

	private WebDriver driver;
	private String baseUrl;

	public HtmlPage(WebDriver driver, String baseUrl) {
		super();
		
		this.driver = driver;
		this.baseUrl = baseUrl;
	}

	// driver.findElement(By.linkText("HTML Forms")).click();
	@FindBy(linkText = "HTML Forms")
	private WebElement htmlFormsLink;

	// driver.findElement(By.linkText("HTML Form Elements")).click();
	@FindBy(linkText = "HTML Form Elements")
	private WebElement htmlFormsElementsLink;

	@Override
	protected void isLoaded() throws Error {
		Assert.assertNotNull(htmlFormsLink);
		Assert.assertNotNull(htmlFormsElementsLink);
	}

	@Override
	protected void load() {
		driver.get(baseUrl + "/html");
		PageFactory.initElements(this.driver, this);
	}

	// funcionalidad que abstraemos
	public void navegarPaginaHtmlForm() {
		this.htmlFormsLink.click();
	}

	// funcionalidad que abstraemos
	public void navegarPaginaHtmlFormElements() {
		this.htmlFormsElementsLink.click();
	}

}

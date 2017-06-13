package com.example.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class CapturaDeImagenesAnteErrores extends AbstractWebDriverEventListener {

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		File screenshotAs = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(screenshotAs, new File("target/surefire-reports/" + "ErrorEnTestNavegacion.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

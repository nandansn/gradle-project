package com.gradle.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SampleDragNDrop {

	static WebDriver driver = null;
	String url = "http://127.0.0.1:5500/learnings/dd2.html";

	@BeforeClass
	public void openApp() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\nrangasa.ORADEV\\chromedriver\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test(enabled = false)
	public void testSelenium() throws InterruptedException {

		WebElement from = driver.findElement(By.id("dragtarget"));

		WebElement to = driver.findElement(By.id("dropTarget"));

		Actions dragandDropAcion = new Actions(driver);
		dragandDropAcion.clickAndHold(from).moveToElement(to).release(to).build().perform();
		

		TimeUnit.SECONDS.sleep(4);

	}
	
	
	@Test(enabled = true)
	public void testJavaScript() throws InterruptedException {

		TimeUnit.SECONDS.sleep(4);
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript(
				"var srcElement = document.getElementById('dragtarget');"+
						"var tgtElement = document.getElementById('dropTarget');"+
						"var evt = document.createEvent('DragEvent');"+
						"evt.initEvent('dragstart');"+
						"srcElement.dispatchEvent(evt);"+
						"evt.initEvent('drag');"+
						"srcElement.dispatchEvent(evt);"+
						"evt.initEvent('dragover');"+
						"srcElement.dispatchEvent(evt);"+
						"evt.initEvent('drop');"+
						"tgtElement.dispatchEvent(evt);"
			
				);
		
		

		TimeUnit.SECONDS.sleep(4);

	}

	@AfterClass
	public void closeApp() {
		driver.quit();
	}
}

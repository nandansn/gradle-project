package com.amazon.automation;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class AmazonHomeTest {

	static final String APP_URL = "http://localhost:8080/dataflow/create";//"https://www.verizonwireless.com/smartphones/apple-iphone-xs-max/?sku=sku3180117";

	static WebDriver driver;
	
	static int counter = 0;

	@BeforeTest
	public void start() {
		driver = new ChromeDriver();
		driver.get(APP_URL);
		
		
		
		
		
		
	}

	@Test(description="Conditional break point.")
	public void testCondition() throws IOException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		URL jqueryUrl = Resources.getResource("jquery.min.js");
        System.out.println(jqueryUrl.getPath());
        String jqueryText = Resources.toString(jqueryUrl, Charsets.UTF_8);
        js.executeScript(jqueryText);
		
		driver.findElement(By.xpath("//span[contains(text(),'Browse...')]")).click();
		
		
		
		String readyState = js.executeScript("return document.readyState").toString();
        System.out.println("Ready State: " + readyState);
        
        
		
		
        js.executeScript("$('span.oui-modal-header-close-link > a.oui-userselect-none')[0].click()");
		
		driver.findElement(By.xpath("//button[contains(@class,'addToCartBtn')  and  not(@disabled)  and  not(contains(@class,'is-visuallyHidden'))]")).click();

		driver.get(APP_URL);
		driver.findElement(By.xpath("//span[contains(text(),'Category')]")).click();
	}
	
	@Test(description="Exceptional break point.")
	public void testException(){
		WebElement element = null;
		
		element.findElement(By.xpath("some xpath...")).click();
		
		
	}
	
	@Test(description="Watch point...test")
	public void testWatchPoint(){
		
		System.out.println(String.format("Init counter value:%d", counter));
		
		
		Arrays.asList(1,2,3).forEach(i -> counter++);
	}
	
	@Test(description="step filtering")
	public void testStepFiltering(){
		
		driver.get(APP_URL);
		driver.findElement(By.xpath("//span[contains(text(),'Category')]")).click();
	}
	
	

	@AfterTest
	public void quit() {
		driver.quit();
	}

}

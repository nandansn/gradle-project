package com.amazon.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PerfectoMobile {
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		
		
		//This code will run the test on Perfecto Mobile Cloud
		
		String browserName = "safari";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName,"",Platform.IOS);
		// Below details we can get it from perfecto lab.
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "12.1");
		capabilities.setCapability("manufacturer", "Apple");
		capabilities.setCapability("model", "iPad Mini 4");
		capabilities.setCapability("location", "NA-US-BOS");
		capabilities.setCapability("resolution", "1536 x 2048");
		//Enter your user and password
		capabilities.setCapability("user", "nandakumar.rangasamy@oracle.com");
		capabilities.setCapability("password", "YgahY5ALe");
		// Name your script
		capabilities.setCapability("scriptName", "FirstTestCase");
		// Enter your cloud URL
		String host = "mobilecloud.perfectomobile.com";

		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		try {
		//Launch the chrome browser and navigate to www.google.com
		driver.get("https://www.amazon.in/");
		//Wait for 3 Sec
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@class='nav-a'][contains(text(),'Sell')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Facebook')]")).click();
		System.out.println(driver.getPageSource());
		Thread.sleep(3000);
		}

		finally {
		// Close the driver
		driver.quit();
		}
		}

}

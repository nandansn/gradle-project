package com.gradle.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SeleniumTestOne {

	@Test(enabled = false)
	public void testOne() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\nrangasa.ORADEV\\chromedriver\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://www.dhtmlgoodies.com/submitted-scripts/i-google-like-drag-drop/index.html");

		WebDriverWait wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()='Block 1']"))));

		WebElement sourceElement = driver.findElement(By.xpath("//h1[text()='Block 1']"));

		WebElement targetElement = driver.findElement(By.xpath("//h1[text()='Block 3']"));

		Actions dragandDropAcion = new Actions(driver);
		dragandDropAcion.dragAndDrop(sourceElement, targetElement).perform();

		TimeUnit.SECONDS.sleep(5);

		driver.close();

	}
	
	
	@Test(enabled=false)
	public void testTwo() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\nrangasa.ORADEV\\chromedriver\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		//driver.get("http://slc14xrm.us.oracle.com:20614/dicloud/app/mapping/edit/53a72b80-1f9a-11e9-8d66-2773bcdc2618");
		
		driver.get("http://127.0.0.1:5500/telusko/dragndrop/dd.html");
		
		String script = "document.getElementById('demo').addEventListener('click', () => {document.getElementById('demo').innerHTML = 'YOU CLICKED ME!';});"
				+ "document.getElementById('demo').click();";
				
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(script);
		
		TimeUnit.SECONDS.sleep(15);
		

		//WebDriverWait wait = new WebDriverWait(driver, 20);
		
		//driver.findElement(By.id("j_username")).sendKeys("weblogic");
		//driver.findElement(By.id("j_password")).sendKeys("welcome1");
		
		//driver.findElement(By.cssSelector("input[type='submit'][class='loginBtn'][name='action'][value='Login']")).click();
		
		

		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='minModeContainer']/img[@id='Source']"))));
		//WebElement sourceElement = driver.findElement(By.xpath("//img[@id='Source']"));

		//WebElement targetElement = driver.findElement(By.xpath("//*[name()='svg']//*[name()='g'][contains(@clip-path,'#pzc')]"));

		//Actions dragandDropAcion = new Actions(driver);
		//dragandDropAcion.dragAndDropBy(sourceElement, 362, 76);
		
		
		//System.out.println(driver.findElement(By.xpath("//img[@id='Source']")).getAttribute("title"));
		
		//dragandDropAcion.moveToElement(sourceElement);
		//dragandDropAcion.clickAndHold();
		//dragandDropAcion.moveByOffset(326, 76);
		//dragandDropAcion.release();
		
		
		//driver.findElement(By.xpath("//img[@title='Close']")).click();
		


		TimeUnit.SECONDS.sleep(5);

		driver.close();

	}
	
	@Test
	public void testThree() throws InterruptedException{
		
		/*System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\nrangasa.ORADEV\\gecko\\geckodriver.exe");
*/
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\nrangasa.ORADEV\\chromedriver\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/dataflow/create");
		
		
		
		
		
		
		TimeUnit.SECONDS.sleep(10);
		
		/*if (driver.findElement(By.xpath("//div[@class='desk'][1]//div[@id='0']")).isDisplayed()){
			
		} else {
			System.exit(0);
		}*/
		
		// document.getElementById(\"//div[@class='desk'][1]//div[@id='0']\")
		//document.evaluate(\"//div[@class='desk'][1]//div[@id='1']\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null )
		
		/*String script = "elemDrag = document.getElementById('dragtarget'); console.log(elemDrag);"+
				"elemDrop = document.getElementById('dropTarget'); "
				+ "var pos = document.getElementById('dragtarget').getBoundingClientRect();"
				+ "pos = elemDrop.getBoundingClientRect();"+
				"var center1X = 200;"+
				"var center1Y = 50;"+
				"var center2X = 300;"+
				"var center2Y = 50;"+
				"var fireMouseEvent = function (type, element, centerX, centerY) {"+
				
				
				"var evt = document.createEvent('MouseEvent');"+
				
				"evt.initMouseEvent(type, true, true, window, 1, 1, 1, centerX, centerY, false, false, false, false, 0, element.element);"+
				"element.dispatchEvent(evt);"+
				"};"+
				"fireMouseEvent('mousemove', elemDrag, center1X, center1Y);"+
				"fireMouseEvent('mouseenter', elemDrag, center1X, center1Y);"+
				"fireMouseEvent('mouseover', elemDrag, center1X, center1Y);"+
				"fireMouseEvent('mousedown', elemDrag, center1X, center1Y);"+
				"fireMouseEvent('dragstart', elemDrag, center1X, center1Y);"+
				"fireMouseEvent('drag', elemDrag, center1X, center1Y);"+
				"fireMouseEvent('mousemove', elemDrag, center1X, center1Y);"+
				"fireMouseEvent('drag', elemDrag, center2X, center2Y);"+
				"fireMouseEvent('mousemove', elemDrop, center2X, center2Y);"+
				"fireMouseEvent('mouseenter', elemDrop, center2X, center2Y);"+
				"fireMouseEvent('dragenter', elemDrop, center2X, center2Y);"+
				"fireMouseEvent('mouseover', elemDrop, center2X, center2Y);"+
				"fireMouseEvent('dragover', elemDrop, center2X, center2Y);"+
				"fireMouseEvent('drop', elemDrop, center2X, center2Y);"+
				"fireMouseEvent('dragend', elemDrag, center2X, center2Y);"+
				"fireMouseEvent('mouseup', elemDrag, center2X, center2Y);";
				
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(script);*/
		
		WebElement source = driver.findElement(By.id("Source"));
		
		
		Actions dragandDropAcion = new Actions(driver);
		dragandDropAcion.dragAndDrop(source, driver.findElement(By.id("layer0-selectbox"))).build().perform();
		
		
		
		
		TimeUnit.SECONDS.sleep(4);
		
		driver.quit();

	}
}

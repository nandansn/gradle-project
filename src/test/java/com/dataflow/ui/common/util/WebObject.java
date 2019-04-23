package com.dataflow.ui.common.util;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * This class can be used to define a fixture object for interacting with an object on a 
 * page in WebDriver.  It implements WebElement, but adds other features.  Also, unlike
 * WebElement, which can't be created until the object exists on the page, the WebObject 
 * can be created ahead of time, and contains methods for checking/waiting for an object to
 * exist.  The methods in this also contain built in logging, so if you perform an action
 * it will automatically be logged.
 * @author nglatzer
 *
 */
public class WebObject implements WebElement {
	WebDriver m_browser;
	String m_name;
	By m_by;
	//Logger m_logger;
	Long m_timeout;
	
	/**
	 * Constructor for the object.
     * timeout is default to UITestContext.getInstance().getTestConfigParams().getSeleniumElementWait().
	 * @param browser the WebDriver object for the current browser session
	 * @param by the locator definition for the object
	 * @param name the logical name of the object (used for logging)
	 */
	public WebObject(WebDriver browser, By by, String name){
        //this(browser, by, name, UITestContext.getInstance().getTestConfigParams().getSeleniumElementWait());
        this(browser, by, name, 100L);
	}
	
	/**
	 * Constructor for the object
	 * @param browser the WebDriver object for the current browser session
	 * @param by the locator definition for the object
	 * @param name the logical name of the object (used for logging)
	 * @param timeout the default timeout value for interactions with this object
	 */
	public WebObject(WebDriver browser, By by, String name, Long timeout){
        m_browser = browser;
        m_by = by;
        m_name = name;
        //m_logger = LogManager.getLogger(WebObject.class.getSimpleName());
		m_timeout = timeout;
	}

	/**
	 * Constructor for the object.
     * timeout is default to UITestContext.getInstance().getTestConfigParams().getSeleniumElementWait().
	 * @param by the locator definition for the object
	 * @param name the logical name of the object (used for logging)
	 */
	public WebObject(By by, String name){
		this(UITestContext.getInstance().getWebDriver(), by, name);
	}

	/**
	 * Constructor for the object
	 * @param by the locator definition for the object
	 * @param name the logical name of the object (used for logging)
	 * @param timeout the default timeout value for interactions with this object
	 */
	public WebObject(By by, String name, Long timeout){
		this(UITestContext.getInstance().getWebDriver(), by, name, timeout);
	}

	/**
	 * Returns the logical name of the object
	 */
	public String toString(){
		return m_name;
	}
	
	/**
	 * Gets the locator for the object
	 * @return the locator for the object
	 */
	public By getBy(){
		return m_by;
	}
	
	/**
	 * Returns the WebElement version of this object.
	 * @return the WebElement of this object.
	 */
	public WebElement getElement(){
		return m_browser.findElement(m_by);
	}
	
	/**
	 * Gets the locator value for the object
	 * @return the locator value for the object.
	 */
	public String getLocator(){
		String byStr = getBy().toString();
		String locator = byStr.substring(byStr.indexOf(":") + 2, byStr.length());
		return locator;
	}
	
	/**
	 * Gets the name for the object, or if it is not set, the locator for the object
	 * @return the name for the object, or if it is not set, the locator for the object
	 */
	public String getName(){
		if(m_name!=null){
			if(!m_name.isEmpty()){
				return m_name;
			}
		}
		return m_by.toString();
	}

	/**
	 * Performs a click action on this object
	 */
	public void click() {
		m_browser.findElement(m_by).click();
		//m_logger.info("Clicked the object '"+getName()+"'.");
	}
	
	/**
	 * Performs a click action on this object at the offset location specified.
	 * The location clicked will be relative to the object, where the upper
	 * right hand corner of the object would be 0,0
	 * @param xOffSet the horizontal position in the object that will be clicked
	 * @param yOffSet the vertical position in the object that will be clicked
	 */
	public void clickAt(int xOffSet, int yOffSet){
		action().moveToElement(getElement(), xOffSet, yOffSet).click().perform();
		//m_logger.info("Clicked the object '"+getName()+"' at the location ("+xOffSet+","+yOffSet+").");
	}
	
	/**
	 * Waits until the object is present (for the default timeout value for the object) and then
	 * clicks on it if it is present before the timeout.
	 */
	public void waitAndClick(){
		this.waitUntilVisible();
		this.click();
	}
	
	/**
	 * Waits until the object is present for the passed in timeout value and then
	 * clicks on it if it is present before the timeout.
	 * @param timeout
	 */
	public void waitAndClick(Long timeout){
		this.waitUntilVisible(timeout);
		this.click();
	}
	
	/**
	 * Waits until the object is present for the passed in timeout value and then
	 * clicks on it if it is present before the timeout.
	 * @param timeout
	 */
	public void waitAndClick(Integer timeout){
		this.waitUntilVisible(timeout);
		this.click();
	}
	
	/**
	 * Waits until the object is present (for the default timeout value for the object) and then
	 * clicks at the offset location specified. The location clicked will be relative to the object, where the upper
	 * right hand corner of the object would be 0,0
	 * @param xOffSet the horizontal position in the object that will be clicked
	 * @param yOffSet the vertical position in the object that will be clicked
	 */
	public void waitAndClickAt(int xOffSet, int yOffSet){
		this.waitUntilVisible();
		this.clickAt(xOffSet, yOffSet);
	}
	
	/**
	 * Waits until the object is present and then clicks at the offset location specified.
	 * The location clicked will be relative to the object, where the upper
	 * right hand corner of the object would be 0,0
	 * @param timeout the time to wait for the object to be present before throwing an exception
	 * @param xOffSet the horizontal position in the object that will be clicked
	 * @param yOffSet the vertical position in the object that will be clicked
	 */
	public void waitAndClickAt(Long timeout, int xOffSet, int yOffSet){
		this.waitUntilVisible(timeout);
		this.clickAt(xOffSet, yOffSet);
	}
	
	/**
	 * Waits until the object is present and then clicks at the offset location specified.
	 * The location clicked will be relative to the object, where the upper
	 * right hand corner of the object would be 0,0
	 * @param timeout the time to wait for the object to be present before throwing an exception
	 * @param xOffSet the horizontal position in the object that will be clicked
	 * @param yOffSet the vertical position in the object that will be clicked
	 */
	public void waitAndClickAt(Integer timeout, int xOffSet, int yOffSet){
		this.waitUntilVisible(timeout);
		this.clickAt(xOffSet, yOffSet);
	}

	/**
	 * Submits a form object on the page for this object
	 */
	public void submit() {
		//m_logger.info("Clicked submit on the object '"+getName()+"'.");
		m_browser.findElement(m_by).submit();
	}

	/**
	 * Enters the specified value into the current object
	 */
	public void sendKeys(CharSequence... keysToSend) {
		//m_logger.info("Entered the text '"+getCharSequenceArrayAsString(keysToSend)+"' in the object '"+getName()+"'.");
		m_browser.findElement(m_by).sendKeys(keysToSend);
	}

	/**
	 * Set value for this element by Javascript.
	 *
	 * @param value   value to set.
	 */
	public void setValueByJS(String value) {
		//m_logger.info("Set the text '"+value+"' in the object '"+getName()+"'.");
		String javaScript = "arguments[0].value=arguments[1]";
		executeScript(javaScript, m_browser.findElement(m_by), value);
	}

	/**
	 * Converts a CharSequenceArray to string 
	 * @param keysToSend the CharSequence[]
	 * @return the string valure for the CharSequence[]
	 */
	private String getCharSequenceArrayAsString(CharSequence... keysToSend) {
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<keysToSend.length;i++){
            buf.append(keysToSend[i].toString());
        }
		return buf.toString();
	}
	
	/**
	 * Clears the object if requested before sending the specified value to the current object
	 * @param clear true if you want the object to be cleared before sending the value
	 * @param keysToSend the value to enter in the object
	 */
	public void sendKeys(boolean clear, CharSequence... keysToSend){
		if(clear){
			m_browser.findElement(m_by).clear();
		}
		sendKeys(keysToSend);
	}
	
	/**
	 * Waits for the object to appear and enters the
	 * specified value into it if it appears before the default timeout
	 * @param keysToSend the value to enter into the object.
	 */
	public void waitAndSendKeys(CharSequence... keysToSend) {
		this.waitUntilVisible();
		this.sendKeys(keysToSend);
	}
	
	/**
	 * Waits for the object to appear and enters the
	 * specified value into it if it appears before the specified timeout
	 * @param timeout the time to wait for the object before throwing an exception
	 * @param keysToSend the value to enter into the object.
	 */
	public void waitAndSendKeys(Long timeout, CharSequence... keysToSend) {
		this.waitUntilVisible(timeout);
		this.sendKeys(keysToSend);
	}
	
	/**
	 * Waits for the object to appear and enters the
	 * specified value into it if it appears before the specified timeout
	 * @param timeout the time to wait for the object before throwing an exception
	 * @param keysToSend the value to enter into the object.
	 */
	public void waitAndSendKeys(Integer timeout, CharSequence... keysToSend) {
		this.waitUntilVisible(timeout);
		this.sendKeys(keysToSend);
	}
	
	/**
	 * Waits for the object to appear and clear the object then enters the
	 * specified value into it if it appears before the default timeout
	 * @param clear true to clear the object, false not to clear the object before entering into it.
	 * @param keysToSend the value to enter into the object.
	 */
	public void waitAndSendKeys(boolean clear, CharSequence... keysToSend){
		this.waitUntilVisible();
		this.sendKeys(clear, keysToSend);
	}
	
	/**
	 * Waits for the object to appear and clear the object then enters the
	 * specified value into it if it appears before the specified timeout
	 * @param timeout the time to wait for the object before throwing an exception
	 * @param clear true to clear the object, false not to clear the object before entering into it.
	 * @param keysToSend the value to enter into the object.
	 */
	public void waitAndSendKeys(Long timeout, boolean clear, CharSequence... keysToSend){
		this.waitUntilVisible(timeout);
		this.sendKeys(clear, keysToSend);
	}
	
	/**
	 * Waits for the object to appear and clear the object then enters the
	 * specified value into it if it appears before the specified timeout
	 * @param timeout the time to wait for the object before throwing an exception
	 * @param clear true to clear the object, false not to clear the object before entering into it.
	 * @param keysToSend the value to enter into the object.
	 */
	public void waitAndSendKeys(Integer timeout, boolean clear, CharSequence... keysToSend){
		this.waitUntilVisible(timeout);
		this.sendKeys(clear, keysToSend);
	}	

	/**
	 * Clears the current object
	 */
	public void clear() {
		//m_logger.info("Clearing the object '"+getName()+"'.");
		m_browser.findElement(m_by).clear();
	}

	/**
	 * Gets the tag name for the current object
	 */
	public String getTagName() {
		return m_browser.findElement(m_by).getTagName();
	}

	/**
	 * Gets the specified attribute value for the current object
	 */
	public String getAttribute(String name) {
		return m_browser.findElement(m_by).getAttribute(name);
	}

	/**
	 * Checks to see if the current object is selected
	 */
	public boolean isSelected() {
		return m_browser.findElement(m_by).isSelected();
	}

	/**
	 * Checks to see if the current object is enabled
	 */
	public boolean isEnabled() {
		return m_browser.findElement(m_by).isEnabled();
	}

	/**
	 * Gets the text of the current objct
	 */
	public String getText() {
		return m_browser.findElement(m_by).getText();
	}

	/**
	 * Finds a list of sub element of the current object
	 */
	public List<WebElement> findElements(By by) {
		return m_browser.findElement(m_by).findElements(by);
	}
	
	/**
	 * Finds a list of elements that match this objects locator 
	 * @return a list of WebElements that match this objects locator
	 */
	public List<WebElement> findElements(){
		return m_browser.findElements(m_by);
	}

	/**
	 * Finds a sub element of the current object
	 */
	public WebElement findElement(By by) {
		return m_browser.findElement(m_by).findElement(by);
	}
	
	/**
	 * Checks to see if this object is displayed
	 */
	public boolean isDisplayed() {
		return m_browser.findElement(m_by).isDisplayed();
	}

	/**
	 * Gets the location of this object
	 */
	public Point getLocation() {
		return m_browser.findElement(m_by).getLocation();
	}

	/**
	 * Gets the size of this object
	 */
	public Dimension getSize() {
		return m_browser.findElement(m_by).getSize();
	}

	/**
	 * Gets the the specified css property value  of this object
	 */
	public String getCssValue(String propertyName) {
		return m_browser.findElement(m_by).getCssValue(propertyName);
	}
	
	/**
	 * Waits until this object is present or throws and exception if it reaches the default timeout
	 * for the object
	 */
	public void waitUntilPresent(){
		waitUntilPresent(m_timeout);
	}
	
	/**
	 * Waits until this object is present or throws and exception if it reaches specified timeout
	 * @param timeout the time to wait for the object to be present before throwing an exception
	 */
	public void waitUntilPresent(Long timeout){
		WebDriverWait wait = new WebDriverWait(m_browser, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(m_by));
		
	}
	
	/**
	 * Waits until this object is present or throws and exception if it reaches specified timeout
	 * @param timeout the time to wait for the object to be present before throwing an exception
	 */
	public void waitUntilPresent(Integer timeout){
		waitUntilPresent(timeout.longValue());
	}
	
	/**
	 * Waits until this object is clickable or throws and exception if it reaches the default timeout
	 * for the object
	 */
	public void waitUntilClickable(){
		waitUntilClickable(m_timeout);
	}
	
	/**
	 * Waits until this object is clickable or throws and exception if it reaches specified timeout
	 * @param timeout the time to wait for the object to be present before throwing an exception
	 */
	public void waitUntilClickable(Long timeout){
		WebDriverWait wait = new WebDriverWait(m_browser, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(m_by));
	}
	
	/**
	 * Waits until this object is clickable or throws and exception if it reaches specified timeout
	 * @param timeout the time to wait for the object to be present before throwing an exception
	 */
	public void waitUntilClickable(Integer timeout){
		waitUntilClickable(timeout.longValue());
	}

	/**
	 * Waits until this object is present
	 * @return true if the object is present before the default timeout of the object,
	 * false if it is not present before the default timeout of the object.
	 */
	public boolean waitAndCheckPresent(){
		return waitAndCheckPresent(m_timeout);
	}
	
	/**
	 * Waits until this object is present
	 * @return true if the object is present before the specified timeout,
	 * false if it is not present before the specified timeout.
	 */
	public boolean waitAndCheckPresent(Long timeout){
		try{
			WebDriverWait wait = new WebDriverWait(m_browser, timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(m_by));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Waits until this object is present
	 * @return true if the object is present before the specified timeout,
	 * false if it is not present before the specified timeout.
	 */
	public boolean waitAndCheckPresent(Integer timeout){
		return waitAndCheckPresent(timeout.longValue());
	}
	
	/**
	 * Waits until the specified text is present in the object, or the default timeout
	 * for the object is reached.
	 * @param expectedText the text to wait for to be present in the object
	 */
	public void waitUntilTextIsPresent(String expectedText){
		waitUntilTextIsPresent(expectedText, m_timeout);
	}
	
	/**
	 * Waits until the specified text is present in the object, or the specified timeout is
	 * reached.
	 * @param expectedText the text to wait for to be present in the object
	 */
	public void waitUntilTextIsPresent(String expectedText, Long timeout){
		WebDriverWait wait = new WebDriverWait(m_browser, timeout);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(getBy(), expectedText));
	}
	
	/**
	 * Waits until the specified text is present in the object, or the specified timeout is
	 * reached.
	 * @param expectedText the text to wait for to be present in the object
	 */
	public void waitUntilTextIsPresent(String expectedText, Integer timeout){
		waitUntilTextIsPresent(expectedText, timeout.longValue());
	}	
	
	/**
	 * Waits until the object is visible, or the default timeout for the object is reached.
	 */
	public void waitUntilVisible(){
		waitUntilVisible(m_timeout);
	}
	
	/**
	 * Waits until the object is visible, or the specified timeout for the object is reached.
	 * @param timeout the time to wait for the object to be visible
	 */
	public void waitUntilVisible(Long timeout){
		WebDriverWait wait = new WebDriverWait(m_browser, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getBy()));
	}
	
	/**
	 * Waits until the object is visible, or the specified timeout for the object is reached.
	 * @param timeout the time to wait for the object to be visible
	 */
	public void waitUntilVisible(Integer timeout){
		waitUntilVisible(timeout.longValue());
	}
	
	/**
	 * Waits until the object is visible, or the default timeout for the object is reached.
	 * @return true if the object is visible before the default timeout,
	 * false if the object is not visible before the default timeout
	 */
	public boolean waitAndCheckVisible(){
		return waitAndCheckVisible(m_timeout);
	}
	
	/**
	 * Waits until the object is visible, or the specified timeout for the object is reached.
	 * @param timeout the timeout value to wait for the object to be visible
	 * @return true if the object is visible before the specified timeout,
	 * false if the object is not visible before the specified timeout
	 */
	public boolean waitAndCheckVisible(Long timeout){
		try{
			waitUntilVisible(timeout);
			return true;
		}
		catch(Throwable t){
			return false;
		}
	}
		
	/**
	 * Waits until the object is visible, or the specified timeout for the object is reached.
	 * @param timeout the timeout value to wait for the object to be visible
	 * @return true if the object is visible before the specified timeout,
	 * false if the object is not visible before the specified timeout
	 */
	public boolean waitAndCheckVisible(Integer timeout){
		return waitAndCheckVisible(timeout.longValue());
	}
	
	/**
	 * Waits until the object is no longer visible, or the default timeout for the object is reached. 
	 */
	public void waitUntilInvisible(){
		waitUntilInvisible(m_timeout);
	}
	
	/**
	 * Waits until the object is no longer visible, or the specified timeout for the object is reached.
	 * @param timeout the timeout value to wait for the object to disappear
	 */
	public void waitUntilInvisible(Long timeout){
		WebDriverWait wait = new WebDriverWait(m_browser, timeout);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy()));
	}
	
	/**
	 *  Waits until the object is no longer visible, or the specified timeout for the object is reached.
	 * @param timeout the timeout value to wait for the object to disappear
	 */
	public void waitUntilInvisible(Integer timeout){
		waitUntilInvisible(timeout.longValue());
	}
	
	/**
	 * Waits until the object is no longer visible, or the specified timeout for the object is reached.
	 * @param timeout the timeout value to wait for the object to disappear
	 * @return true if the object disappears before the specified timeout,
	 * false if the object is still visible before the specified timeout
	 */
	public boolean waitAndCheckInvisible(Long timeout){
		try{
			waitUntilInvisible(timeout);
			return true;
		}
		catch(Throwable t){
			return false;
		}
	}
	
	/**
	 * Waits until the object is no longer visible, or the default timeout for the object is reached.
	 * @return true if the object disappears before the default timeout,
	 * false if the object is still visible before the default timeout
	 */
	public boolean waitAndCheckInvisible(){
		return waitAndCheckInvisible(m_timeout);
	}
	
	/**
	 * Waits until the object is no longer visible, or the specified timeout for the object is reached.
	 * @param timeout the timeout value to wait for the object to disappear
	 * @return true if the object disappears before the specified timeout,
	 * false if the object is still visible before the specified timeout
	 */
	public boolean waitAndCheckInvisible(Integer timeout){
		return waitAndCheckInvisible(timeout.longValue());
	}
	
	/**
	 * Deselects all options in a select object.
	 */
	public void deselectAll(){
		Select select = new Select(m_browser.findElement(m_by));
		select.deselectAll();
	}
	
	/**
	 * Deselects the specified option for the select object
	 * @param index the index of the option in the select object to deselect
	 */
	public void	deselectByIndex(int index){
		Select select = new Select(m_browser.findElement(m_by));
		select.deselectByIndex(index);
	}
	
	/**
	 * Deselects the specified option for the select object
	 * @param value the value of the option in the select object to deselect
	 */
	public void	deselectByValue(String value){
		Select select = new Select(m_browser.findElement(m_by));
		select.deselectByValue(value);
	}
	
	/**
	 * Deselects the specified option for the select object
	 * @param text the text of the option in the select object to deselect
	 */
	public void	deselectByVisibleText(String text){
		Select select = new Select(m_browser.findElement(m_by));
		select.deselectByVisibleText(text);
	}
	
	/**
	 * Gets a list of selected options from the current select object
	 * @return a list of selected options from the current select object
	 */
	public List<WebElement> getAllSelectedOptions(){
		Select select = new Select(m_browser.findElement(m_by));
		return select.getAllSelectedOptions();
	}
	
	/**
	 * Gets the first selected option from the current select object
	 * @return the first selected option form the current select object
	 */
	public WebElement getFirstSelectedOption(){
		Select select = new Select(m_browser.findElement(m_by));
		return select.getFirstSelectedOption();
	}
	
	/**
	 * Gets a list of all the options from the current select object 
	 * @return a list of all the options from the current select object 
	 */
	public List<WebElement> getOptions(){
		Select select = new Select(m_browser.findElement(m_by));
		return select.getOptions();
	}
	
	/**
	 * Checks if the current select object supports multi-select
	 * @return true if it supports multi-select, false if not
	 */
	public boolean isMultiple(){
		Select select = new Select(m_browser.findElement(m_by));
		return select.isMultiple();
	}
	
	/**
	 * Selects an option in the current select object by the index of the option
	 * @param index the index of the option to select.
	 */
	public void selectByIndex(int index){
		Select select = new Select(m_browser.findElement(m_by));
		select.selectByIndex(index);
	}
	
	/**
	 * Select an option in the current select object by the value of the option
	 * @param value the value fo the option to select.
	 */
	public void selectByValue(String value){
		Select select = new Select(m_browser.findElement(m_by));
		select.selectByValue(value);
	}
	
	/**
	 * Select an option in the current select object by the text of the option.
	 * @param text the text of the option to select.
	 */
	public void	selectByVisibleText(String text){
		Select select = new Select(m_browser.findElement(m_by));
		select.selectByVisibleText(text);
	}
	
	/**
	 * Executes the specified javascript with the specified arguments... note
	 * the WebElement will automatically be added to the args.
	 * @param script the script to execute
	 * @param args the arguments to pass into the script - does not need the web element, since it will be included.
	 * @return the return value from the script that is executed
	 */
	public Object executeScript(String script, Object... args){
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) m_browser;
		Object[] scriptArgs = getArgs(args);
		return javaScriptExecutor.executeScript(script, scriptArgs);
	}
	
	/**
	 * Executes the specified asyn javascript with the specified arguments... note
	 * the WebElement will automatically be added to the args.
	 * @param script the script to execute
	 * @param args the arguments to pass into the script - does not need the web element, since it will be included.
	 * @return the return value from the script that is executed
	 */
	public Object executeAsyncScript(String script, Object... args){
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) m_browser;
		Object[] scriptArgs = getArgs(args);
		return javaScriptExecutor.executeAsyncScript(script, scriptArgs);
	}
	
	/**
	 * Takes the passed in args and adds the WebElement object to it.
	 * @param args the arguments that you want to add the web element to.
	 * @return an array of the arguments, plus the current objects WebElement
	 */
	private Object[] getArgs(Object... args){
		List<Object> argList = new ArrayList<Object>();
		argList.add(m_browser.findElement(m_by));
		if(args.length>0){
			return argList.toArray(args);
		}
		else{
			return argList.toArray();
		}
	}
	
	/**
	 * Creates an action object using the browser defined in this object
	 * @return an action object using the browser defined in this object
	 */
	private Actions action(){
		return new Actions(m_browser);
	}

	/**
	 * Capture the screenshot and store it in the specified location.
	 * For WebDriver extending TakesScreenshot,
	 *  this makes a best effort depending on the browser to return the following in order of preference:
	 *  	Entire page
	 *  	Current window
	 *  	Visible portion of the current frame
	 *  	The screenshot of the entire display containing the browser
	 *  For WebElement extending TakesScreenshot,
	 *   this makes a best effort depending on the browser to return the following in order of preference:
	 *    - The entire content of the HTML element
	 *    - The visible portion of the HTML element
	 */
	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return m_browser.findElement(m_by).getScreenshotAs(target);
	}

	/**
	 * Gets the location and size of the rendered element
	 */
	@Override
	public Rectangle getRect() {
		return m_browser.findElement(m_by).getRect();
	}
	
}

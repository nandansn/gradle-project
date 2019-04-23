package DESKTOP;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import supportlibraries.Executor;
import supportlibraries.WaitTool;
import utilities.Functions;
import utilities.Results;

/**
 * This class is used to validate following functionality under My Verizon Homepage
 * a. MYPMNMD000042 ao suspend service single line
 * b. MYPMNMD000044 ao suspend service multi line
 * c. MYPMNMD000045 ao reconnect service multi line
 * Created By: shetisu
 * Created Date: Jul 17, 2017
 * @author Sudhakar Shetiya(shetisu) [Sudhakar.Shetiya@VerizonWireless.com] (shetisu)
 */

public class MyVerizonSuspendReconnectService implements POM.ObjectRepository_OneDigitalPostpaid {

	/*-------------------------------PAGE NAME CONSTANTS -------------------------------------------------------------------*/

	public static final String MY_DEVICES = "My Devices";

	public static final String SUSPEND_RECONNECT_SERVICE = "Suspend or Reconnect Service";

	/*-------------------------------INSTANCE VARIABLES -------------------------------------------------------------------*/

	String strUser = null;
	String strPIN = null;
	String strSecretQuestion = null;
	String strApplicationName = null;
	String strBillingOption = null;
	String strSuspendService = null;
	String strReconnectService = null;
	String strZipCode = null;
	String strPhoneLines = null;

	/*-------------------------------STATIC XPATH --------------------------------------------------------------------------*/

	public static By suspendPopUpHeader = By.id("ui-dialog-title-MyV20Overlay");

	//public static By radioOtherReason = By.id("reasonDropdownSV");
	public static By radioOtherReason = By.xpath(".//input[@id='radio2' and @value='other']");

	//public static By radioKeepBilling = By.id("withBilling");
	public static By radioKeepBilling = By.xpath(".//input[@id='radio3' and @value='withBilling']");

	//public static By suspendSelectedLine = By.xpath(".//*[@id='suspendButton']/span");
	public static By suspendSelectedLine = By.xpath(".//button[@class='btn btn--round' and contains(text(),'Suspend')]");

	public static By btn_closeSuspendWindow = By.xpath(".//*[@id='processSuspendServiceOnly']//span[contains(text(), 'Close')]");

	public static By reconnectPopUpHeader = By.id("ui-dialog-title-MyV20Overlay");

	//public static By radioReconnectService = By.xpath(".//*[@id='radioRsnCtr']//input[@value = 'resume']");
	
	public static By radioReconnectService = By.xpath(".//input[@id='radio0']");

	public static By reconnectSelectedLine = By.xpath(".//*[@id='submitResume']/span");

	public static By btn_closeReconnectWindow = By.xpath(".//*[@id='processResumeService']//span[contains(text(), 'Close')]");

	/*-------------------------------Dynamic XPATH --------------------------------------------------------------------------*/

	//String strMDNPlaceholder = ".//*[@id='suspendResumeDevice-PLACEHOLDER']//span";
	String strMDNPlaceholder = ".//p[contains(text(),'PLACEHOLDER')]/../following-sibling::div/button";

	/**
	 * This method is used to test functinality under My Devices --> Suspend or Reconnect Service
	 * This method should be accessed primarily from FRAMEWORK code as it uses data from the portal
	 * @throws Exception
	 */
	public void aMyVerizonSuspendReconnectService(WebDriver driver) throws Exception {
		//Maximizes the browser window
		driver.manage().window().maximize();

		//Get the values from the database
		this.strUser = TestData.GetTestData.getActionData("D_USER_NAME");
		this.strPIN = TestData.GetTestData.getActionData("D_PIN");
		this.strSecretQuestion = TestData.GetTestData.getActionData("S_SECRET_QUESTION_ANSWER");
		this.strApplicationName = TestData.GetTestData.getActionData("F_APPLICATION_NAME");
		this.strBillingOption = TestData.GetTestData.getActionData("F_BILLING_OPTION");
		this.strSuspendService = TestData.GetTestData.getActionData("F_SUSPEND_SERVICE");
		this.strReconnectService = TestData.GetTestData.getActionData("F_RECONNECT_SERVICE");
		this.strZipCode = TestData.GetTestData.getActionData("S_ZIPCODE");
		this.strPhoneLines = TestData.GetTestData.getActionData("F_PHONE");

		if (Functions.exist(oneDHomePageXp)) {
			Results.info("1D My Verizon Page is displayed");
			OneDigitalDesktop.oneDigitalGlobalNavigation(driver, "Devices", "Suspend or reconnect");
		}

		//Navigate to My Devices --> Suspend or Reconnect Service
		//		if (!Executor.isTestUsingTabletDevice() && !Executor.isTableViewtTest()) {
		//			MyVerizonNavigation.navigateMyVerizonMenu(driver, MY_DEVICES, SUSPEND_RECONNECT_SERVICE);
		//		}
		//Variable strPhoneLines contains list of phone numbers set in the Portal
		String[] delimitedPhonesLines = StringUtils.split(this.strPhoneLines, "|");

		for (String phoneLine : delimitedPhonesLines) {

			//Remove the "-" character from the SSN sent from the portal
			//String mdn = StringUtils.remove(phoneLine, "-");
			String mdn = StringUtils.replace(phoneLine, "-", ".");
			Results.info("Test Case is working on MDN: " + mdn);
			String strMDNXPath = this.strMDNPlaceholder.replace("PLACEHOLDER", mdn);
			By suspendMDNXPath = By.xpath(strMDNXPath);

			//Click on Suspend Service Button corresponding to the MDN
			WebElement suspendResumeElem = WaitTool.waitForElementPresent(driver, suspendMDNXPath, 20);
			//this.strSuspendService = "suspend";
			this.strSuspendService = "Other Reason";
			this.strReconnectService = "";
			//if (suspendResumeElem.getText().equals("Suspend Service") && this.strSuspendService != null && this.strSuspendService != "") {
			if (suspendResumeElem.getAttribute("analyticstrack").equals("suspend-selectdevice") && this.strSuspendService != null && this.strSuspendService != "") {
				suspendResumeElem.click();

				//Invoke suspendService method to handle workflow
				suspendService(driver, mdn);

			} else if (suspendResumeElem.getAttribute("analyticstrack").equals("reconnect-selectdevice") && this.strReconnectService != null && this.strReconnectService != "") {
				suspendResumeElem.click();
				//Invoke reconnectService method to handle workflow
				reconnectService(driver, mdn);
			} else if (this.strSuspendService != null && this.strSuspendService != "") {
				Results.pass("Line \"" + mdn + "\" is already suspended");
			} else if (this.strReconnectService != null && this.strReconnectService != "") {
				Results.pass("Line \"" + mdn + "\" is already reconnected");
			}
		}

	}

	/**
	 * This method is used to suspend Service for an Active Line
	 * @param driver WebDriver object
	 * @param MDN The line# to be suspended
	 * @throws InterruptedException
	 */
	/*public void suspendService(WebDriver driver, String MDN) throws InterruptedException {
		//Suspend Service POP Handling
		WebElement suspendPopupElem = WaitTool.waitForElementPresent(driver, suspendPopUpHeader, 10);
		if (suspendPopupElem.getText().startsWith("Suspend")) {
			Results.pass("Displaying Suspend Your Wireless Service Popup");
		} else {
			Results.fail("Suspend Your Wireless Service Popup is NOT displayed");
		}
		if (this.strSuspendService.equals("Other Reason")) {
			//Click on Other Reason radio button
			WaitTool.waitForElementPresent(driver, radioOtherReason, 10).click();
			Results.pass("Selected Radio button: Other");
		}
	
		if (this.strBillingOption.equals("Keep billing")) {
			//Click on Keep Billing Option
			WaitTool.waitForElementPresent(driver, radioKeepBilling, 10).click();
			Results.pass("Selected Keep Billing me option.");
		}
	
		//Click on the Suspend Selected Line Button
		WaitTool.waitForElementPresent(driver, suspendSelectedLine, 10).click();
		Results.pass("Clicked on Suspend Selected Line Button");
	
		//Click on Close Button
		WaitTool.waitForElementPresent(driver, btn_closeSuspendWindow, 20).click();
		Results.pass("Reached Confirmation Page for Line Suspension");
	
	}*/

	public void suspendService(WebDriver driver, String MDN) throws InterruptedException {
		//Suspend Service POP Handling
		/*WebElement suspendPopupElem = WaitTool.waitForElementPresent(driver, suspendPopUpHeader, 10);
		if (suspendPopupElem.getText().startsWith("Suspend")) {
			Results.pass("Displaying Suspend Your Wireless Service Popup");
		} else {
			Results.fail("Suspend Your Wireless Service Popup is NOT displayed");
		}*/
		if (this.strSuspendService.equals("Other Reason")) {
			//Click on Other Reason radio button
			WaitTool.waitForElementPresent(driver, radioOtherReason, 10).click();
			Results.pass("Selected Radio button: Other");
			By nextButton = By.xpath(".//button[@class='btn btn--round' and text()='Next']");
			WaitTool.waitForElementPresent(driver, nextButton, 10).click();
			Results.pass("Clicked button: Next");
		}

		if (this.strBillingOption.equals("Keep billing")) {
			//Click on Keep Billing Option
			WaitTool.waitForElementPresent(driver, radioKeepBilling, 10).click();
			Results.pass("Selected Keep Billing me option.");
		}

		//Click on the Suspend Selected Line Button
		WaitTool.waitForElementPresent(driver, suspendSelectedLine, 10).click();
		Results.pass("Clicked on Suspend Selected Line Button");

		//Click on Close Button
		//WaitTool.waitForElementPresent(driver, btn_closeSuspendWindow, 20).click();
		//Results.pass("Reached Confirmation Page for Line Suspension");
		By selectAnotherLine = By.xpath(
				".//div[@class='nextStep-btn full-ctas hidden-xs-up hidden-sm-up visible-lg visible-md']//a[@class='btn btn--round-invert'][contains(text(),'Select another line')]");

		WaitTool.waitForElementPresent(driver, selectAnotherLine, 10).click();
		Results.pass("Clicked on Select another line button");
	}

	/**
	 * This method is used to reconnect service for a suspended line
	 * @param driver WebDriver object
	 * @param MDN The line# to be reconnected
	 * @throws InterruptedException
	 */
	public void reconnectService(WebDriver driver, String MDN) throws InterruptedException {
		//Reconnect Service POP Handling
		WebElement reconnectPopupElem = WaitTool.waitForElementPresent(driver, reconnectPopUpHeader, 10);
		/*if (reconnectPopupElem.getText().startsWith("Reconnect")) {
			Results.pass("Displaying Reconnect Your Verizon Wireless Service Popup");
		} else {
			Results.fail("Reconnect Your Verizon Wireless Service Popup is NOT displayed");
		}*/

		if (this.strReconnectService.equals("Current")) {
			//Click on Reconnect Service Radio button
			WaitTool.waitForElementPresent(driver, radioReconnectService, 10).click();
			Results.pass("Selected Reconnect Service Radio Button");
		}

		// Click on Reconnect Service to this line button
		WaitTool.waitForElementPresent(driver, reconnectSelectedLine, 10).click();

		//Click on Close Button
		WaitTool.waitForElementPresent(driver, btn_closeReconnectWindow, 20).click();
		Results.pass("Reached Confirmation Page for Resume Service");
	}
}

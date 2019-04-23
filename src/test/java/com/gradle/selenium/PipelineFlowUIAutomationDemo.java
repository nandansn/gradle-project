package com.gradle.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PipelineFlowUIAutomationDemo {

	static WebDriver driver = null;
	String url = "http://localhost:8080/dataflow/create";

	String javaScript = "(function (draggable, droppable) {\n"
			+ "var __extends = (this && this.__extends) || function (d, b) {\n" + "for (var p in b)\n"
			+ "if (b.hasOwnProperty(p)) d[p] = b[p];\n" + "function __() {\n" + "this.constructor = d;\n" + "}\n"
			+ "d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());\n" + "};\n"
			+ "var dnd;\n" + "(function (dnd) {\n" + "'use strict';\n" + "function simulate(draggable, droppable) {\n"
			+ "var store = new DragDataStore();\n" + "store.mode = 'readwrite';\n"
			+ "var dataTransfer = new DataTransfer(store);\n"
			+ "var dragstartEvent = createEventWithDataTransfer('dragstart', dataTransfer);\n"
			+ "draggable.dispatchEvent(dragstartEvent);\n" + "store.mode = 'readonly';\n"
			+ "var dragOverEvent = createEventWithDataTransfer('dragover', dataTransfer);\n"
			+ "droppable.dispatchEvent(dragOverEvent);\n"
			+ "var dropEvent = createEventWithDataTransfer('drop', dataTransfer);\n" + "dropEvent.clientX = 300;\n"
			+ "dropEvent.clientY = 100;\n" + "dropEvent.pageX = PAGE_X_LOCATION;\n"
			+ "dropEvent.pageY = PAGE_Y_LOCATION;\n" + "droppable.dispatchEvent(dropEvent);\n"
			+ "store.mode = 'protected';\n"
			+ "var dragendEvent = createEventWithDataTransfer('dragend', dataTransfer);\n"
			+ "draggable.dispatchEvent(dragendEvent);\n" + "}\n" + "dnd.simulate = simulate;\n"
			+ "function createEventWithDataTransfer(type, dataTransfer) {\n"
			+ "var event = document.createEvent('CustomEvent');\n" + "event.initCustomEvent(type, true, true, null);\n"
			+ "event.dataTransfer = dataTransfer;\n" + "return event;\n" + "}\n" + "var DataTransfer = (function () {\n"
			+ "function DataTransfer(store) {\n" + "this.store = store;\n" + "this.typeTable = {};\n"
			+ "this.effectAllowed = 'uninitialized';\n" + "this.types = [];\n" + "this.files = new FileList();\n"
			+ "}\n" + "DataTransfer.prototype.setDragImage = function (element, x, y) {\n" + "};\n"
			+ "DataTransfer.prototype.getData = function (format) {\n" + "if (this.store.mode === 'protected') {\n"
			+ "return '';\n" + "}\n" + "format = format.toLowerCase();\n" + "var convertToUrl = false;\n"
			+ "if (format === 'text') {\n" + "format = 'text/plain';\n" + "} else if (format === 'url') {\n"
			+ "format = 'text/uri-list';\n" + "convertToUrl = true;\n" + "}\n" + "if (!(format in this.typeTable)) {\n"
			+ "return '';\n" + "}\n" + "var result = this.typeTable[format];\n" + "if (convertToUrl) {\n"
			+ "result = parseTextUriList(result)[0] || '';\n" + "}\n" + "return result;\n" + "};\n"
			+ "DataTransfer.prototype.setData = function (format, data) {\n" + "if (!this.store) {\n" + "return;\n"
			+ "}\n" + "if (this.store.mode !== 'readwrite') {\n" + "return;\n" + "}\n"
			+ "format = format.toLowerCase();\n" + "if (format === 'text') {\n" + "format = 'text/plain';\n"
			+ "} else if (format === 'url') {\n" + "format = 'text/uri-list';\n" + "}\n"
			+ "this.typeTable[format] = data;\n" + "this.types = Object.keys(this.typeTable);\n" + "};\n"
			+ "DataTransfer.prototype.clearData = function (format) {\n" + "var _this = this;\n"
			+ "if (!this.store) {\n" + "return;\n" + "}\n" + "if (this.store.mode !== 'readwrite') {\n" + "return;\n"
			+ "}\n" + "if (typeof format === 'undefined') {\n" + "this.types.filter(function (type) {\n"
			+ "return type !== 'Files';\n" + "})\n" + ".forEach(function (type) {\n" + "return _this.clearData(type);\n"
			+ "});\n" + "return;\n" + "}\n" + "format = format.toLowerCase();\n" + "if (format === 'text') {\n"
			+ "format = 'text/plain';\n" + "} else if (format === 'url') {\n" + "format = 'text/uri-list';\n" + "}\n"
			+ "delete this.typeTable[format];\n" + "this.types = Object.keys(this.typeTable);\n" + "};\n"
			+ "return DataTransfer;\n" + "}());\n" + "dnd.DataTransfer = DataTransfer;\n"
			+ "var FileList = (function () {\n" + "function FileList() {\n" + "this.length = 0;\n" + "}\n"
			+ "FileList.prototype.item = function (index) {\n" + "return null;\n" + "};\n" + "return FileList;\n"
			+ "}());\n" + "dnd.FileList = FileList;\n" + "var DragDataStore = (function () {\n"
			+ "function DragDataStore() {}\n" + "return DragDataStore;\n" + "}());\n"
			+ "var DataTransferItemList = (function () {\n" + "function DataTransferItemList(store) {\n"
			+ "this.store = store;\n" + "this.items = [];\n" + "this.typeTable = {};\n" + "this.length = 0;\n" + "}\n"
			+ "DataTransferItemList.prototype.remove = function (idx) {\n" + "if (this.store.mode !== 'readwrite') {\n"
			+ "throw InvalidStateError.createByDefaultMessage();\n" + "}\n"
			+ "var removed = this.items.splice(idx, 1)[0];\n" + "this.syncInternal();\n" + "if (removed) {\n"
			+ "delete this.typeTable[removed.type];\n" + "}\n" + "};\n"
			+ "DataTransferItemList.prototype.clear = function () {\n" + "if (this.store.mode !== 'readwrite') {\n"
			+ "throw InvalidStateError.createByDefaultMessage();\n" + "}\n" + "this.items = [];\n"
			+ "this.syncInternal();\n" + "};\n" + "DataTransferItemList.prototype.add = function (data, type) {\n"
			+ "if (this.store.mode !== 'readwrite') {\n" + "return null;\n" + "}\n"
			+ "if (typeof data === 'string') {\n" + "var typeLowerCase = type.toLowerCase();\n"
			+ "if (this.typeTable[typeLowerCase]) {\n" + "throw NotSupportedError.createByDefaultMessage();\n" + "}\n"
			+ "var stringItem = DataTransferItem.createForString(data, typeLowerCase, this.store);\n"
			+ "this.items.push(stringItem);\n" + "this.typeTable[typeLowerCase] = true;\n" + "} else {\n"
			+ "var fileItem = DataTransferItem.createForFile(data, this.store);\n" + "this.items.push(fileItem);\n"
			+ "}\n" + "this.syncInternal();\n" + "};\n"
			+ "DataTransferItemList.prototype.syncInternal = function () {\n" + "var _this = this;\n"
			+ "for (var i = 0; i < this.length; i++) {\n" + "delete this[i];\n" + "}\n"
			+ "this.items.forEach(function (item, j) {\n" + "_this[j] = item;\n" + "});\n"
			+ "this.length = this.items.length;\n" + "};\n" + "return DataTransferItemList;\n" + "}());\n"
			+ "dnd.DataTransferItemList = DataTransferItemList;\n" + "var DataTransferItem = (function () {\n"
			+ "function DataTransferItem(data, kind, typeLowerCase, store) {\n" + "this.data = data;\n"
			+ "this.store = store;\n" + "this.type = typeLowerCase;\n" + "this.kind = kind;\n" + "}\n"
			+ "DataTransferItem.prototype.getAsString = function (callback) {\n" + "var _this = this;\n"
			+ "if (callback) {\n" + "return;\n" + "}\n" + "if (this.store.mode !== 'readwrite') {\n" + "return;\n"
			+ "}\n" + "if (this.kind !== 'string') {\n" + "return;\n" + "}\n" + "setTimeout(function () {\n"
			+ "callback(_this.data);\n" + "}, 0);\n" + "};\n" + "DataTransferItem.prototype.getAsFile = function () {\n"
			+ "if (this.store.mode !== 'readwrite') {\n" + "return null;\n" + "}\n" + "if (this.kind !== 'string') {\n"
			+ "return null;\n" + "}\n" + "return this.data;\n" + "};\n"
			+ "DataTransferItem.createForString = function (data, type, store) {\n"
			+ "return new DataTransferItem(data, 'string', type, store);\n" + "};\n"
			+ "DataTransferItem.createForFile = function (data, store) {\n"
			+ "return new DataTransferItem(data, 'file', null, store);\n" + "};\n" + "return DataTransferItem;\n"
			+ "}());\n" + "var InvalidStateError = (function (_super) {\n" + "__extends(InvalidStateError, _super);\n"
			+ "\n" + "function InvalidStateError(message) {\n" + "_super.call(this, message);\n"
			+ "this.message = message;\n" + "this.name = 'InvalidStateError';\n" + "}\n"
			+ "InvalidStateError.createByDefaultMessage = function () {\n"
			+ "return new InvalidStateError('The object is in an invalid state');\n" + "};\n"
			+ "return InvalidStateError;\n" + "}(Error));\n" + "var NotSupportedError = (function (_super) {\n"
			+ "__extends(NotSupportedError, _super);\n" + "\n" + "function NotSupportedError(message) {\n"
			+ "_super.call(this, message);\n" + "this.message = message;\n" + "this.name = 'NotSupportedError';\n"
			+ "}\n" + "NotSupportedError.createByDefaultMessage = function () {\n"
			+ "return new InvalidStateError('The operation is not supported');\n" + "};\n"
			+ "return NotSupportedError;\n" + "}(Error));\n" + "function parseTextUriList(textUriList) {\n"
			+ "textUriList = textUriList.replace(/\\r\\n$/, '');\n" + "if (textUriList === '') {\n" + "return [];\n"
			+ "}\n" + "return textUriList.split(/\\r\\n/).filter(function (line) {\n" + "return line[0] !== '#';\n"
			+ "});\n" + "}\n" + "dnd.parseTextUriList = parseTextUriList;;\n" + "})(dnd || (dnd = {}));\n"
			+ "dnd.simulate(draggable, droppable);\n"
			+ "})(document.querySelector('#SOURCE_NODE_LOCATION'), document.querySelector('#dataFlowCanvas'));";

	@BeforeMethod
	public void openApp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\nrangasa.ORADEV\\chromedriver\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(1);

		driver.findElement(By.xpath("//div[contains(@class,'dataFlowToolbar')]//img[contains(@src,'fullscreen')]"))
				.click();

		TimeUnit.SECONDS.sleep(1);

	}

	@Test(enabled = true)
	public void testFlow() throws InterruptedException {
		
		/*testSourceContextMenu();

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		String src0Realign = "document.cy.elements()[0].position().x=186.66666666666666;document.cy.elements()[0].position().y=88.00000000000001;document.cy.elements()[0].trigger('position');document.cy.elements()[0].trigger('tap');";
		js1.executeScript(src0Realign);

		TimeUnit.SECONDS.sleep(1);
		
		
		js1 = (JavascriptExecutor) driver;
		String tgt1Realign = "document.cy.elements()[1].position().x=1233.9687466666667;document.cy.elements()[1].position().y=340;document.cy.elements()[1].trigger('position');document.cy.elements()[1].trigger('tap');";
		js1.executeScript(tgt1Realign);*/

		TimeUnit.SECONDS.sleep(1);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String srcDrag = javaScript.replaceFirst("PAGE_X_LOCATION", "380").replace("PAGE_Y_LOCATION", "340");
		srcDrag = srcDrag.replaceFirst("SOURCE_NODE_LOCATION", "Source Operator");
		js.executeScript(srcDrag);

		TimeUnit.SECONDS.sleep(2);

		js = (JavascriptExecutor) driver;
		String joinerDrag = javaScript.replaceFirst("PAGE_X_LOCATION", "490").replace("PAGE_Y_LOCATION", "340");
		joinerDrag = joinerDrag.replaceFirst("SOURCE_NODE_LOCATION", "Joiner");
		js.executeScript(joinerDrag);
		
		TimeUnit.SECONDS.sleep(1);

		js = (JavascriptExecutor) driver;
		String filterDrag = javaScript.replaceFirst("PAGE_X_LOCATION", "590").replace("PAGE_Y_LOCATION", "340");
		filterDrag = filterDrag.replaceFirst("SOURCE_NODE_LOCATION", "Filter");
		js.executeScript(filterDrag);
		
		TimeUnit.SECONDS.sleep(1);

		js = (JavascriptExecutor) driver;
		String srcDrag_3 = javaScript.replaceFirst("PAGE_X_LOCATION", "690").replace("PAGE_Y_LOCATION", "440");
		srcDrag_3 = srcDrag_3.replaceFirst("SOURCE_NODE_LOCATION", "Source");
		js.executeScript(srcDrag_3);
		
		TimeUnit.SECONDS.sleep(1);

		js = (JavascriptExecutor) driver;
		String exprDrag = javaScript.replaceFirst("PAGE_X_LOCATION", "850").replace("PAGE_Y_LOCATION", "340");
		exprDrag = exprDrag.replaceFirst("SOURCE_NODE_LOCATION", "Projection");
		js.executeScript(exprDrag);
		
		TimeUnit.SECONDS.sleep(1);

		js = (JavascriptExecutor) driver;
		String joinerDrag_2 = javaScript.replaceFirst("PAGE_X_LOCATION", "730").replace("PAGE_Y_LOCATION", "340");
		joinerDrag_2 = joinerDrag_2.replaceFirst("SOURCE_NODE_LOCATION", "Joiner");
		js.executeScript(joinerDrag_2);
		
		TimeUnit.SECONDS.sleep(1);

		js = (JavascriptExecutor) driver;
		String joinerDrag_3 = javaScript.replaceFirst("PAGE_X_LOCATION", "950").replace("PAGE_Y_LOCATION", "340");
		joinerDrag_3 = joinerDrag_3.replaceFirst("SOURCE_NODE_LOCATION", "Joiner");
		js.executeScript(joinerDrag_3);
		
		TimeUnit.SECONDS.sleep(1);

		js = (JavascriptExecutor) driver;
		String sourceDrag_4 = javaScript.replaceFirst("PAGE_X_LOCATION", "910").replace("PAGE_Y_LOCATION", "440");
		sourceDrag_4 = sourceDrag_4.replaceFirst("SOURCE_NODE_LOCATION", "Source");
		js.executeScript(sourceDrag_4);
		
		TimeUnit.SECONDS.sleep(1);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeSrc1toJoiner1 = "document.cy.add({data:{id:'1',source:document.cy.elements()[0].data().id,target:document.cy.elements()[3].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeSrc1toJoiner1);

		TimeUnit.SECONDS.sleep(1);

		String triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[0],document.cy.elements()[3],document.cy.elements()[10]]);";
		js.executeScript(triggerehComplete);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeSrc2toJoiner1 = "document.cy.add({data:{id:'2',source:document.cy.elements()[2].data().id,target:document.cy.elements()[3].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeSrc2toJoiner1);

		TimeUnit.SECONDS.sleep(1);

		triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[2],document.cy.elements()[3],document.cy.elements()[11]]);";
		js.executeScript(triggerehComplete);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeJoiner1toFilter1 = "document.cy.add({data:{id:'3',source:document.cy.elements()[3].data().id,target:document.cy.elements()[4].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeJoiner1toFilter1);

		TimeUnit.SECONDS.sleep(1);

		triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[3],document.cy.elements()[4],document.cy.elements()[12]]);";
		js.executeScript(triggerehComplete);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeFilter1toSource3 = "document.cy.add({data:{id:'4',source:document.cy.elements()[4].data().id,target:document.cy.elements()[7].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeFilter1toSource3);

		TimeUnit.SECONDS.sleep(1);

		triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[4],document.cy.elements()[7],document.cy.elements()[13]]);";
		js.executeScript(triggerehComplete);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeSource3toJoiner2 = "document.cy.add({data:{id:'5',source:document.cy.elements()[5].data().id,target:document.cy.elements()[7].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeSource3toJoiner2);

		TimeUnit.SECONDS.sleep(1);

		triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[5],document.cy.elements()[7],document.cy.elements()[14]]);";
		js.executeScript(triggerehComplete);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeJoner2toExpr1 = "document.cy.add({data:{id:'6',source:document.cy.elements()[7].data().id,target:document.cy.elements()[6].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeJoner2toExpr1);

		TimeUnit.SECONDS.sleep(1);

		triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[7],document.cy.elements()[6],document.cy.elements()[15]]);";
		js.executeScript(triggerehComplete);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeExpr1ToJoiner3 = "document.cy.add({data:{id:'7',source:document.cy.elements()[6].data().id,target:document.cy.elements()[8].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeExpr1ToJoiner3);

		TimeUnit.SECONDS.sleep(1);

		triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[6],document.cy.elements()[8],document.cy.elements()[16]]);";
		js.executeScript(triggerehComplete);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeErc41ToJoiner3 = "document.cy.add({data:{id:'8',source:document.cy.elements()[9].data().id,target:document.cy.elements()[8].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeErc41ToJoiner3);

		TimeUnit.SECONDS.sleep(1);

		triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[9],document.cy.elements()[8],document.cy.elements()[17]]);";
		js.executeScript(triggerehComplete);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeJoiner3ToTgt1 = "document.cy.add({data:{id:'9',source:document.cy.elements()[8].data().id,target:document.cy.elements()[1].data().id,group:'edges'}})";
		js.executeScript(addDummyEdgeJoiner3ToTgt1);

		TimeUnit.SECONDS.sleep(1);

		triggerehComplete = "document.cy.trigger('ehcomplete',[document.cy.elements()[8],document.cy.elements()[1],document.cy.elements()[18]]);";
		js.executeScript(triggerehComplete);

		//testSourceProperties();

		//testTargetProperties();

		//testJoinerProperties();

		//testFilterProperties();

		//testExprProperties();
		
		
		
		

		TimeUnit.SECONDS.sleep(5);

	}
	
	public static void testSourceContextMenu() throws InterruptedException{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String srcDrag = "document.cy.elements()[0].emit('cxttap', new MouseEvent('cxttap', {x:390, y:220, clientX: 390, clientY: 220})  );";
		
		js.executeScript(srcDrag);
		
		TimeUnit.SECONDS.sleep(1);
		
		driver.findElement(By.xpath("//span[contains(text(),'Insert After')]")).click();
		
		
	}

	public static void testSourceProperties() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scriptToTapSource = "document.cy.elements()[0].trigger('tap')";
		js.executeScript(scriptToTapSource);

		TimeUnit.SECONDS.sleep(1);

		// 1. Default is details, Enter Value for name.
		/*driver.findElement(By.cssSelector("#name")).clear();
		TimeUnit.SECONDS.sleep(1);
		
		js = (JavascriptExecutor) driver;
		String scriptToTypeName = "document.querySelector('#name').value = 'Test_Source_1'";
		js.executeScript(scriptToTypeName);
		TimeUnit.SECONDS.sleep(1);*/

		// 1.1 Click the Browse buttons and select the entity.
		String pathForBrowseButton = "#main div.dataFlowProps > div.propertySectionContainer > div > div:nth-child(2) > div.diVal.diText1 > button";
		driver.findElement(By.cssSelector(pathForBrowseButton)).click();

		TimeUnit.SECONDS.sleep(1);

		driver.switchTo().activeElement();

		// 1.2 Click the cancel button and close the active element.

		TimeUnit.SECONDS.sleep(1);

		String pathForCancelButton = "//div[contains(@class,'MuiDialogActions-root')]//span[contains(text(),'Cancel')]";
		driver.findElement(By.xpath(pathForCancelButton)).click();

		driver.switchTo().activeElement();

		TimeUnit.SECONDS.sleep(1);

		// 2. Click
		driver.findElement(By.xpath(" //span[contains(text(),'Attributes')]")).click();
		TimeUnit.SECONDS.sleep(1);

		// 2.1 Search some string
		driver.findElement(By.xpath("//input[@placeholder='Search…']")).sendKeys("Data Flow");
		TimeUnit.SECONDS.sleep(1);

		System.out.println(driver
				.findElements(By
						.xpath("//*[@id='main']//header//button//span[1]/*[contains(@class,'MuiSvgIcon-root') and @role='presentation']/parent::span[1]"))
				.size());

		// 2.2 Click the rules icon and check for clear rules button/link
		// displayed...

		driver.findElements(By
				.xpath("//*[@id='main']/div/main/div[2]/div/div/div[4]/div[2]/div/div/div/div/div[1]/div/header/div/div[3]/button/span[1]/*"))
				.forEach(element -> {
					if (element.getTagName().equals("svg")) {
						element.click();
					}

					// element.click();
				});

		System.out.println(driver.findElement(By.xpath("//span[contains(text(),'Clear all rules')]")).isDisplayed());
		TimeUnit.SECONDS.sleep(1);

		// 3. Click the 'Data' Tab.
		driver.findElement(By.xpath("//span[contains(@class,'MuiTab-label')]//span[contains(text(),'Data')]")).click();
		TimeUnit.SECONDS.sleep(1);

		// 3.1 Check the table contains the value, //td[@value='PU_CLERK']
		driver.findElement(By.xpath("//td[@value='PU_CLERK']")).click();
		TimeUnit.SECONDS.sleep(1);

		// 4. Click the validation tab.
		driver.findElement(By.xpath(" //span[contains(text(),'Validation')]")).click();
		TimeUnit.SECONDS.sleep(1);

		System.out.println(
				driver.findElement(By.xpath("//div[@class='diRow' and contains(text(),'DataFlow Node Validation')]"))
						.isDisplayed());
		TimeUnit.SECONDS.sleep(1);

	}

	public static void testTargetProperties() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scriptToTapSource = "document.cy.elements()[1].trigger('tap')";
		js.executeScript(scriptToTapSource);

		TimeUnit.SECONDS.sleep(1);

		driver.findElement(By.xpath("//span[contains(text(),'Details')]")).click();
		TimeUnit.SECONDS.sleep(1);

		// 1. Default is details, Enter Value for name.
		/*driver.findElement(By.cssSelector("#name")).clear();
		driver.findElement(By.cssSelector("#name")).sendKeys("Test_Target_1");
		TimeUnit.SECONDS.sleep(1);*/

		// 1.1 Click the Browse buttons and select the entity.
		String pathForBrowseButton = "#main div.dataFlowProps > div.propertySectionContainer > div > div:nth-child(2) > div.diVal.diText1 > button";
		driver.findElement(By.cssSelector(pathForBrowseButton)).click();

		TimeUnit.SECONDS.sleep(1);

		driver.switchTo().activeElement();

		// 1.2 Click the cancel button and close the active element.

		TimeUnit.SECONDS.sleep(1);

		String pathForCancelButton = "//div[contains(@class,'MuiDialogActions-root')]//span[contains(text(),'Cancel')]";
		driver.findElement(By.xpath(pathForCancelButton)).click();

		driver.switchTo().activeElement();

		TimeUnit.SECONDS.sleep(1);

		// 2. Click
		/*
		 * driver.findElement(By.xpath(" //span[contains(text(),'Attributes')]")
		 * ).click(); TimeUnit.SECONDS.sleep(1);
		 * 
		 * //driver.manage().window().maximize();
		 * 
		 * // 2.1 Search some string
		 * driver.findElement(By.xpath("//input[@placeholder='Search…']")).
		 * sendKeys("Data Flow"); TimeUnit.SECONDS.sleep(1);
		 * 
		 * System.out.println(driver .findElements(By
		 * .xpath("//*[@id='main']//header//button//span[1]/*[contains(@class,'MuiSvgIcon-root') and @role='presentation']/parent::span[1]"
		 * )) .size());
		 * 
		 * driver.findElements(By .xpath(
		 * "//*[@id='main']/div/main/div[2]/div/div/div[4]/div[2]/div/div/div/div/div[1]/div/header/div/div[3]/button/span[1]/*"
		 * )) .forEach(element -> { if (element.getTagName().equals("svg")) {
		 * element.click(); }
		 * 
		 * // element.click(); });
		 * 
		 * System.out.println(driver.findElement(By.
		 * xpath("//span[contains(text(),'Clear all rules')]")).isDisplayed());
		 * TimeUnit.SECONDS.sleep(1);
		 */

		// 3. Click the 'Data' Tab.
		driver.findElement(By.xpath("//span[contains(@class,'MuiTab-label')]//span[contains(text(),'Data')]")).click();
		TimeUnit.SECONDS.sleep(1);

		// 4. Click the validation tab.
		driver.findElement(By.xpath(" //span[contains(text(),'Validation')]")).click();
		TimeUnit.SECONDS.sleep(1);

		// 4. Click the validation tab.
		driver.findElement(By.xpath(" //span[contains(text(),'Map')]")).click();
		TimeUnit.SECONDS.sleep(1);

	}

	public static void testJoinerProperties() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scriptToTapSource = "document.cy.elements()[3].trigger('tap')";
		js.executeScript(scriptToTapSource);
		
		TimeUnit.SECONDS.sleep(1);

		// 1. Default is details, Enter Value for name.
		/*driver.findElement(By.cssSelector("#name")).clear();
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.cssSelector("#name")).sendKeys("Test_Joiner_1");
		TimeUnit.SECONDS.sleep(1);*/

		driver.findElement(By.xpath("//img[@class='expressionActionButton' and contains(@src,'edit')]")).click();
		TimeUnit.SECONDS.sleep(1);
		driver.switchTo().activeElement();
		
//		driver.findElement(By.xpath("//div[@class='CodeMirror-lines']")).clear();
	//	driver.findElement(By.xpath("//div[@class='CodeMirror cm-s-default CodeMirror-wrap CodeMirror-empty']")).click();
//		driver.findElement(By.xpath("//div[@class='CodeMirror cm-s-default CodeMirror-wrap CodeMirror-empty']")).sendKeys("Joiner Condition");
		
		js = (JavascriptExecutor) driver;
		String addDummyEdgeSrc2toJoiner1 = "document.querySelector('.CodeMirror-line').innerText = 'Joiner Condition'";
		js.executeScript(addDummyEdgeSrc2toJoiner1);

		driver.findElement(By.xpath("//div[contains(@class,'MuiDialogActions-root')]//span[contains(text(),'Save')]"))
				.click();

		driver.switchTo().activeElement();

		driver.findElement(By.xpath("//div[contains(@class,'MuiSelect-select')]/span")).click();
		TimeUnit.SECONDS.sleep(1);

		driver.switchTo().activeElement();

		driver.findElement(By
				.xpath("//ul[contains(@class,'MuiList-root') and @role='listbox']//li[@data-value='FULL']//span[contains(text(),'Full Outer Join')]"))
				.click();

		driver.switchTo().activeElement();

		TimeUnit.SECONDS.sleep(1);

		// 4. Click the validation tab.
		driver.findElement(By.xpath("//span[contains(text(),'Validation')]")).click();
		TimeUnit.SECONDS.sleep(1);
	}

	public static void testFilterProperties() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scriptToTapSource = "document.cy.elements()[4].trigger('tap')";
		js.executeScript(scriptToTapSource);

		// 1. Filter Properties
		driver.findElement(By.xpath("//span[contains(text(),'Details')]")).click();
		TimeUnit.SECONDS.sleep(1);

		/*driver.findElement(By.cssSelector("#name")).clear();
		driver.findElement(By.cssSelector("#name")).sendKeys("Test_Filter_1");
		TimeUnit.SECONDS.sleep(1);*/

		driver.findElement(
				By.xpath("//label[@id='filterExpression']//following-sibling::img[@class='expressionActionButton']"))
				.click();

		driver.switchTo().activeElement();
		TimeUnit.SECONDS.sleep(1);

		js = (JavascriptExecutor) driver;
		String addDummyEdgeSrc2toJoiner1 = "document.querySelector('.CodeMirror-line').innerText = 'Filter Condition'";
		js.executeScript(addDummyEdgeSrc2toJoiner1);

		String pathForCancelButton = "//div[contains(@class,'MuiDialogActions-root')]//span[contains(text(),'Save')]";
		driver.findElement(By.xpath(pathForCancelButton)).click();

		driver.switchTo().activeElement();

		TimeUnit.SECONDS.sleep(1);

		// 4. Click the validation tab.
		driver.findElement(By.xpath("//span[contains(text(),'Validation')]")).click();
		TimeUnit.SECONDS.sleep(1);
	}

	public static void testExprProperties() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scriptToTapSource = "document.cy.elements()[6].trigger('tap')";
		js.executeScript(scriptToTapSource);

		// 1. Filter Properties
		driver.findElement(By.xpath("//span[contains(text(),'Details')]")).click();
		TimeUnit.SECONDS.sleep(1);

		// 1. Default is details, Enter Value for name.
		/*driver.findElement(By.cssSelector("#name")).clear();
		driver.findElement(By.cssSelector("#name")).sendKeys("Test_Expression_1");
		TimeUnit.SECONDS.sleep(1);*/

		driver.findElement(By.xpath("//div[@class='expressionTableToolButtons']//img[contains(@src,'add')]")).click();

		driver.findElement(By.xpath("//div[@class='expressionTableToolButtons']//img[contains(@src,'add')]")).click();

		driver.findElement(
				By.xpath("//table[contains(@class,'MuiTable-root')]/tbody/tr[2]//td/img[contains(@src,'edit')]"))
				.click();

		driver.switchTo().activeElement();

		js = (JavascriptExecutor) driver;
		String addDummyEdgeSrc2toJoiner1 = "document.querySelector('.CodeMirror-line').innerText = 'Expression Condition'";
		js.executeScript(addDummyEdgeSrc2toJoiner1);

		driver.findElement(By.xpath("//div[@id='select-dataType']")).click();

		driver.switchTo().activeElement();

		TimeUnit.SECONDS.sleep(1);

		driver.findElement(By.xpath("//ul//li[contains(text(),'DATE')]")).click();
		TimeUnit.SECONDS.sleep(1);

		driver.switchTo().activeElement();

		String pathForCancelButton = "//div[contains(@class,'MuiDialogActions-root')]//span[contains(text(),'Save')]";
		driver.findElement(By.xpath(pathForCancelButton)).click();

		driver.switchTo().activeElement();

		TimeUnit.SECONDS.sleep(1);

		// 4. Click the validation tab.
		driver.findElement(By.xpath("//span[contains(text(),'Validation')]")).click();
		TimeUnit.SECONDS.sleep(1);
	}

	@AfterMethod
	public void closeApp() {
		driver.quit();
	}

}

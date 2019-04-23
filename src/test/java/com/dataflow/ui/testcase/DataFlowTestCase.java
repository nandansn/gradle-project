package com.dataflow.ui.testcase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.dataflow.ui.common.util.UIBaseTest;
import com.dataflow.ui.page.DataFlowPage;

public class DataFlowTestCase extends UIBaseTest {

	@Test
	public void testOne() {
		
		/*UITestContext.getInstance().getWebDriver().get("http://localhost:8080/catalog");
		UITestContext.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'Create')]")).click();
		UITestContext.getInstance().getWebDriver().findElement(By.xpath("//span[contains(text(),'DataFlow')]")).click();*/
		
		DataFlowPage.open().isLoaded();
		DataFlowPage.get().palette().isLoaded();
		DataFlowPage.get().property().isLoaded();

	}

}

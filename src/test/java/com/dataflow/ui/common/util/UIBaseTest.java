package com.dataflow.ui.common.util;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class UIBaseTest {

	@BeforeClass
	public void setUpTest() {
		UITestContext.getInstance().createWebDriver();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownTest() {
		UITestContext.getInstance().removeWebDriver();
	}

}

package com.dataflow.ui.common.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UITestContext {

	private static volatile UITestContext uiTestContext;
	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	/**
	 * Load test properties.
	 */
	private UITestContext() {

		try {

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Get singletone of UITestContext.
	 *
	 * @return UITestContext
	 */
	public static UITestContext getInstance() {
		if (uiTestContext == null) {
			synchronized (UITestContext.class) {
				if (uiTestContext == null) {
					uiTestContext = new UITestContext();
				}
			}
		}
		return uiTestContext;
	}

	public WebDriver getWebDriver() {

		if (webDriver.get() == null)

			this.createWebDriver();

		return webDriver.get();
	}

	public WebDriver createWebDriver() {

		webDriver.set(new ChromeDriver());

		return webDriver.get();
	}

	public void removeWebDriver() {

		webDriver.get().quit();

		webDriver.set(null);
	}

}

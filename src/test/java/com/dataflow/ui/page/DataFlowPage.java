package com.dataflow.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.dataflow.ui.common.util.UITestContext;

public class DataFlowPage extends BasePage {

	private static DataFlowPage page;

	Palette palette;
	Property property;

	private static final String PAGE_URI = "http://localhost:8080/dataflow/create";

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement saveButton;

	@FindBy(xpath = "//span[contains(text(),'Cancel')]")
	WebElement cancelButton;

	public static DataFlowPage get() {

		if (page == null) {

			page = initializePage(DataFlowPage.class);
		}
		return page;

	}

	/**
	 * This method is used to open the UI page
	 * 
	 * @return
	 */

	public static DataFlowPage open() {

		if (page == null) {

			UITestContext.getInstance().getWebDriver().get(PAGE_URI);
			page = initializePage(DataFlowPage.class);

		}
		return page;

	}

	public boolean isLoaded() {

		return saveButton.isDisplayed() && cancelButton.isDisplayed();
	}

	public Palette palette() {

		if (palette == null) {

			palette = PageFragment.initializeFragment(Palette.class);
		}
		return palette;

	}

	public Property property() {

		if (property == null) {

			property = PageFragment.initializeFragment(Property.class);
		}
		return property;

	}

}

package com.dataflow.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Property extends PageFragment {

	@FindBy(id = "businessName")
	WebElement nameTextBox;

	@FindBy(id = "name")
	WebElement identifierTextBox;

	@FindBy(id = "description")
	WebElement descriptionTextArea;

	@FindBy(xpath = "//span[contains(text(),'Validation')]")
	WebElement validationButton;

	public boolean isLoaded() {

		
		return nameTextBox.isDisplayed() && identifierTextBox.isDisplayed();
	}

}

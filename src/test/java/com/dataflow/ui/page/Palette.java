package com.dataflow.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Palette extends PageFragment{
	
	@FindBy(id = "Source")
	WebElement sourceIcon;
	
	@FindBy(id = "Target")
	WebElement targetIcon;
	
	@FindBy(id = "Filter")
	WebElement filterIcon;
	
	@FindBy(id = "Joiner")
	WebElement joinerIcon;
	
	@FindBy(id = "Projection")
	WebElement expressionIcon;
	
	
	public boolean isLoaded() {

		return sourceIcon.isDisplayed() && targetIcon.isDisplayed();
	}

}

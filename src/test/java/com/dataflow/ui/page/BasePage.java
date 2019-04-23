package com.dataflow.ui.page;

import org.openqa.selenium.support.PageFactory;

import com.dataflow.ui.common.util.UITestContext;

public class BasePage {
	
	public static <T extends BasePage> T initializePage(Class<T> clazz){
		
		return  PageFactory.initElements(UITestContext.getInstance().getWebDriver(), clazz);
	}
	
	

}

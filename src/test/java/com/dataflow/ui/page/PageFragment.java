package com.dataflow.ui.page;

import org.openqa.selenium.support.PageFactory;

import com.dataflow.ui.common.util.UITestContext;

public class PageFragment {
	
	public static <T extends PageFragment> T initializeFragment(Class<T> clazz){
		
		return  PageFactory.initElements(UITestContext.getInstance().getWebDriver(), clazz);
	}
	

}

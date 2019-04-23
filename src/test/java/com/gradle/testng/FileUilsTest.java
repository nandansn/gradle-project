package com.gradle.testng;

import org.apache.commons.io.FileUtils;

public class FileUilsTest {
	
	public static void main(String args[]){
		
		System.out.println(FileUtils.class.getClassLoader().getResource("nanda/nan.txt").getPath());
		
		
		
	}
	
	

}

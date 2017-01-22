package com.school.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OperateElementWithJS {
	

	public static void modifyStyleBlock(WebDriver driver,By locator){
		JavascriptExecutor  executor = (JavascriptExecutor)driver;

		WebElement element = driver.findElement(locator);

		executor.executeScript("arguments[0].style=arguments[1]",element,"display: block;");
	}
	
	
	
	public static void removeAttribute(WebDriver driver,By locator,String attr_name){
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		WebElement e=driver.findElement(locator);
		executor.executeScript("arguments[0].removeAttribute('"+attr_name+"')", e);
	}
}

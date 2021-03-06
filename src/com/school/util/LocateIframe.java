package com.school.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocateIframe {
	
	
	/**
	 * 定位iframe
	 */
	public static WebDriver locateIframe(WebDriver driver,String iframe_name){
		WebElement iframe=driver.findElement(By.xpath("//iframe[starts-with(@id,'"+iframe_name+"')]"));
		Wait.waitMilliSeconds(5000);
		driver=driver.switchTo().frame(iframe);
		return driver;
	}

}

package com.school.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DialogPage {
	private WebDriver driver;
	
	public DialogPage(WebDriver driver){
		this.driver=driver;
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']")));

	}
	
	public String getTip(){
		 WebElement tip=driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div[contains(@class,'layui-layer-content')]"));	 
		 return tip.getText();
	}
	
	public void confirm(){
		driver.findElement(By.className("layui-layer-btn0")).click();
	}
	
	
	
	
}

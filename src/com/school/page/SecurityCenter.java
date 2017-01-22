package com.school.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.school.util.Wait;

public class SecurityCenter {
	private WebDriver driver;
	
	
	public SecurityCenter(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void clickModifypasswordBtn(){
		WebElement reset=Wait.explicitlyWait(driver, 10, By.id("reset_password"));
		  reset.click();
	}
	
	public void clickBindMobileBtn(){
		
	}
	public void clickBindEmailBtn(){
		
	}
	public void inputEmail(String email_url){
		
	}
	
	
	public void inputOldpassword(String old_password){
		 WebElement old_p=Wait.explicitlyWait(driver, 10, By.id("old_password"));
		  old_p.clear();
		  old_p.sendKeys(old_password);
	}
	
	public void inputNewpassword(String new_password){
		 driver.findElement(By.id("new_password")).sendKeys(new_password);
	}
	
	public void confirmNewpassword(String new_password){
		driver.findElement(By.id("re_password")).sendKeys(new_password);
	}
	
	public WebDriver submitModify(){
		driver.findElement(By.id("password_btn")).click();
		return driver;
	}
	
	public WebDriver submitBind(){
		return driver;
	}
	public void cancelModify(){
		
	}
	
	public void cancelBindEmail(){
		
	}
	
}

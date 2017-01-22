package com.school.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.school.database.ResultSetHelper;
import com.school.util.Wait;

public class PersonalCenter {
	private WebDriver  driver;
	
	public PersonalCenter(WebDriver  driver){
		this.driver=driver;
	}
	
	public void updateName(String name){  
		  WebElement member_name=driver.findElement(By.id("member_name"));
		  member_name.clear();
		  member_name.sendKeys(name);
 
	}
	
	public void updateSex(String sex){
		driver.findElement(By.xpath("//div[@class='radio-inline']/label/span[text()='"+sex+"']")).click();
		Wait.waitSeconds(5);
	}
	
	
	public WebDriver clickSubmit(){
		driver.findElement(By.id("user_btn")).click();
		return driver;
	}

	public String getMemName(){
		Wait.waitSeconds(5);
		return driver.findElement(By.xpath("//div[@class='user-info']/span[@class='user-name']")).getText();
	}
	
	/**
	 * 进入安全中心
	 */
	public void enterSecurityCenter(){
		driver.findElement(By.xpath("//ul[@class='nav']/li/a/span[text()='安全中心']")).click();
		Wait.waitSeconds(5);
	}

	
	public void clickBindMobileBtn(){
		driver.findElement(By.id("bind_btn1")).click();
		Wait.waitSeconds(5);
	}
	
	
	public void clickBindEmailBtn(){
		driver.findElement(By.id("bind_btn2")).click();
		Wait.waitSeconds(5);
	}
	
	
	public void clickResetPwdBtn(){
		driver.findElement(By.id("reset_password")).click();
		Wait.waitSeconds(5);
	}
	
	
	
	public void inputMobileNumber(String mobile_number){
		WebElement m=driver.findElement(By.id("mobile"));
		m.clear();
		m.sendKeys(mobile_number);
		Wait.waitSeconds(5);
	}
	
	public void inputEmail(String email){
		WebElement m=driver.findElement(By.id("email"));
		m.clear();
		m.sendKeys(email);
		Wait.waitSeconds(5);
	}
	
	public void inputOldpwd(String old_pwd){
		
		WebElement m=driver.findElement(By.id("old_password"));
		m.clear();
		m.sendKeys(old_pwd);
		Wait.waitSeconds(5);
	}
	
	
	public void inputNewpwd(String new_pwd){
		
		WebElement m=driver.findElement(By.id("new_password"));
		m.clear();
		m.sendKeys(new_pwd);
		Wait.waitSeconds(5);
	}
	
	public void confirmNewpwd(String new_pwd){
		
		WebElement m=driver.findElement(By.id("re_password"));
		m.clear();
		m.sendKeys(new_pwd);
		Wait.waitSeconds(5);
	}
	
	
	
	public String  clickFetchCodeBtn(String mobile_number){
		driver.findElement(By.id("fetch_code")).click();
		Wait.waitSeconds(10);

		String code=ResultSetHelper.smsMessage(mobile_number);
		
		
		return code.substring(0, 6);
	}
	
	public void inputCode(String code){
		driver.findElement(By.id("mobile_code")).sendKeys(code);
	}
	
	public void clickMobileConfirmBtn(){
		driver.findElement(By.id("mobile_confirm")).click();
		Wait.waitSeconds(5);
	}
	
	public void clickEmailConfirmBtn(){
		driver.findElement(By.id("email_confirm")).click();
		Wait.waitSeconds(5);
	}
	
	
	public void clickResetPwdConfirmBtn(){
		driver.findElement(By.id("password_btn")).click();
		
	}
	
	public String getphoneTip(){
		return driver.findElement(By.id("phoneTip")).getText();
		
	}
	
	public String getTip(){
		 WebElement tip=driver.findElement(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']/div[contains(@class,'layui-layer-content')]"));	 
		 return tip.getText();
	}
	
	
}

package com.school.page.basicmanage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.school.util.Wait;

public class BasicInformation {
	
	private WebDriver driver;
	public BasicInformation(WebDriver driver){
		this.driver=driver;
	}
	
	public String getOrgType(){
		Wait.waitSeconds(5);
		return driver.findElement(By.id("org_type")).getText();
	}
	
	public String getOrgName(){
		Wait.waitSeconds(5);
		return driver.findElement(By.id("org_name")).getText();
	}
	
	
	public void clickEditBtn(){
		
		driver.findElement(By.id("edit")).click();
		Wait.waitSeconds(5);
	}
	
	public void inputJC(String omit_name){
		WebElement e=driver.findElement(By.id("org_omit_name"));
		e.clear();
		e.sendKeys(omit_name);
		Wait.waitSeconds(5);
	}
	
	public void clickSaveBtn(){
		
		driver.findElement(By.id("save")).click();
		
	}
	
	public String getOrgCodeUrl(){
		Wait.waitSeconds(5);
		return driver.findElement(By.id("org_code")).getText();
	}
	
	public String getUserLimit(){
		Wait.waitSeconds(5);
		return driver.findElement(By.id("user_num_limit")).getText();
	}

	public String getEmpNum(){
		Wait.waitSeconds(5);
		return driver.findElement(By.id("org_emp_num")).getText();
	}
	public String getStuNum(){
		Wait.waitSeconds(5);
		return driver.findElement(By.id("org_stu_num")).getText();
	}
	
	public void clickUploadBtn(){
		driver.findElement(By.xpath("//span[@id='logo_img_upload']/i")).click();
		Wait.waitSeconds(5);
	}
	public String getInviteCode(){
		Wait.waitSeconds(5);
		return driver.findElement(By.id("invite-code")).getText();
	}
	
	public WebElement getQRcode(){
		return driver.findElement(By.id("org_qr_code"));
	}
	
	public WebDriver enterLeftNav(String nav_name){
		driver.findElement(By.xpath("//ul[@class='nav']/li/a/span[text()='"+nav_name+"']")).click();
		Wait.waitSeconds(5);
		return driver;
	}
	
	
	
}

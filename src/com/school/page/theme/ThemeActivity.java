package com.school.page.theme;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.school.util.OperateElementWithJS;
import com.school.util.Wait;

public class ThemeActivity {
	private WebDriver driver;
	
	public ThemeActivity(WebDriver driver){
		this.driver=driver;
	}
	
	public WebDriver enterNavMenu(String nav_name){
		driver.findElement(By.xpath("//ul[@class='nav']/li/a/span[text()='"+nav_name+"']")).click();
		Wait.waitSeconds(5);
		return driver;
	}

	
	
	public void inputThemeTitle(String theme_title){
		WebElement e=driver.findElement(By.xpath("//input[@name='activity_title' and @type='text']"));
		e.clear();
		e.sendKeys(theme_title);
	}
	
	public void inputDescritions(String activity_desc){
		WebElement e=driver.findElement(By.xpath("//textarea[@name='activity_content']"));
		e.clear();
		e.sendKeys(activity_desc);
		Wait.waitSeconds(5);
	}
	
	public void uploadFile(String appendix_url){
		OperateElementWithJS.removeAttribute(driver, By.xpath("//a[@id='a_file_picker']//input[@name='file']"), "class");
		WebElement e=driver.findElement(By.xpath("//a[@id='a_file_picker']//input[@name='file']"));
		e.sendKeys(appendix_url);
		Wait.waitSeconds(5);
	}
	
	public String getUploadState(){
		return driver.findElement(By.xpath("//div[@id='upload_fin']//span[@class='state']")).getText();
	}
	
	public void inputGroup(String group_name){
		driver.findElement(By.xpath("//div[@id='group_list']//label/span[text()='"+group_name+"']")).click();
		Wait.waitSeconds(5);
		
	}
	
	public void setDeadline(String deadline){
		
		
		//移除日期的可读性
		OperateElementWithJS.removeAttribute(driver, By.id("date"), "readonly");
		
		WebElement date=driver.findElement(By.id("date"));
		
		date.clear();
		date.sendKeys(deadline);
		Wait.waitSeconds(5);
		
	}
	
	public void confirm(){
		driver.findElement(By.id("btn_publish")).click();
		Wait.waitSeconds(5);
	}
	
	public WebElement findActivity(String activity_name){
		WebElement e=driver.findElement(By.xpath("//div[@id='activity_list']/ul/li/div[@class='header']/a[text()='"+activity_name+"']"));
		return e;
	}
	
	
	public void viewActivityDetails(WebElement activity){
		WebElement li=activity.findElement(By.xpath("./ancestor::li"));
		li.findElement(By.xpath("./div[@class='more']/a")).click();
		Wait.waitSeconds(5);
	}
	
	public String getActivityTitle(){
		return driver.findElement(By.id("activity_title")).getText();
	}
	
	public void enterHistoryActivity(){
		driver.findElement(By.xpath("//div[contains(@class,'pull-left')]/ul/li/a[text()='历史活动']")).click();
		Wait.waitSeconds(5);
	}
	
	
	
}

package com.school.page.basicmanage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.school.util.Wait;

public class AuthorityManage {
	private WebDriver driver;
	public AuthorityManage(WebDriver driver){
		this.driver=driver;
	}
	

	public WebElement findAdminAccount(String account_id){
		
		WebElement e=null;
		
		try{
			e=driver.findElement(By.xpath("//tbody[@id='admin_list']/tr/td[contains(text(),'"+account_id+"')]"));
		}catch(NoSuchElementException ee){
			ee.printStackTrace();
		}
		return  e;
	}
	
	public void clickRemoveAdmin(WebElement e){
		e.findElement(By.xpath("./../td/a")).click();
		Wait.waitSeconds(3);
		
	}
	
	public String getAdminName(WebElement e){
		return e.findElement(By.xpath("./../td[1]")).getText().trim();
	}
	
	public void clickAddAdminBtn(){
		WebElement e=driver.findElement(By.cssSelector("div.btns.pull-right"));
		e.findElement(By.xpath("./a")).click();
		Wait.waitSeconds(3);
	}
	
	public void chooseDepartment(){
		driver.findElement(By.id("triggerValue")).click();
		Wait.waitSeconds(2);
		
		driver.findElement(By.xpath("//div[@id='deptTree']/ul[1]/li[1]/div/div/a")).click();
		Wait.waitSeconds(2);
	}
	
	
	public void chooseUser(String admin_name){
		driver.findElement(By.xpath("//div[@id='select_from']/a[text()='"+admin_name+"']")).click();
		Wait.waitSeconds(2);
		
		WebElement e=driver.findElement(By.cssSelector("div.col-xs-2.text-center.list-ctrl"));
		e.findElement(By.xpath("./a[2]")).click();
		Wait.waitSeconds(2);
		
	}
	
	public void clickAddAdminConfirm(){
		WebElement e=driver.findElement(By.cssSelector("div.row.form-btns.text-center"));
		e.findElement(By.xpath("./a[2]")).click();
		Wait.waitSeconds(5);
	}
	
	
	
}

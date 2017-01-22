package com.school.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.school.util.Wait;

public class SchoolHomepage {
	
	private WebDriver driver;
	
	public SchoolHomepage(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * 获取登陆用户名
	 * @return
	 */
	public String getUsername(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='user-info']/span[@class='user-name']")));
		return driver.findElement(By.xpath("//div[@class='user-info']/span[@class='user-name']")).getText();
	}
	
	/**
	 * 进入个人中心
	 * @return
	 */
	public WebDriver enterPersonalDetail(){
		Wait.waitSeconds(5);
		driver.findElement(By.cssSelector("i.iconfont.icon-setting")).click();
		return driver;
	}
	
	/**
	 * 进入课程管理
	 * @return
	 */	
	public WebDriver enterCourseManage(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		Wait.waitMilliSeconds(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='w-menu']/ul/li/a[text()='课程管理']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='w-menu']/ul/li/a[text()='课程管理']")));
		
		driver.findElement(By.xpath("//div[@class='w-menu']/ul/li/a[text()='课程管理']")).click();
		return driver;
	}
	
	
	
	/**
	 * 进入顶部的菜单
	 * @param menu_name
	 * @return
	 */
	public WebDriver enterTopMenu(String menu_name){
		driver.findElement(By.xpath("//div[@class='w-menu']/ul/li/a[text()='"+menu_name+"']")).click();
		Wait.waitSeconds(5);
		return driver;
	}

	
	
	
	/**
	 * 进入消息
	 */
	public WebDriver enterMessage(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("umessage")));
		driver.findElement(By.xpath("//a[@class='umessage']/span")).click();
		return driver;
	}
	

}

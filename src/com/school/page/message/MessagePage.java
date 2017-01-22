package com.school.page.message;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MessagePage {
	private WebDriver driver;
	
	public MessagePage(WebDriver driver){
		this.driver=driver;
	}
	
	
	public String getMessageCenterText(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='p-header']/h2")));
		return driver.findElement(By.xpath("//div[@class='p-header']/h2")).getText();
		
	}
	
	public String getCommentText(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("comment")));
		return driver.findElement(By.xpath("//li[@id='comment']/a")).getText();
	}
	
	
	public String getLikeText(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("like")));
		return driver.findElement(By.xpath("//li[@id='like']/a")).getText();
	}
	
	public String getNoticeText(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("notice")));
		return driver.findElement(By.xpath("//li[@id='notice']/a")).getText();
	}
	
	
}

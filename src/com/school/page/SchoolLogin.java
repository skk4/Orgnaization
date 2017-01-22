package com.school.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.school.util.Wait;

public class SchoolLogin {
	private WebDriver driver;
	
	
	public SchoolLogin(WebDriver driver){
		this.driver=driver;
	}
	
	  /**
	   * 请求主机地址
	   * @param web_driver
	   * @param host_url
	   */
	  public WebDriver lauchSite(String host_url){

			driver.get(host_url);
			WebDriverWait wait=new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_button")));
			driver.manage().window().maximize();
			return driver;
	}
	  
	  
	  public void inputUsername(String user_name){
		  WebElement username=driver.findElement(By.id("username"));
		  username.clear();
		  username.sendKeys(user_name);
	  }
	  
	  public void inputPassword(String pass_word){
		  WebElement pwd=driver.findElement(By.id("password"));
		  pwd.clear();
		  pwd.sendKeys(pass_word);
	  }
	  
	  public WebDriver clickloginButton(){
		  driver.findElement(By.id("login_button")).click();
		  Wait.waitSeconds(5);
		  return driver;
	  }
	  
	  public WebDriver clicklogoutButton(){
		  driver.findElement(By.id("logout")).click();
		  Wait.waitSeconds(3);
		  return driver;
	  }
	  
	  
	  public WebDriver loginSchool(String host_url,String user_name,String pass_word){
		  this.lauchSite(host_url);
		  this.inputUsername(user_name);
		  this.inputPassword(pass_word);
		  driver=this.clickloginButton();
		  Wait.waitSeconds(5);
		  return driver;
	  }
	  	  
	  
	  public String getTitle(){
		  Wait.waitMilliSeconds(5000);
		  return driver.getTitle();
	  }
	  
	  
	  public String getErrorUsername(){
		  Wait.waitMilliSeconds(2000);
		  return driver.findElement(By.id("username-error")).getText();
		  
	  }
	  
	  public String getErrorPassword(){
		  Wait.waitMilliSeconds(2000);
		  return driver.findElement(By.id("password-error")).getText();
		  
	  }
	
	
	  public String getErrorMsg(){
		  Wait.waitMilliSeconds(2000);
		  return driver.findElement(By.id("error_msg")).getText();
	  }
	  
	  
	  
}

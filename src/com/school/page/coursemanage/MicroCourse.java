package com.school.page.coursemanage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.school.util.Wait;

public class MicroCourse {
	
	private WebDriver driver;
	
	
	public MicroCourse(WebDriver driver){
		this.driver=driver;
	}
	
	
	public WebDriver clickAddAlbum(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("add_album")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("add_album")));
		driver.findElement(By.id("add_album")).click();
		
		return driver;
	}

	
	public void inputAlbumName(String album_name){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[starts-with(@id,'layui-layer') and @type='page']")));
		WebElement name=driver.findElement(By.id("album_name"));
		name.clear();
		name.sendKeys(album_name);
	}
	
	public void clickConfirmBtn(){
		driver.findElement(By.id("op_album_ok")).click();
		Wait.waitSeconds(5);
	}
	
	
	public WebElement findAlbum(String album_name){
		WebElement album=null;
		Wait.waitSeconds(5);
/*		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='album_list_view']/div[@class='list-grid']//a[@class='title' and text()='"+album_name+"']")));
*/		try{
			album=driver.findElement(By.xpath("//div[@id='album_list_view']/div[@class='list-grid']//a[@class='title' and text()='"+album_name+"']"));

		}catch(NoSuchElementException e){
			e.printStackTrace();
		}
		return album;
	}
	
	public void clickRenameBtn(WebElement album){
		Actions action=new Actions(driver);
		action.moveToElement(album).perform();
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(album, By.xpath("./../div[@class='item-opts']//a/i[contains(@class,'icon-edit')]")));
		
		action.moveToElement(album.findElement(By.xpath("./../div[@class='item-opts']//a/i[contains(@class,'icon-edit')]"))).perform();
		
		wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(album, By.xpath("./../div[@class='item-opts']//a/span[text()='重命名']")));
		album.findElement(By.xpath("./../div[@class='item-opts']//a/span[text()='重命名']")).click();
		
	}
	
	public void clickDeleteBtn(WebElement album){
		Actions action=new Actions(driver);
		action.moveToElement(album).perform();
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(album, By.xpath("./../div[@class='item-opts']//a/i[contains(@class,'icon-del')]")));
		
		action.moveToElement(album.findElement(By.xpath("./../div[@class='item-opts']//a/i[contains(@class,'icon-del')]"))).perform();
		
		wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(album, By.xpath("./../div[@class='item-opts']//a/span[text()='删除']")));
		album.findElement(By.xpath("./../div[@class='item-opts']//a/span[text()='删除']")).click();
		
	}
	
	
	public void clickSendBtn(WebElement album){
		Actions action=new Actions(driver);
		action.moveToElement(album).perform();
/*		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(album, By.xpath("./../div[@class='item-opts']//a/i[contains(@class,'icon-send')]")));
*/
		Wait.waitSeconds(2);
		action.moveToElement(album.findElement(By.xpath("./../div[@class='item-opts']//a/i[contains(@class,'icon-send')]"))).perform();
		
/*		wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(album, By.xpath("./../div[@class='item-opts']//a/span[text()='发送']")));
*/		Wait.waitSeconds(2);
		album.findElement(By.xpath("./../div[@class='item-opts']//a/span[text()='发送']")).click();

	}
	
	
	
	public WebDriver enterAblum(String album_name){
		WebElement a=this.findAlbum(album_name);
		a.findElement(By.xpath("./../div[@class='box']/a")).click();
		Wait.waitSeconds(5);
		return driver;
	}
	
	
	/*
	 * 点击制作微课
	 */
	public void clickMakeCourseBtn(){
		driver.findElement(By.id("add_album_course")).click();
		Wait.waitSeconds(5);
	}
	
	
}

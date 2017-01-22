package com.school.page.coursemanage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.school.util.LocateIframe;
import com.school.util.Wait;


public class Album {
	
	private WebDriver driver;
	
	
	public Album(WebDriver driver){
		this.driver=driver;
		WebDriverWait wait=new WebDriverWait(driver,10);
		Wait.waitMilliSeconds(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("upload_local_res")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("add_album_course")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("refresh_btn")));
		
	}
	
	public WebDriver clickUploadBtn(){
		driver.findElement(By.id("upload_local_res")).click();
		
/*		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@id,'layui-layer-iframe')]")));
*/		
		LocateIframe.locateIframe(driver, "layui-layer-iframe");
		return driver;
	}
	
	
	public void sendResourceUrl(String resource_url){
		
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		WebElement e=driver.findElement(By.xpath("//div[@id='filePicker']//input[@name='file']"));
		executor.executeScript("arguments[0].removeAttribute(\"class\")", e);
		
		driver.findElement(By.name("file")).sendKeys(resource_url);
	}
	
	public void clickUploadReadyBtn(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'state-ready')]")));
		
		driver.findElement(By.xpath("//div[contains(@class,'state-ready')]")).click();
		Wait.waitMilliSeconds(5000);
		driver.switchTo().defaultContent();
	}
	
	
	public WebElement findCourseAndResource(String resource_name){
		Wait.waitMilliSeconds(5000);
		return driver.findElement(By.xpath("//div[@id='course']//li/div[contains(@class,'title')]/div/a[text()='"+resource_name+"']"));
	}
	
	public void clickPreviewBtn(WebElement resource){
		WebElement a=resource.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		a.findElement(By.xpath("./div[@class='box']/div[@class='opts-list']//a[@data-name='preview-btn']")).click();
	
		Wait.waitMilliSeconds(5000);
	}
	
	public void clickSendBtn(WebElement resource){
		WebElement a=resource.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		a.findElement(By.xpath("./div[@class='box']/div[@class='opts-list']//a[@data-name='send-btn']")).click();
	
		Wait.waitMilliSeconds(5000);
	}
	
	public void clickRenameBnt(WebElement resource){
		WebElement a=resource.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		
		WebElement reset=a.findElement(By.xpath(".//a[@data-name='reset-name-btn']"));
		action.moveToElement(reset).perform();
		Wait.waitMilliSeconds(3000);
		reset.findElement(By.xpath("./span[text()='重命名']")).click();
		Wait.waitMilliSeconds(3000);
	}
	
	public void clickMoveBtn(WebElement resource){
		WebElement a=resource.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		
		WebElement reset=a.findElement(By.xpath(".//a[@data-name='move-btn']"));
		action.moveToElement(reset).perform();
		Wait.waitMilliSeconds(3000);
		reset.findElement(By.xpath("./span[text()='移动']")).click();
		Wait.waitMilliSeconds(3000);
	}
	
	public void clickDeleteBtn(WebElement resource){
		WebElement a=resource.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		
		WebElement reset=a.findElement(By.xpath(".//a[@data-name='del-btn']"));
		action.moveToElement(reset).perform();
		Wait.waitMilliSeconds(3000);
		reset.findElement(By.xpath("./span[text()='删除']")).click();
		Wait.waitMilliSeconds(3000);
	}
	
	
	public void clickPubBtn(WebElement resource){
		WebElement a=resource.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		
		WebElement reset=a.findElement(By.xpath(".//a[@data-name='pub-course']"));
		action.moveToElement(reset).perform();
		Wait.waitMilliSeconds(3000);
		reset.findElement(By.xpath("./span[text()='发布']")).click();
		Wait.waitMilliSeconds(3000);
	}
	
	public void clickCancelPubBtn(WebElement resource){
		WebElement a=resource.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		
		WebElement reset=a.findElement(By.xpath(".//a[@data-name='cancel-pub-course']"));
		action.moveToElement(reset).perform();
		Wait.waitMilliSeconds(3000);
		reset.findElement(By.xpath("./span[text()='取消发布']")).click();
		Wait.waitMilliSeconds(3000);
	}
	
	
	
	public void chooseColumns(String fColumn,String sColumn){
		driver.findElement(By.id("column_sel")).findElement(By.xpath("./option[text()='"+fColumn+"']")).click();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("column_sel_2")).findElement(By.xpath("./option[text()='"+sColumn+"']")).click();

	}
	
	
	public void chooseNewAlbum(String new_album){
		WebElement as=driver.findElement(By.id("album_sel"));
		as.findElement(By.xpath("./option[text()='"+new_album+"']")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	
	public void inputResetName(String resource_name,String new_res_name){
		WebElement resource1=this.findCourseAndResource(resource_name);
		  
		  JavascriptExecutor  js = (JavascriptExecutor)driver;
		  WebElement element=resource1.findElement(By.xpath("./../../input"));
		  js.executeScript("arguments[0].style=arguments[1]",element,"display: block;");
		  element.clear();
		  element.sendKeys(new_res_name);
		  this.clickRefreshBtn();
	}
	
	
	
	public String getResourseName(){
		return driver.findElement(By.id("course_name")).getText();
	}
	
	
	public void clickRefreshBtn(){
		driver.findElement(By.id("refresh_btn")).click();
		Wait.waitMilliSeconds(5000);
	}
	public void clickMoveConfirmBtn(){
		driver.findElement(By.id("move_btn")).click();
		Wait.waitMilliSeconds(5000);
	}

	public void clickPubConfirmBtn(){
		driver.findElement(By.id("pub_btn")).click();
		Wait.waitMilliSeconds(5000);
	}
	
}

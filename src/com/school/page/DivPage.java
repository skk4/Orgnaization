package com.school.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.school.util.Wait;

public class DivPage {

	
private WebDriver driver;
	
	public DivPage(WebDriver driver){
		this.driver=driver;
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[starts-with(@id,'layui-layer') and @type='page']")));

	}
	
	public void chooseGroup(String group_name){
		 driver.findElement(By.xpath("//div[@id='album_send_list']/div/label/span[contains(text(),'"+group_name+"')]")).click();
	}
	
	public void confirmSendBtn(){
		driver.findElement(By.id("op_album_send_ok")).click();
	}
	
	public void chooseSendGroup(String group_name){
		driver.findElement(By.xpath("//div[@id='group_list']/div/label/span[text()='"+group_name+"']")).click();
	}
	
	public void clickSendBtn(){
		driver.findElement(By.id("send_btn")).click();
	}
	
	public void clickUploadCover(String pic_url){
		driver.findElement(By.xpath("//input[@name='file']")).sendKeys(pic_url);
		Wait.waitMilliSeconds(5000);
	}
	public void clickSaveBtn(){
		driver.findElement(By.xpath("//a[contains(@class,'btn-cropSave')]")).click();
		
	}
	
	public void chooseColumns(String fColumn,String sColumn){
		driver.findElement(By.id("column_sel")).findElement(By.xpath("./option[text()='"+fColumn+"']")).click();
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("column_sel_2")).findElement(By.xpath("./option[text()='"+sColumn+"']")).click();

	}
	
	public void clickPubConfirmBtn(){
		driver.findElement(By.id("pub_btn")).click();
		Wait.waitMilliSeconds(5000);
	}
	
	public void selectAlbum(String album_name){
		driver.findElement(By.xpath("//div[@class='dir-tree']/ul/li[@class='open']//span[contains(@class,'node') and text()='"+album_name+"']")).click();
		Wait.waitSeconds(5);
	}
	
	public void selectMicroCourse(String micro_c_name){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@id='album_course_view']//span[text()='"+micro_c_name+"']")));
		driver.findElement(By.xpath("//tbody[@id='album_course_view']//span[text()='"+micro_c_name+"']")).click();
		Wait.waitSeconds(5);
	}
	public void clickSelectInsBtn(){
		driver.findElement(By.id("a_insert_weike")).click();
		Wait.waitSeconds(5);
	}
	
	public void inputGroupName(String new_group_name){
		WebElement e=driver.findElement(By.id("group_name"));
		e.clear();
		e.sendKeys(new_group_name);
	}
	
	public void clickEditGroupConfirmBtn(){
		driver.findElement(By.xpath("//div[@id='groupEdit']//a[text()='确 定']")).click();
	}
	
	public void uploadLogo(String logo_url){
		driver.findElement(By.xpath("//input[@name='file']")).sendKeys(logo_url);
		Wait.waitMilliSeconds(5000);
		
	}
	
	
	public void inputDeptName(String dept_name){
		WebElement e=driver.findElement(By.id("dept_name"));
		e.clear();
		e.sendKeys(dept_name);
	}
	
	public void clickConfirmBtn(){
		driver.findElement(By.xpath("//a[text()='确 定']")).click();
		//不能休眠等待
	}
	
	
}

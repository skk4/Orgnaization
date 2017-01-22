package com.school.page.coursemanage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.school.util.LocateIframe;
import com.school.util.OperateElementWithJS;
import com.school.util.Wait;

public class Course {
	
	private WebDriver driver;
	public Course(WebDriver driver){
		this.driver=driver;
	}
	
	public void clickAddCourseBtn(){
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("add_course")).click();
	}
	
	public void enterNavCourseManage(){
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.xpath("//ul[@class='nav']/li/a/span[text()='课程管理']")).click();
	}
	
	public void inputCourseName(String course_name){
		
		WebElement e=driver.findElement(By.xpath("//input[@name='course_name']"));
		e.clear();
		e.sendKeys(course_name);
	}
	
	public void inputCourseDesc(String course_desc){
		WebElement e=driver.findElement(By.xpath("//textarea[@name='course_desc']"));
		e.clear();
		e.sendKeys(course_desc);
	}
	
	public  void uploadCover(){
		driver.findElement(By.id("uploader_cover")).click();
	}
	
	public void clickNextBtn(){
		driver.findElement(By.id("btn_next")).click();
	}
	
	public void clickcourseFinishBtn(){
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("btn_finish")).click();
	}
	
	public void clickcoursePreviewBtn(){
		Wait.waitMilliSeconds(5000);
		driver.findElement(By.id("a_preview")).click();
	}
	
	public WebElement findCourse(String course_name){
		Wait.waitMilliSeconds(5000);
		return driver.findElement(By.xpath("//div[@id='course_list_view']//li/div[@class='title']/div/a[text()='"+course_name+"']"));
	}
	
	public void moveToOpsAndClick(WebElement course,String op_name){
		WebElement a=course.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		a.findElement(By.xpath("./div[@class='box']/div[@class='opts-list']//a/div[text()='"+op_name+"']")).click();
	
		Wait.waitMilliSeconds(5000);
	}
	
	public void movoToItemsAndClick(WebElement course,String item_name){
		WebElement li=course.findElement(By.xpath("./ancestor::li"));
		Actions action=new Actions(driver);
		action.moveToElement(li).perform();
		Wait.waitMilliSeconds(3000);

		WebElement a=li.findElement(By.xpath(".//a/span[text()='"+item_name+"']/parent::a"));
		action.moveToElement(a).perform();
		Wait.waitMilliSeconds(3000);
		a.findElement(By.xpath("./span[text()='"+item_name+"']")).click();
		Wait.waitMilliSeconds(3000);
	}
	
	public String  getCourseName(){
		return driver.findElement(By.id("course_name")).getText();
	}
	
	
	public String  getCourseDesc(){
		return driver.findElement(By.id("course_desc")).getText();
	}
	
	public String getPubStatus(String course_name){
		WebElement course=this.findCourse(course_name);
		return course.findElement(By.xpath("./../span")).getText();
	}
	
	public void clickAddMenu(String menu_type){
		if(menu_type.equalsIgnoreCase("同级目录")){
			driver.findElement(By.id("same_menu")).click();
		}else if(menu_type.equalsIgnoreCase("子目录")){
			driver.findElement(By.id("child_menu")).click();
		}
	}
	
	
	
	public void inputMenuName(String menu_name){

		WebElement input=driver.findElement(By.xpath("//ul[@id='course_menu_view']/li/span[contains(@class,'edit active')]/input"));
		input.clear();
		input.sendKeys(menu_name);
		this.clickMenuManageIcon();
	}
	
	public void inputChildMenuName(WebElement parent,String menu_name){
		WebElement input=parent.findElement(By.xpath("./../ul/li/span[contains(@class,'edit active')]/input"));
		input.clear();
		input.sendKeys(menu_name);
		this.clickMenuManageIcon();
	}
	
	
	public void clickMenuManageIcon(){
		driver.findElement(By.className("p-title")).click();
		Wait.waitMilliSeconds(3000);
	}
	
	public WebElement findMenu(String menu_name){
		return driver.findElement(By.xpath("//ul[@id='course_menu_view']/li/div[contains(text(),'"+menu_name+"')]"));
	}
	
	
	public WebElement findChildMenu(WebElement parent,String menu_name){
		return parent.findElement(By.xpath("./../ul/li/div[contains(text(),'"+menu_name+"')]"));

	}
	
	
	public void clickMenuDeleteBtn(WebElement menu){
		Actions action=new Actions(driver);
		action.moveToElement(menu).perform();
		Wait.waitSeconds(3);
		menu.findElement(By.xpath("./span/a[contains(@class,'icon-del')]")).click();
	}
	
	public void clickMenuEditBtn(WebElement menu){
		Actions action=new Actions(driver);
		action.moveToElement(menu).perform();
		Wait.waitSeconds(3);
		menu.findElement(By.xpath("./span/a[contains(@class,'icon-edit')]")).click();
	}
	
	public void clickChildMenuEditBtn(WebElement menu,String menu_name){
		WebElement c=this.findChildMenu(menu, menu_name);
		Actions action=new Actions(driver);
		action.moveToElement(c).perform();
		Wait.waitSeconds(3);
		c.findElement(By.xpath("./span/a[contains(@class,'icon-edit')]")).click();
	}
	
	
	
	public void clickChildMenuDeleteBtn(WebElement menu,String menu_name){
		
		WebElement c=this.findChildMenu(menu, menu_name);
		Actions action=new Actions(driver);
		action.moveToElement(c).perform();
		Wait.waitSeconds(3);
		c.findElement(By.xpath("./span/a[contains(@class,'icon-del')]")).click();
	}
	
	public void clickUploadBtn(){
		driver.findElement(By.id("a_upload")).click();
		Wait.waitSeconds(5);
	}
	
	public void clickChooseFileBtn(String resource_url){
		LocateIframe.locateIframe(driver, "layui-layer-iframe");
		OperateElementWithJS.removeAttribute(driver, By.xpath("//div[@id='filePicker']/div/input"), "class");
		
		driver.findElement(By.xpath("//div[@id='filePicker']/div/input")).sendKeys(resource_url);
		
	}
	
	
	public void clickUploadReadyBtn(){
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'state-ready')]")));
		
		driver.findElement(By.xpath("//div[contains(@class,'state-ready')]")).click();
		Wait.waitMilliSeconds(5000);
		driver.switchTo().defaultContent();
	}
	
	
	public WebElement findMenuResource(String resource_name){
		return driver.findElement(By.xpath("//div[@id='menu_resource_view']//a[@name='res_name' and text()='"+resource_name+"']"));
	}
	
	public void clickMenuResourceBtn(WebElement e){
		Actions a=new Actions(driver);
		a.moveToElement(e).perform();
		Wait.waitSeconds(3);
		
		WebElement li=e.findElement(By.xpath(".//ancestor::li"));
		WebElement btn=li.findElement(By.xpath("./div[@class='item-opts']//a[@name='btn_del']"));
		a.moveToElement(btn).perform();
		Wait.waitSeconds(3);
		
		btn.findElement(By.xpath("./span")).click();
		Wait.waitSeconds(5);
	}
	
	
	public void clickSelectInsertBtn(){
		driver.findElement(By.id("a_select_insert")).click();
		Wait.waitSeconds(5);
	}
	
	public void clickEditCourseBtn(){
		
		driver.findElement(By.id("a_edit_course")).click();
		Wait.waitSeconds(5);
	}
	
	
}

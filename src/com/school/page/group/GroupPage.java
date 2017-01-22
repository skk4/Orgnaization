package com.school.page.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.school.page.DivPage;
import com.school.util.Wait;

public class GroupPage {
	private WebDriver driver;
	
	public GroupPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void enterNavMenu(String menu_name){
		driver.findElement(By.xpath("//ul[@class='nav']/li/a/span[text()='"+menu_name+"']")).click();
		Wait.waitSeconds(5);
	}
	
	public WebDriver clickAddStudentBtn(){
		
		driver.findElement(By.cssSelector("i.iconfont.icon-add")).click();
		Wait.waitSeconds(5);
		return driver;
	}
	public void inputGrade(String grade_name){
		WebElement select=driver.findElement(By.id("grade"));
		select.findElement(By.xpath("./option[text()='"+grade_name+"']")).click();
		Wait.waitSeconds(5);
	}
	public void inputClass(String class_name){
		WebElement select=driver.findElement(By.id("class"));
		select.findElement(By.xpath("./option[text()='"+class_name+"']")).click();
		Wait.waitSeconds(5);
	}
	
	public void inputGroup(String group_name){
		WebElement select=driver.findElement(By.id("group"));
		select.findElement(By.xpath("./option[text()='"+group_name+"']")).click();
		Wait.waitSeconds(5);
	}
	
	public void chooseStudent(String student_name){
		driver.findElement(By.xpath("//div[@id='select_from']/a[text()='"+student_name+"']")).click();
		Wait.waitSeconds(5);
	}
	
	/**
	 * 选择群
	 * @param group_name
	 */
	public void chooseGroup(String group_name){
		driver.findElement(By.xpath("//ul[@id='group_list']/li/div[@class='node']/span[text()='"+group_name+"']")).click();
		Wait.waitSeconds(5);
	}
	
	public void select(){
		driver.findElement(By.xpath("//a[contains(@onclick,'select()')]")).click();
		Wait.waitSeconds(5);
	}

	public void inputNewGroup(String new_group_name){
		DivPage page=new DivPage(driver);
		page.inputGroupName(new_group_name);
		Wait.waitSeconds(3);
		
	}
	
	public void confirm(){
		DivPage page=new DivPage(driver);
		page.clickEditGroupConfirmBtn();
	}
	
	public void addStudentConfirmBtn(){
		driver.findElement(By.xpath("//a[contains(@onclick,'addStudent()')]")).click();
		Wait.waitSeconds(5);
	}
	
	
	public void clickExportListBtn(){
		driver.findElement(By.cssSelector("i.iconfont.icon-importexcel")).click();
		Wait.waitSeconds(5);
	}
	
	public WebElement findGroup(String group_name){
		
		WebElement e=driver.findElement(By.xpath("//ul[@id='group_list']/li/div[@class='node']/span[text()='"+group_name+"']"));
		return e;
	}
	
	public void renameGroup(WebElement group,String new_group_name){
		Actions action=new Actions(driver);
		action.moveToElement(group).perform();
		Wait.waitSeconds(3);
		
		WebElement li=group.findElement(By.xpath("./ancestor::li"));
		li.findElement(By.xpath("./span/a[contains(@class,'icon-edit')]")).click();
		
		Wait.waitSeconds(3);

	}
	public void deleteGroup(WebElement group){
		Actions action=new Actions(driver);
		action.moveToElement(group).perform();
		Wait.waitSeconds(3);
		
		WebElement li=group.findElement(By.xpath("./ancestor::li"));
		li.findElement(By.xpath("./span/a[contains(@class,'icon-del')]")).click();
		
		Wait.waitSeconds(3);
	}
	
	/**
	 * 通过学生姓名
	 * @param student_name
	 * @return
	 */
	public WebElement findStudent(String student_name){
		
		return driver.findElement(By.xpath("//div[@id='student_list']//tr/td[text()='"+student_name+"']"));
		 
	}
	
	public void clickStudentDetailsBtn(WebElement student){
		student.findElement(By.xpath("./../td[contains(@class,'opts')]/a[text()='查看']")).click();
		Wait.waitSeconds(5);
	}
	
	public void clickRemoveStudentBtn(WebElement student){
		student.findElement(By.xpath("./../td[contains(@class,'opts')]/a[text()='移除']")).click();
		Wait.waitSeconds(5);
	}
	
	public void inputSearchKeyWord(String key_word){
		
	}
	
	public void clickSearchBtn(){
		
	}
	
	
	
}

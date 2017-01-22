package com.school.page.basicmanage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.school.util.Wait;

public class DepartmentManage {
	private WebDriver driver;
	
	public DepartmentManage(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void clickAddDeptBtn(){
		driver.findElement(By.xpath("//div[@id='addDept_btn']//span")).click();
		Wait.waitSeconds(5);
	}
	
	public WebElement getDepartment(String dept_name){
		WebElement e=driver.findElement(By.xpath("//ul[@id='member_department_list']//a[@class='tree-node' and text()='"+dept_name+"']"));
		return e.findElement(By.xpath("./../../.."));
	}
	
	public void clickDeleteBtn(WebElement e){
		e.findElement(By.xpath(".//div[@class='opts']/a[@class='doDelete']")).click();
		Wait.waitSeconds(5);
		
	}
	
	public void clickEditBtn(WebElement e){
		e.findElement(By.xpath(".//div[@class='opts']/a[@class='doEdit']")).click();
		Wait.waitSeconds(5);
	}
	
	
	public void clickAddBtn(WebElement e){
		e.findElement(By.xpath(".//div[@class='opts']/a[@class='doAdd']")).click();
		Wait.waitSeconds(5);
	}
	
	
}

package com.school.test.basicmanage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.DialogPage;
import com.school.page.DivPage;
import com.school.page.SchoolHomepage;
import com.school.page.SchoolLogin;
import com.school.page.basicmanage.BasicInformation;
import com.school.page.basicmanage.DepartmentManage;
import com.school.util.Wait;
import com.school.util.WebDriverGenerator;

public class DepartmentManageTest {
  
	private WebDriver driver;
	private DepartmentManage department;
	
	  @BeforeTest
	  @Parameters({"host_url","user_name","pass_word"})
	  public void beforeTest(String host_url,String user_name,String pass_word) {
		  this.driver=WebDriverGenerator.generateWebDriver();
		  SchoolLogin login=new SchoolLogin(driver);
		  driver=login.loginSchool(host_url, user_name, pass_word);
		  
		  SchoolHomepage home=new SchoolHomepage(driver);
		  home.enterTopMenu("基础管理");
		  
		  BasicInformation basic=new BasicInformation(driver);
		  basic.enterLeftNav("部门管理");
		  
		  this.department=new DepartmentManage(driver);
	  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
	
	  /**
	   * 添加部门
	   * @param dept_name
	   */
	  
	  @Test(alwaysRun=true)
	  @Parameters({"dept_name"})
	  public void addDeparment(String dept_name){		  	
		  this.department.clickAddDeptBtn();
		  
		  DivPage page=new DivPage(driver);
		  page.inputDeptName(dept_name);
		  page.clickConfirmBtn();
		  
		  DialogPage d=new DialogPage(driver);
		  Assert.assertEquals(d.getTip(), "您已成功添加部门："+dept_name);
		  Wait.waitSeconds(5);
		  Assert.assertNotNull(this.department.getDepartment(dept_name));
  
	  }
	
	  /**
	   * 删除部门
	   * @param dept_name
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"dept_name"})
	  public void deleteDeparment(String dept_name){
		  WebElement dept=this.department.getDepartment(dept_name);
		  
		  this.department.clickDeleteBtn(dept);
		  
		  DialogPage d=new DialogPage(driver);
		  Assert.assertEquals(d.getTip(), "确认删除部门");
		  d.confirm();
		  Assert.assertEquals(d.getTip(), "您已成功删除部门");
	  }
	  
	  /**
	   * 删除带成员的部门
	   * @param dept_name
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"dept_name"})
	  public void deleteDeparmentWithMembers(String dept_name){
		  WebElement dept=this.department.getDepartment(dept_name);
		  
		  this.department.clickDeleteBtn(dept);
		  
		  DialogPage d=new DialogPage(driver);
		  Assert.assertEquals(d.getTip(), "确认删除部门");
		  d.confirm();
		  Assert.assertEquals(d.getTip(), "当前部门有成员信息，不能删除！");
	  }
	  
	  /**
	   * 编辑部门
	   * @param dept_name
	   * @param new_dept_name
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"dept_name","new_dept_name"})
	  public void editDepartment(String dept_name,String new_dept_name){
		  WebElement dept=this.department.getDepartment(dept_name);
		  this.department.clickEditBtn(dept);
		  
		  DivPage page=new DivPage(driver);

		  page.inputDeptName(new_dept_name);
		  page.clickConfirmBtn();
		  
		  Wait.waitSeconds(5);
		  
		  Assert.assertNotNull(this.department.getDepartment(new_dept_name));
		  
	  }
	  
	  /**
	   * 添加子部门
	   * @param dept_name
	   * @param sub_dept_name
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"dept_name","sub_dept_name"})
	  public void addSubDepartment(String dept_name,String sub_dept_name){

		  WebElement dept=this.department.getDepartment(dept_name);
		  this.department.clickAddBtn(dept); 

		  DivPage page=new DivPage(driver);
		  page.inputDeptName(sub_dept_name);
		  page.clickConfirmBtn();
		  
		  DialogPage d=new DialogPage(driver);
		  Assert.assertEquals(d.getTip(), "您已成功添加部门："+sub_dept_name);
 
	  }
	  
	  
	  
}

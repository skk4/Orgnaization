package com.school.test.group;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.DialogPage;
import com.school.page.SchoolHomepage;
import com.school.page.SchoolLogin;
import com.school.page.group.GroupPage;
import com.school.util.Wait;
import com.school.util.WebDriverGenerator;

/**
 * 群组测试
 * @author Administrator
 *
 */
public class GroupTest {

	private GroupPage group;
	private WebDriver driver;
	
  @BeforeTest
  @Parameters({"host_url","user_name","pass_word"})
  public void beforeTest(String host_url,String user_name,String pass_word) {
	  this.driver=WebDriverGenerator.generateWebDriver();
	  SchoolLogin login=new SchoolLogin(driver);
	  driver=login.loginSchool(host_url, user_name, pass_word);
	  Wait.waitSeconds(5);
	  SchoolHomepage homepage=new SchoolHomepage(driver);
	  driver=homepage.enterTopMenu("群 组");
	  
	  this.group=new GroupPage(driver);
	  Wait.waitSeconds(5);
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  /**
   * 添加学员
   */
  @Test(alwaysRun=true)
  @Parameters({"grade_name","class_name","student_name","group_name"})
  public void addStudent(String grade_name,String class_name,String student_name,String group_name){
	  this.group.enterNavMenu("学员管理");
	  driver=this.group.clickAddStudentBtn();
	  this.group.inputGrade(grade_name);
	  
	  this.group.inputClass(class_name);
	  
	  this.group.chooseStudent(student_name);
	  
	  this.group.select();
	  
	  this.group.inputGroup(group_name);
	  
	  this.group.addStudentConfirmBtn();
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "群成员添加成功");
	  d.confirm();
	  
  }
  
  /**
   * 导出名单
   */
  @Test(alwaysRun=true)
  @Parameters({"group_name"})
  public void exportStudentsList(String group_name){
	  
	  this.group.enterNavMenu("学员管理");
	  
	  this.group.chooseGroup(group_name);
	  
	  this.group.clickExportListBtn();
	  
	  
  }
  
  /**
   * 重命名群
   */
  @Test(alwaysRun=true)
  @Parameters({"group_name","new_group_name"})
  public void renameGroupName(String group_name,String new_group_name){
	  this.group.enterNavMenu("学员管理");
	  
	  WebElement finedgroup=this.group.findGroup(group_name);
	  this.group.renameGroup(finedgroup, new_group_name);
	  this.group.inputNewGroup(new_group_name);
	  
	  this.group.confirm();
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "群修改成功");
	  d.confirm();
	  
  }
  
  /**
   * 删除群
   */
  @Test(alwaysRun=true)
  @Parameters({"group_name"})
  public void deleteGroup(String group_name){
	  this.group.enterNavMenu("学员管理");
	  
	  WebElement finedgroup=this.group.findGroup(group_name);
	  this.group.deleteGroup(finedgroup);
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "确认删除该群,群学员将会解散?");
	  d.confirm();
  }
  
  
  /**
   * 查看学生情况
   */
  @Test(alwaysRun=true)
  @Parameters({"group_name","student_name"})
  public void checkStudent(String group_name,String student_name){
	  this.group.enterNavMenu("学员管理");
	  
	  this.group.findGroup(group_name).click();
	  Wait.waitSeconds(5);
	  WebElement student=this.group.findStudent(student_name);
	  
	  this.group.clickStudentDetailsBtn(student);
	  
	  
  }
  
  
  /**
   * 移除学生
   */
  @Test(alwaysRun=true)
  @Parameters({"group_name","student_name"})
  public void removeStudent(String group_name,String student_name){
	  this.group.enterNavMenu("学员管理");
	  
	  this.group.findGroup(group_name).click();
	  Wait.waitSeconds(5);
	  WebElement student=this.group.findStudent(student_name);
	  this.group.clickRemoveStudentBtn(student);
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "确认移除该学员吗?");
	  d.confirm();
	  
	  Assert.assertEquals(d.getTip(), "学员移除成功");
	  d.confirm();
  }
  

  
  
  

}

package com.school.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.PersonalCenter;
import com.school.page.SchoolHomepage;
import com.school.page.SchoolLogin;
import com.school.util.Wait;
import com.school.util.WebDriverGenerator;

public class PersonalCenterTest {

  public WebDriver driver;
  private PersonalCenter person;
  
  @BeforeTest
  @Parameters({"host_url","user_name","pass_word"})
  public void beforeTest(String host_url,String user_name,String pass_word) {
	  this.driver=WebDriverGenerator.generateWebDriver();
	  SchoolLogin login=new SchoolLogin(driver);
	  driver=login.loginSchool(host_url, user_name, pass_word);
	  SchoolHomepage homepage=new SchoolHomepage(driver);
	  driver=homepage.enterPersonalDetail();
	  this.person=new PersonalCenter(driver);
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  @Test(alwaysRun=true)
  @Parameters({"personal_name","personal_sex"})
  public void personalDetails(String personal_name,String personal_sex){
	  Wait.waitSeconds(5);
	  this.person.updateName(personal_name);
	  this.person.updateSex(personal_sex);
	  this.person.clickSubmit();
	  
	 Assert.assertEquals(this.person.getMemName(), personal_name);  
  }
  
  /**
   * 手机绑定
   */
  @Test(alwaysRun=true)
  @Parameters({"mobile_number"})
  public void bandingMobile(String mobile_number){
	  Wait.waitSeconds(5);
	  this.person.enterSecurityCenter();
	  
	  //点击绑定按钮
	  this.person.clickBindMobileBtn();
	  
	  this.person.inputMobileNumber(mobile_number);
	  
	  //获取验证码
	  String code=this.person.clickFetchCodeBtn(mobile_number);
	  
	  Assert.assertEquals(this.person.getphoneTip(), "已向 "+mobile_number+"发送验证短信！");
	  
	  this.person.inputCode(code);
	  
	  this.person.clickMobileConfirmBtn();
	  
  }
  
  
  /**
   * 绑定邮箱
   */
  @Test(alwaysRun=true)
  @Parameters({"email"})
  public void bandingEmail(String email){
	  Wait.waitSeconds(5);
	  this.person.enterSecurityCenter();
	  
	  //点击绑定按钮
	  this.person.clickBindEmailBtn();
	  this.person.inputEmail(email);
	  
	  this.person.clickEmailConfirmBtn();
	  //这行不休眠	
	  WebDriverWait wait=new WebDriverWait(driver,10);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']")));
	  Assert.assertEquals(this.person.getTip(), "绑定信息已经发送到您的邮箱，请尽快登录邮箱确认~");
	  
  }
  
  /**
   * 修改密码
   */
  @Test(alwaysRun=true)
  @Parameters({"old_pwd","new_pwd"})
  public void resetPassword(String old_pwd,String new_pwd){
	  Wait.waitSeconds(5);
	  this.person.enterSecurityCenter();
	  
	  //点击修改密码按钮
	  this.person.clickResetPwdBtn();
	  
	  
	  this.person.inputOldpwd(old_pwd);
	  
	  this.person.inputNewpwd(new_pwd);
	  
	  this.person.confirmNewpwd(new_pwd);
	  
	  this.person.clickResetPwdConfirmBtn();
	  
	  WebDriverWait wait=new WebDriverWait(driver,10);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[starts-with(@id,'layui-layer') and @type='dialog']")));
	  Assert.assertEquals(this.person.getTip(), "密码修改成功");
  }
  
  
  
  
  
  
  

}

package com.school.test;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.SchoolHomepage;
import com.school.page.SchoolLogin;
import com.school.util.WebDriverGenerator;

public class SchoolLoginTest {

  public WebDriver driver;
  public SchoolLogin login;
  
  

  @DataProvider(name = "data")
  public Object[][] data(){
	 return new Object[][]{{"http://localhost:4444/wd/hub", BrowserType.FIREFOX,"46.0.1",Platform.WIN10,"http://school.gray.yoya.com/","t-daida-00001","123456","小呆呆02"},
          {"http://localhost:4444/wd/hub", BrowserType.CHROME,"49.0.2623.110",Platform.WIN10,"http://school.gray.yoya.com/","t-daida-00001","123456","小呆呆02"},
          {"http://localhost:4444/wd/hub", BrowserType.IE,"11.0.10240.17022",Platform.WIN10,"http://school.gray.yoya.com/","t-daida-00001","123456","小呆呆02"}};
  }
  
  @BeforeTest
  @Parameters({"host_url"})
  public void beforeTest(String host_url) {
	  this.driver=WebDriverGenerator.generateWebDriver();
	  this.login=new SchoolLogin(driver);
	  this.login.lauchSite(host_url);
  }

  @AfterTest
  public void afterTest() {
	 driver.quit();
  }

  
  /**
   * 退出登陆
   * @param user_name
   * @param pass_word
   */
  @Test(alwaysRun=true)
  @Parameters({"user_name","pass_word"})
  public void logout(String user_name,String pass_word){
	 
	  this.login.inputUsername(user_name);
	  this.login.inputPassword(pass_word);
	  this.login.clickloginButton();
	  this.login.clicklogoutButton();
	  //退出登录，断言
	  Assert.assertEquals(this.login.getTitle(), "互动微课教学平台 - 登录");
  }
  
  
  
  
  
  /**
   * 正常登陆
   * @param user_name
   * @param pass_word
   */
  @Test(alwaysRun=true)
  @Parameters({"user_name","pass_word","name"})
  public void loginNormal(String user_name,String pass_word,String name){
	  
	  
	  //输入用户名
	  this.login.inputUsername(user_name);
	  //输入密码
	  this.login.inputPassword(pass_word);
	  //提交

	  driver=this.login.clickloginButton();
	  
	  SchoolHomepage homepage=new SchoolHomepage(driver);
	  //断言用户名
	  Assert.assertEquals(homepage.getUsername(), name);
	  
	  
  }
  
  /**
   * 空用户名
   */
  @Test(alwaysRun=true)
  @Parameters({"pass_word"})
  public void loginNullUsername(String pass_word){
	//输入用户名
	  this.login.inputUsername("");
	  //输入密码
	  this.login.inputPassword(pass_word);
	  //提交
	  driver=this.login.clickloginButton();
	  
	  //断言
	  Assert.assertEquals(this.login.getErrorUsername(), "用户名不能为空");
  }
  
  /**
   * 空密码
   */
  @Test(alwaysRun=true)
  @Parameters({"user_name"})
  public void loginNullpassword(String user_name){
	//输入用户名
	  this.login.inputUsername(user_name);
	  //输入密码
	  this.login.inputPassword("");
	  //提交
	  driver=this.login.clickloginButton();
	  
	  //断言
	  Assert.assertEquals(this.login.getErrorPassword(), "登录密码不能为空");
  }
  
  /**
   * 用户名不存在
   */
  @Test(alwaysRun=true)
  @Parameters({"user_name"})
  public void loginWrUsername(String user_name){
	//输入用户名
	  this.login.inputUsername(user_name);
	  //输入密码
	  this.login.inputPassword("123456");
	  //提交
	  driver=this.login.clickloginButton();
	  
	  //断言
	  Assert.assertEquals(this.login.getErrorMsg(), "您输入的用户名不存在，请重新输入");
	  
	  
  }
  
  /**
   * 密码错误
   */
  @Test(alwaysRun=true)
  @Parameters({"pass_word"})
  public void loginWrpassword(String pass_word){
	//输入用户名
	  this.login.inputUsername("t-mgdz3-00001");
	  //输入密码
	  this.login.inputPassword(pass_word);
	  //提交
	  driver=this.login.clickloginButton();
	  
	  //断言
	  Assert.assertEquals(this.login.getErrorMsg(), "密码错误，出错5次后帐号将锁定");
  }
  
  
  
  

}

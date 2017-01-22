package com.school.test.theme;

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
import com.school.page.theme.ThemeActivity;
import com.school.util.Wait;
import com.school.util.WebDriverGenerator;

public class ThemeActivityTest {

	private WebDriver driver;
	private ThemeActivity activity;
	
  @BeforeTest
  @Parameters({"host_url","user_name","pass_word"})
  public void beforeTest(String host_url,String user_name,String pass_word) {
	  this.driver=WebDriverGenerator.generateWebDriver();
	  SchoolLogin login=new SchoolLogin(driver);
	  driver=login.loginSchool(host_url, user_name, pass_word);
	  Wait.waitSeconds(5);
	  SchoolHomepage homepage=new SchoolHomepage(driver);
	  driver=homepage.enterTopMenu("主题活动");
	  
	  this.activity=new ThemeActivity(driver);
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
  /**
   * 发布活动并查看未过期的活动
   * @param theme_title
   * @param activity_desc
   * @param appendix_url
   * @param group_name
   * @param deadline
   */
  @Test(alwaysRun=true)
  @Parameters({"theme_title","activity_desc","appendix_url","group_name","deadline"})
  public void realeaseActivity(String theme_title,String activity_desc,String appendix_url,String group_name,String deadline){
	  
	  driver=this.activity.enterNavMenu("发布活动");
	  this.activity.inputThemeTitle(theme_title);
	  this.activity.inputDescritions(activity_desc);
	  this.activity.uploadFile(appendix_url);
	  
	  Assert.assertEquals(this.activity.getUploadState(), "上传成功！");
	  
	  this.activity.inputGroup(group_name);
	  this.activity.setDeadline(deadline);
	  this.activity.confirm();
	  
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "活动发布成功后，将无法修改，是否确认发布！");
	  d.confirm();
	  
	  Wait.waitSeconds(5);
	  
	  Assert.assertEquals(d.getTip(), "活动发布成功");
	  d.confirm();
	  
	  Wait.waitSeconds(5);
	  
	  driver=this.activity.enterNavMenu("活动列表");
	  
	  Wait.waitSeconds(5);
	  WebElement activity=this.activity.findActivity(theme_title);
	  this.activity.viewActivityDetails(activity);
	  Wait.waitSeconds(5);
	  Assert.assertEquals(this.activity.getActivityTitle(),theme_title );
	  
  }
  
  
  /**
   * 查看已过期的活动详情
   * @param activity_name
   */
  @Test(alwaysRun=true)
  @Parameters({"activity_name"})
  public void reviewexpiryActiviry(String activity_name){
	  
	  driver=this.activity.enterNavMenu("活动列表");
	  Wait.waitSeconds(5);
	  this.activity.enterHistoryActivity();
	  
	  WebElement activity=this.activity.findActivity(activity_name);
	  this.activity.viewActivityDetails(activity);
	  Wait.waitSeconds(5);
	  Assert.assertEquals(this.activity.getActivityTitle(),activity_name );
	  
  }
  
 
  

}

package com.school.test.message;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.SchoolHomepage;
import com.school.page.SchoolLogin;
import com.school.page.message.MessagePage;
import com.school.util.RemoteWebDriverGenerator;

public class MessageTest {

	private WebDriver driver;
	private MessagePage message;
  @BeforeTest
  @Parameters({"host_url","user_name","pass_word"})
  public void beforeTest(String host_url,String user_name,String pass_word) {
	  this.driver=RemoteWebDriverGenerator.generateRemoteWebDriver();
	  SchoolLogin login=new SchoolLogin(driver);
	  this.driver=login.loginSchool(host_url, user_name, pass_word);
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

  
  @Test(alwaysRun=true)
  public void assertMessagePage() {
	  
	  SchoolHomepage home=new SchoolHomepage(driver);
	  driver=home.enterMessage();
	  message=new MessagePage(driver);
	  Assert.assertEquals(message.getMessageCenterText(), "消息中心");
	  
	  Assert.assertTrue(message.getCommentText().contains("评论"));
	  
	  Assert.assertTrue(message.getLikeText().contains("赞"));
	  Assert.assertTrue(message.getNoticeText().contains("通知"));
	  
	  
  }
  
  
  
  
}

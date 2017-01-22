package com.school.test.basicmanage;

import org.openqa.selenium.WebDriver;
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
import com.school.util.Wait;
import com.school.util.WebDriverGenerator;

public class BasicInformationTest {

	private WebDriver driver;
	private BasicInformation basic;
	  @BeforeTest
	  @Parameters({"host_url","user_name","pass_word"})
	  public void beforeTest(String host_url,String user_name,String pass_word) {
		  this.driver=WebDriverGenerator.generateWebDriver();
		  SchoolLogin login=new SchoolLogin(driver);
		  driver=login.loginSchool(host_url, user_name, pass_word);
		  
		  SchoolHomepage home=new SchoolHomepage(driver);
		  home.enterTopMenu("基础管理");
		  
		  this.basic=new BasicInformation(driver);
		  
	  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }



  @Test(alwaysRun=true)
  @Parameters({"school_jc","logo_url"})
  public void basicVerify(String school_jc,String logo_url) {
	  Assert.assertEquals(this.basic.getOrgType(), "企业");
	  Assert.assertEquals(this.basic.getOrgName(), "mgdz3");
	  
	  this.basic.clickEditBtn();
	  this.basic.inputJC(school_jc);
	  this.basic.clickSaveBtn();
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "修改成功!");
	  
	  Assert.assertEquals(this.basic.getOrgCodeUrl(), "http://org.test.yoya.com/mgdz3");
	  Assert.assertEquals(this.basic.getUserLimit(), "23/88888");
	  
	  Assert.assertEquals(this.basic.getEmpNum(), "4人");
	  Assert.assertEquals(this.basic.getStuNum(), "19个");
	  Wait.waitSeconds(5);
	  this.basic.clickUploadBtn();
	  
	  DivPage page=new DivPage(driver);
	  page.uploadLogo(logo_url);
	  page.clickSaveBtn();
	  Assert.assertEquals(d.getTip(), "上传成功！");
	  
	  
	 Assert.assertEquals(this.basic.getInviteCode(), "38407");
	 
	 Assert.assertNotNull(this.basic.getQRcode());
	  
	  
  }
  
  
}

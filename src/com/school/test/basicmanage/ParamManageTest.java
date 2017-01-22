package com.school.test.basicmanage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.SchoolHomepage;
import com.school.page.SchoolLogin;
import com.school.page.basicmanage.BasicInformation;
import com.school.page.basicmanage.ParametersManage;
import com.school.util.WebDriverGenerator;

public class ParamManageTest {
 
	private WebDriver driver;
	private ParametersManage params;
	
	  @BeforeTest
	  @Parameters({"host_url","user_name","pass_word"})
	  public void beforeTest(String host_url,String user_name,String pass_word) {
		  this.driver=WebDriverGenerator.generateWebDriver();
		  SchoolLogin login=new SchoolLogin(driver);
		  driver=login.loginSchool(host_url, user_name, pass_word);
		  
		  SchoolHomepage home=new SchoolHomepage(driver);
		  home.enterTopMenu("基础管理");
		  
		  BasicInformation basic=new BasicInformation(driver);
		  basic.enterLeftNav("参数管理");
		  
		  this.params=new ParametersManage(driver);
	  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
	
	@Test
  public void f() {
  }
}

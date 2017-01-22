package com.school.test.basicmanage;

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
import com.school.page.basicmanage.AuthorityManage;
import com.school.page.basicmanage.BasicInformation;
import com.school.util.Wait;
import com.school.util.WebDriverGenerator;

public class AuthorityManageTest {
 
	private WebDriver driver;
	private AuthorityManage authority;
	  @BeforeTest
	  @Parameters({"host_url","user_name","pass_word"})
	  public void beforeTest(String host_url,String user_name,String pass_word) {
		  this.driver=WebDriverGenerator.generateWebDriver();
		  SchoolLogin login=new SchoolLogin(driver);
		  driver=login.loginSchool(host_url, user_name, pass_word);
		  
		  SchoolHomepage home=new SchoolHomepage(driver);
		  home.enterTopMenu("基础管理");
		  
		  BasicInformation basic=new BasicInformation(driver);
		  basic.enterLeftNav("权限管理");
		  
		  this.authority=new AuthorityManage(driver);
		  
	  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
	
/**
 * 移除管理员	
 * @param account_id
 */
  @Test(alwaysRun=true)
  @Parameters({"account_id"})
  public void removeAdmin(String account_id) {
	  
	  WebElement admin=this.authority.findAdminAccount(account_id);
	  String adminName=this.authority.getAdminName(admin);
	  
	   admin=this.authority.findAdminAccount(account_id);
	  this.authority.clickRemoveAdmin(admin);

	  DialogPage d=new DialogPage(driver);
	  
	  Assert.assertEquals(d.getTip(), "确定移除"+adminName+"的管理员身份吗？");
	  
	  d.confirm();
	  
	  Wait.waitSeconds(5);

	  Assert.assertNull(this.authority.findAdminAccount(account_id));
  }
  
  /**
   * 只能对一级部门的人员进行添加管理的操作
   */
  @Test(alwaysRun=true)
  @Parameters({"admin_name","account_id"})
  public void addAdmin(String admin_name,String account_id){
	  this.authority.clickAddAdminBtn();
	  this.authority.chooseDepartment();
	  this.authority.chooseUser(admin_name);
	  this.authority.clickAddAdminConfirm();
	  
	  Assert.assertNotNull(this.authority.findAdminAccount(account_id));
  }
  
}

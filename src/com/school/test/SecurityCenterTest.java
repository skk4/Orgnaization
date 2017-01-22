package com.school.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.DialogPage;
import com.school.page.SecurityCenter;
/**
 * 安全中心
 * @author Administrator
 *
 */
public class SecurityCenterTest {
		public WebDriver driver;
		private SecurityCenter security;
		
	  @BeforeTest
	  @Parameters({"host_url","user_name","pass_word"})
	  public void beforeTest(String host_url,String user_name,String pass_word) {
		 
		  
	  }

	  @AfterTest
	  public void afterTest() {
		  
		  driver.quit();
	  }
	    
	  /**
	   * 绑定邮箱
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"email_addr",""})
	  public void bindEmail(String email_addr){
		  this.security.clickBindEmailBtn();
		  this.security.inputEmail(email_addr);
		  this.security.submitBind();
		  
	  }
	  
	  
	  /**
	   * 绑定空邮箱
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"email_addr",""})
	  public void bindNullEmail(String email_addr){
		  this.security.clickBindEmailBtn();
		  this.security.inputEmail(email_addr);
		  this.security.submitBind();
		  
		  //f断言
	  }
	  
	  
	  /**
	   * 绑定错误邮箱
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"email_addr",""})
	  public void bindWrongEmail(String email_addr){
		  this.security.clickBindEmailBtn();
		  this.security.inputEmail(email_addr);
		  this.security.submitBind();
		  //断言
	  }
	  
	  /**
	   * 重复绑定同一个邮箱
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"email_addr",""})
	  public void bindSameEmail(String email_addr){
		  this.security.clickBindEmailBtn();
		  this.security.inputEmail(email_addr);
		  this.security.submitBind();
		  //断言
	  }
	  
	  
	  
	  /**
	   * 取消绑定邮箱
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"email_addr",""})
	  public void cancelbindEmail(String email_addr){
		  this.security.clickBindEmailBtn();
		  this.security.inputEmail(email_addr);
		  this.security.cancelBindEmail();
		  
	  }
	  
	  
	  /**
	   * 更新密码
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"old_password","new_password"})
	  public void updatePassword(String old_password,String new_password){
		
		 this.security.clickModifypasswordBtn(); 
		 this.security.inputOldpassword(old_password);
		 this.security.inputNewpassword(new_password);
		 this.security.confirmNewpassword(new_password);
		  
		 this.security.submitModify();
		  
		 DialogPage dialog=new DialogPage(driver);
		  Assert.assertEquals(dialog.getTip(), "密码修改成功");
		  dialog.confirm();
		  
		  
	  }
	  
	  
	  /**
	   * 新旧密码相同
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"old_password","new_password"})
	  public void updateSamePassword(String old_password,String new_password){
		
		 this.security.clickModifypasswordBtn(); 
		 this.security.inputOldpassword(old_password);
		 this.security.inputNewpassword(new_password);
		 this.security.confirmNewpassword(new_password);
		  
		 this.security.submitModify();
		  
		 //断言
		    
	  }
	  
	  
	  /**
	   * 旧密码错误
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"old_password","new_password"})
	  public void updateWrongOldPassword(String old_password,String new_password){
		
		 this.security.clickModifypasswordBtn(); 
		 this.security.inputOldpassword(old_password);
		 this.security.inputNewpassword(new_password);
		 this.security.confirmNewpassword(new_password);
		  
		 this.security.submitModify();
		  
		 //断言
		    
	  }

	  
	  /**
	   * 新密码不一样
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"old_password","new_password"})
	  public void updateWrongNewPassword(String old_password,String new_password){
		
		 this.security.clickModifypasswordBtn(); 
		 this.security.inputOldpassword(old_password);
		 this.security.inputNewpassword(new_password);
		 this.security.confirmNewpassword(new_password);
		  
		 this.security.submitModify();
		  
		 //断言
		    
	  }
	  
	  /**
	   * 原密码为空
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"old_password","new_password"})
	  public void updateNullOldpassword(String old_password,String new_password){
		  this.security.clickModifypasswordBtn(); 
			 this.security.inputOldpassword(old_password);
			 this.security.inputNewpassword(new_password);
			 this.security.confirmNewpassword(new_password);
			  
			 this.security.submitModify();
			  
			 //断言
	  }
	  
	  /**
	   * 新密码为空
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"old_password","new_password"})
	  public void updateNullNewpassword(String old_password,String new_password){
		  this.security.clickModifypasswordBtn(); 
			 this.security.inputOldpassword(old_password);
			 this.security.inputNewpassword(new_password);
			 this.security.confirmNewpassword(new_password);
			  
			 this.security.submitModify();
			  
			 //断言
	  }
	  
	  
	  /**
	   * 取消修改密码
	   */
	  @Test(alwaysRun=true)
	  @Parameters({"old_password","new_password"})
	  public void cancelUpdatepassword(String old_password,String new_password){
		  this.security.clickModifypasswordBtn(); 
			 this.security.inputOldpassword(old_password);
			 this.security.inputNewpassword(new_password);
			 this.security.confirmNewpassword(new_password);
			 this.security.cancelModify();
	  }
	  
	  
	  

}

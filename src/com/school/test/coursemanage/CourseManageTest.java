package com.school.test.coursemanage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.DialogPage;
import com.school.page.DivPage;
import com.school.page.SchoolLogin;
import com.school.page.coursemanage.Course;
import com.school.util.Wait;
import com.school.util.WebDriverGenerator;


/**
 * 课程管理
 * @author Administrator
 */
public class CourseManageTest {
  
	private WebDriver driver;
	private Course course;
	
  @BeforeTest
  @Parameters({"host_url","user_name","pass_word"})
  public void beforeTest(String host_url,String user_name,String pass_word) {
	  this.driver=WebDriverGenerator.generateWebDriver();
	  SchoolLogin login=new SchoolLogin(driver);
	  driver=login.loginSchool(host_url, user_name, pass_word);
	  
	  
	  this.course=new Course(driver);
	  this.course.enterNavCourseManage();
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

  /**
   * 新增课程
   * @param course_name
   * @param course_desc
   * @param pic_url
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","course_desc","pic_url"})
  public void addCourse(String course_name,String course_desc,String pic_url) {
	  //点击新增课程按钮
	  this.course.clickAddCourseBtn();
	  
	  //输入名称
	  this.course.inputCourseName(course_name);
	  
	  this.course.inputCourseDesc(course_desc);
	  
	  this.course.uploadCover();
	  
	  DivPage page=new DivPage(driver);
	  page.clickUploadCover(pic_url);
	  page.clickSaveBtn();
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "上传成功！");
	  d.confirm();
	  Wait.waitMilliSeconds(3000);
	  //下一步
	  this.course.clickNextBtn();
	  Wait.waitMilliSeconds(5000);
	  this.course.clickcourseFinishBtn();
	  
	  Wait.waitMilliSeconds(5000);
	  
	  Assert.assertNotNull(this.course.findCourse(course_name));
	  
  }
  
  
  /**
   * 编辑课程信息
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","new_course_name","course_desc","pic_url"})
  public void editAndSaveCourse(String course_name,String new_course_name,String course_desc,String pic_url){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  
	  //点击编辑按钮
	  this.course.clickEditCourseBtn();
	  
	  Wait.waitSeconds(5);
	  this.course.inputCourseName(new_course_name);
	  this.course.inputCourseDesc(course_desc);
	  this.course.uploadCover();
	  
	  DivPage page=new DivPage(driver);
	  page.clickUploadCover(pic_url);
	  page.clickSaveBtn();
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "上传成功！");
	  d.confirm();
	  Wait.waitMilliSeconds(3000);
	  //下一步
	  this.course.clickNextBtn();
	  Wait.waitMilliSeconds(5000);
	  
	  this.course.clickcoursePreviewBtn();
	  
	  Wait.waitSeconds(5);
	  
	  //断言封面的存在，新的课程名称，新的简介
	  Assert.assertNotNull(driver.findElement(By.id("course_cover")));
	  Assert.assertEquals(this.course.getCourseName(), new_course_name);
	  Assert.assertEquals(this.course.getCourseDesc(), course_desc);
	  
	  
  }
  
  
  
  
  /**
   * 预览课程
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name"})
  public void previewCourse(String course_name){
	  
	  //已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  this.course.moveToOpsAndClick(course, "预览");
	  
	  Wait.waitMilliSeconds(5000);
	  
	  Assert.assertEquals(this.course.getCourseName(), course_name);
	  
	  
  }
  
  
  /**
   * 编辑
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name"})
  public void editCourse(String course_name){
	  //已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitMilliSeconds(5000);
	  
	  Assert.assertEquals(this.course.getCourseName(), "《"+course_name+"》");
  }
  
  /**
   * 发送
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","group_name"})
  public void sendCourse(String course_name,String group_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  this.course.moveToOpsAndClick(course, "发送");
	  
	  Wait.waitMilliSeconds(5000);
	  
	  DivPage page=new DivPage(driver);
	  
	  page.chooseSendGroup(group_name);
	  page.clickSendBtn();
	  
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "课程发送成功！");
	  d.confirm();
  }
  
  /**
   * 删除
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name"})
  public void deleteCourse(String course_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  this.course.movoToItemsAndClick(course, "删除");
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "确定删除“"+course_name+"”课程（包含相关资源）？");
	  d.confirm();
	  
	  Assert.assertEquals(d.getTip(), "删除成功！");
	  d.confirm();
	  
  }
  
  
  /**
   * 发布
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","first_col","sec_col"})
  public void publishCourse(String course_name,String first_col,String sec_col){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  this.course.movoToItemsAndClick(course, "发布");
	  
	  DivPage page=new DivPage(driver);
	  
	  page.chooseColumns(first_col, sec_col);
	  
	  page.clickPubConfirmBtn();
	  
	 
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "《"+course_name+"》已发布！");
	  d.confirm();
	  
	  driver.navigate().refresh();
	  Assert.assertEquals(this.course.getPubStatus(course_name), "已发布");
	  
	  
	  
  }
  
  /**
   * 取消发布
   */
  
  @Test(alwaysRun=true)
  @Parameters({"course_name"})
  public void cancelpublishCourse(String course_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  this.course.movoToItemsAndClick(course, "取消发布");

	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "确定要取消发布课程《"+course_name+"》吗？");
	  d.confirm();

	  Assert.assertEquals(d.getTip(), "课程《"+course_name+"》已取消发布");
	  d.confirm();
	  
	  
  }
  
  /**
   * 创建同级目录
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","parent_menu","menu_name"})
  public void creatSameMenu(String course_name,String parent_menu,String menu_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  //选择父节点
	  WebElement p=this.course.findMenu(parent_menu);
	  p.click();
	  
	  Wait.waitSeconds(5);
	  this.course.clickAddMenu("同级目录");
	  
	  Wait.waitMilliSeconds(5000);
	  
	  this.course.inputMenuName(menu_name);
	  
	  Wait.waitSeconds(5);
	  WebElement m=this.course.findMenu(menu_name);
	  
	  Assert.assertNotNull(m);
	  
  }
  
  /**
   * 创建子目录
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","parent_menu","menu_name"})
  public void creatChildMenu(String course_name,String parent_menu,String menu_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  //选择父节点
	  WebElement p=this.course.findMenu(parent_menu);
	  p.click();
	  
	  Wait.waitSeconds(5);
	  this.course.clickAddMenu("子目录");
	  
	  Wait.waitMilliSeconds(5000);
	  
	  p=this.course.findMenu(parent_menu);
	  this.course.inputChildMenuName(p, menu_name);
	  
	  Wait.waitSeconds(5);
	  
	  p=this.course.findMenu(parent_menu);
	  WebElement m=this.course.findChildMenu(p, menu_name);
	  
	  Assert.assertNotNull(m);
	  
  }
  
  /**
   * 同级目录
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","menu_name"})
  public void deleteMenu(String course_name,String menu_name){  
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  
	  WebElement menu=this.course.findMenu(menu_name);
	  
	  this.course.clickMenuDeleteBtn(menu);
	  Wait.waitSeconds(5);
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "确定删除当前目录（含子目录和资源）？");
	  d.confirm();
	  
	  Assert.assertEquals(d.getTip(), "删除成功！");
	  d.confirm();  
  }
  
  
  /**
   * 删除子目录
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","parent_menu","menu_name"})
  public void deleteChildMenu(String course_name,String parent_menu,String menu_name){  
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  
	  WebElement menu=this.course.findMenu(parent_menu);
	  
	  this.course.clickChildMenuDeleteBtn(menu, menu_name);
	  Wait.waitSeconds(5);
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "确定删除当前目录（含子目录和资源）？");
	  d.confirm();
	  
	  Assert.assertEquals(d.getTip(), "删除成功！");
	  d.confirm();  
  }
  
  /**
   * 编辑同级目录
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","parent_name","new_name"})
  public void editMenu(String course_name,String parent_name,String new_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  
	  WebElement menu=this.course.findMenu(parent_name);
	  
	  this.course.clickMenuEditBtn(menu);
	  
	  this.course.inputMenuName(new_name);
	  Wait.waitSeconds(5);
	  Assert.assertNotNull(this.course.findMenu(new_name));
	  
  }
  
  
  /**
   * 编辑子目录
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","parent_name","child_name","new_name"})
  public void editChildMenu(String course_name,String parent_name,String child_name,String new_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  
	  WebElement menu=this.course.findMenu(parent_name);

	  this.course.clickChildMenuEditBtn(menu, child_name);
	  
	  menu=this.course.findMenu(parent_name);
	  this.course.inputChildMenuName(menu, new_name);
	  driver.navigate().refresh();
	  Wait.waitSeconds(5);
	  menu=this.course.findMenu(parent_name);
	  Assert.assertNotNull(this.course.findChildMenu(menu, new_name));
	  
  }
  
  
  
  
  /**
   * 本地上传
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","parent_menu","child_menu","resource_url","resource_name"})
  public void upload(String course_name,String parent_menu,String child_menu,String resource_url,String resource_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  
	  //选择目录
	  WebElement parent=this.course.findMenu(parent_menu);
	  WebElement child=this.course.findChildMenu(parent, child_menu);
	  child.click();
	  Wait.waitSeconds(5);
	  
	  //点击上传按钮
	  this.course.clickUploadBtn();
	  
	  this.course.clickChooseFileBtn(resource_url);
	  
	  this.course.clickUploadReadyBtn();
	  
	  Assert.assertNotNull(this.course.findMenuResource(resource_name));
	  
  }
  
  /**
   * 删除内容
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","parent_menu","child_menu","resource_name"})
  public void deleteResource(String course_name,String parent_menu,String child_menu,String resource_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  
	  //选择目录
	  WebElement parent=this.course.findMenu(parent_menu);
	  WebElement child=this.course.findChildMenu(parent, child_menu);
	  child.click();
	  Wait.waitSeconds(5);
	  
	  //查找资源
	  WebElement r=this.course.findMenuResource(resource_name);
	  
	  this.course.clickMenuResourceBtn(r);
	  
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "确定删除该资源？");
	  d.confirm();
	  
  }
  
  /**
   * 插入微课
   */
  @Test(alwaysRun=true)
  @Parameters({"course_name","parent_menu","album_name","micro_c_name"})
  public void addMircoCourse(String course_name,String parent_menu,String album_name,String micro_c_name){
	//已经到课程管理页面
	  WebElement course=this.course.findCourse(course_name);
	  
	  //进入编辑
	  this.course.moveToOpsAndClick(course, "编辑");
	  
	  Wait.waitSeconds(5);
	  
	  //选择目录
	  WebElement parent=this.course.findMenu(parent_menu);
	  parent.click();
	  Wait.waitSeconds(5);
	  
	  this.course.clickSelectInsertBtn();
	  
	  Wait.waitSeconds(5);
	  DivPage page=new DivPage(driver);
	  
	  page.selectAlbum(album_name);
	  
	  page.selectMicroCourse(micro_c_name);
	  
	  page.clickSelectInsBtn();
	 
	  Wait.waitSeconds(5);
	  
  }

  
  
  
  
  
}

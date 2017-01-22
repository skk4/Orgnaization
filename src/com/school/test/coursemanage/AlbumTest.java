package com.school.test.coursemanage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.school.page.DialogPage;
import com.school.page.DivPage;
import com.school.page.SchoolHomepage;
import com.school.page.SchoolLogin;
import com.school.page.coursemanage.Album;
import com.school.page.coursemanage.MicroCourse;
import com.school.util.DriverExchangeAssist;
import com.school.util.Wait;
import com.school.util.WebDriverGenerator;


/**
 * 微课管理
 * @author Administrator
 *
 */
public class AlbumTest {
 
	private WebDriver driver;
	private Album album;
	

  @BeforeTest
  @Parameters({"host_url","user_name","pass_word"})
  public void beforeTest(String host_url,String user_name,String pass_word) {
	  long id = Thread.currentThread().getId();
      System.out.println(" Thread id is: " + id);
	  
	  this.driver=WebDriverGenerator.generateWebDriver();
	  SchoolLogin login=new SchoolLogin(driver);
	  driver=login.loginSchool(host_url, user_name, pass_word);
	  
	  SchoolHomepage homepage=new SchoolHomepage(driver);
	  driver=homepage.enterCourseManage();
	  
	  
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

  
  /**
   * 专辑内制作微课
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name"})
  public void makeMicroCourse(String album_name){
	//进入专辑
	  MicroCourse c=new MicroCourse(driver);
	  driver=c.enterAblum(album_name);
	  
	  //制作微课
	  c.clickMakeCourseBtn();
	  
	  DriverExchangeAssist.exchangeDriverUseTitle(driver, "制作互动电影");
	  Assert.assertEquals(driver.getTitle(), "制作互动电影");
  }
  
  
  
  
  
  /**
   * 上传本地资源
   * @param album_name
   * @param resource_url
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","resource_url"})
  public void uploadLocalResource(String album_name,String resource_url) {
	  //进入专辑
	  MicroCourse c=new MicroCourse(driver);
	  driver=c.enterAblum(album_name);
	  
	  //专辑
	  this.album=new Album(driver);

	  //点击上传资源
	  driver=this.album.clickUploadBtn();
	  
	  //点击浏览，上传文件
	  this.album.sendResourceUrl(resource_url);
	  
	  this.album.clickUploadReadyBtn();

	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "本地资源上传成功！");
	  d.confirm();
	  
	  
  }
  
  /**
   * 预览
   * @param album_name
   * @param resource_name
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","resource_name"})
  public void preview(String album_name,String resource_name){
	  //进入专辑
	  MicroCourse c=new MicroCourse(driver);
	  driver=c.enterAblum(album_name);
	  //专辑
	  this.album=new Album(driver);
	  
	  WebElement resource=this.album.findCourseAndResource(resource_name);
	  
	  //点击预览按钮
	  this.album.clickPreviewBtn(resource);
	  
	  driver=DriverExchangeAssist.exchangeDriverUseTitle(driver, "创新微课教学平台-微课预览");
	  
	  
	  Assert.assertEquals(this.album.getResourseName(), "微课名称："+resource_name);
	  
  }
  
  /**
   * 发送
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","resource_name","group_name"})
  public void send(String album_name,String resource_name,String group_name){
	//进入专辑
	  MicroCourse c=new MicroCourse(driver);
	  driver=c.enterAblum(album_name);
	  //专辑
	  this.album=new Album(driver);
	  WebElement resource=this.album.findCourseAndResource(resource_name);
	  
	  //点击发送按钮
	  this.album.clickSendBtn(resource);
	  
	  DivPage page=new DivPage(driver);
	  page.chooseSendGroup(group_name);
	 
	  
	  page.clickSendBtn();
	  
	  Wait.waitMilliSeconds(5000);
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "微课发送成功！");
	  
	  d.confirm();
	  
	  
  }
  
  /**
   * 重命名
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","resource_name","new_res_name"})
  public void rename(String album_name,String resource_name,String new_res_name){
		// 进入专辑
		MicroCourse c = new MicroCourse(driver);
		driver = c.enterAblum(album_name);
		// 专辑
		this.album = new Album(driver);
		WebElement resource = this.album.findCourseAndResource(resource_name);

		this.album.clickRenameBnt(resource);

		this.album.inputResetName(resource_name, new_res_name);

		driver.navigate().refresh();
		Wait.waitMilliSeconds(5000);
		Assert.assertEquals(this.album.findCourseAndResource(new_res_name).getText(), new_res_name);
	  
  }
  
  /**
   * 移动
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","resource_name","new_album"})
  public void move(String album_name,String resource_name,String new_album){
		// 进入专辑
		MicroCourse c = new MicroCourse(driver);
		driver = c.enterAblum(album_name);
		// 专辑
		this.album = new Album(driver);
		WebElement resource = this.album.findCourseAndResource(resource_name);

		// 点击移动
		this.album.clickMoveBtn(resource);

		// 选择专辑
		this.album.chooseNewAlbum(new_album);

		this.album.clickMoveConfirmBtn();

		DialogPage d = new DialogPage(driver);
		Assert.assertEquals(d.getTip(), "微课移动成功！");
		d.confirm();
  }
  
  /**
   * 删除
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","resource_name"})
  public void delete(String album_name,String resource_name){
		// 进入专辑
		MicroCourse c = new MicroCourse(driver);
		driver = c.enterAblum(album_name);
		// 专辑
		this.album = new Album(driver);
		WebElement resource = this.album.findCourseAndResource(resource_name);

		this.album.clickDeleteBtn(resource);

		DialogPage d = new DialogPage(driver);
		Assert.assertEquals(d.getTip(), "确定要删除微课《" + resource_name + "》吗？删除了就不能恢复了！");
		d.confirm();

		Wait.waitMilliSeconds(5000);

		Assert.assertEquals(d.getTip(), "微课《" + resource_name + "》删除成功！");
		d.confirm();
			
  }
  
  /**
   * 发布
   * @param album_name
   * @param resource_name
   * @param first_column
   * @param sec_column
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","resource_name","first_column","sec_column"})
  public void publish(String album_name,String resource_name,String first_column,String sec_column){
	// 进入专辑
			MicroCourse c = new MicroCourse(driver);
			driver = c.enterAblum(album_name);
			// 专辑
			this.album = new Album(driver);
			WebElement resource = this.album.findCourseAndResource(resource_name);
			
			this.album.clickPubBtn(resource);
			
			this.album.chooseColumns(first_column, sec_column);
			
			this.album.clickPubConfirmBtn();
			
			Wait.waitMilliSeconds(5000);
			
			DialogPage d = new DialogPage(driver);
			Assert.assertEquals(d.getTip(), "《"+resource_name+"》已发布！");
			d.confirm();		
  }
  
  /**
   * 取消发布
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","resource_name"})
  public void cancelPublish(String album_name,String resource_name){
		// 进入专辑
		MicroCourse c = new MicroCourse(driver);
		driver = c.enterAblum(album_name);
		// 专辑
		this.album = new Album(driver);
		WebElement resource = this.album.findCourseAndResource(resource_name);

		// 取消发布
		this.album.clickCancelPubBtn(resource);
		Wait.waitMilliSeconds(5000);

		DialogPage d = new DialogPage(driver);
		Assert.assertEquals(d.getTip(), "确定要取消发布微课《" + resource_name + "》吗？");
		d.confirm();		
		Wait.waitMilliSeconds(5000);	
		Assert.assertEquals(d.getTip(), "《"+resource_name+"》已取消发布！");	
		d.confirm();
  }
  
  
  /**
   * 默认的我的收藏和我的收藏文件夹验证
   */
  @Test(alwaysRun=true)
  public void checkDefaultFileDirectory(){
	  MicroCourse c=new MicroCourse(driver);
	  
	  Assert.assertNotNull(c.findAlbum("我的收藏"));
	  Assert.assertNotNull(c.findAlbum("我的专辑"));
  }
  
  
  
  /**
   * 新建专辑
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name"})
  public void addAlbum(String album_name){
	  MicroCourse c=new MicroCourse(driver);
	  
	  //点击新建专辑按钮
	  c.clickAddAlbum();
	  c.inputAlbumName(album_name);
	  
	  c.clickConfirmBtn();
	  
	  Assert.assertNotNull(c.findAlbum(album_name));
  }
  
  /**
   * 重命名专辑
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","new_album_name"})
  public void renameAlbum(String album_name,String new_album_name){
	  MicroCourse c=new MicroCourse(driver);
	  
	  WebElement album=c.findAlbum(album_name);
	  
	  c.clickRenameBtn(album);
	  
	  c.inputAlbumName(new_album_name);
	  
	  c.clickConfirmBtn();
	  
	  Assert.assertNotNull(c.findAlbum(new_album_name));
  }
  
  
  /**
   * 删除专辑
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name"})
  public void deleteAlbum(String album_name){
	  MicroCourse c=new MicroCourse(driver);
	  
	  WebElement album=c.findAlbum(album_name);
	  
	  c.clickDeleteBtn(album);
	  
	  DialogPage d=new DialogPage(driver);
	  Assert.assertEquals(d.getTip(), "确定删除专辑（相关数据也将被删除）？");
	  
	  d.confirm();
	  
	  Assert.assertNull(c.findAlbum(album_name));
  }
  
  
  /**
   * 发送专辑
   */
  @Test(alwaysRun=true)
  @Parameters({"album_name","group_name"})
  public void sendAlbum(String album_name,String group_name){
	  MicroCourse c=new MicroCourse(driver);
	  
	  WebElement album=c.findAlbum(album_name);
	  
	  c.clickSendBtn(album);
	  
	  Wait.waitSeconds(3);
	  
	  DivPage page=new DivPage(driver);
	  
	  page.chooseGroup(group_name);
	  
	  page.confirmSendBtn();
	  
	  Wait.waitSeconds(3);
	  
	  DialogPage dialog=new DialogPage(driver);
	  Assert.assertEquals(dialog.getTip(), "发送成功！");
  
  }
  
}

package com.school.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
/**
 * 屏幕截屏
 * @author Administrator
 *
 */
public class ScreenShot {

	
	private WebDriver driver;
	
	
	public ScreenShot(WebDriver driver){
		this.driver=driver;
	}
	
	private static void takeScreenshot(String screenPath, WebDriver driver) {
        try {
            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenPath));
        } catch (IOException e) {
        	e.printStackTrace();
            System.out.println("Screen shot error: " + screenPath);
        }

    }

    public  void takeScreenshot(ITestResult tr,WebDriver driver) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String mDateTime = formatter.format(new Date());
        String screenName=mDateTime+"_"+tr.getName()+".jpg";
        File dir = new File("snapshot");
        if (!dir.exists()){
        	dir.mkdirs();
        }
            
        String screenPath = dir.getAbsolutePath() + "\\" + screenName;
        takeScreenshot(screenPath, driver);
    }
	
    
    public WebDriver getDriver(){
    	return driver;
    }
	
	
	

}

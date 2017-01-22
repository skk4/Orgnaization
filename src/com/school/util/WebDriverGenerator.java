package com.school.util;

import java.util.Enumeration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

public class WebDriverGenerator {
	
	
	private static Properties p=null;
	static{
		p=PropertyConfigure.configure("./properties/system.properties");
		
		String browserType=p.getProperty("browser.type");
		
		if(!browserType.equalsIgnoreCase("Firefox")){
			System.setProperty(p.getProperty("browser.driver.type"), p.getProperty("browser.driver.url"));
		}
		System.setProperty(p.getProperty("browser.bin.type"), p.getProperty("browser.bin.url"));
	}
	public static WebDriver generateWebDriver(){

		String browserType=p.getProperty("browser.type");
		WebDriver driver=null;
		if(browserType.equalsIgnoreCase(BrowserType.FIREFOX)){
			
			Properties p=PropertyConfigure.configure("./properties/firefox.properties");
			Enumeration<Object> keys=p.keys();
			FirefoxProfile fxProfile=new FirefoxProfile();
			fxProfile.setAcceptUntrustedCertificates(true);
			
			while(keys.hasMoreElements()){
				String key=(String)keys.nextElement();
				String value=p.getProperty(key);
				fxProfile.setPreference(key, value);
			}
			
			driver=new FirefoxDriver(fxProfile);
						
		}else if(browserType.equalsIgnoreCase(BrowserType.CHROME)){
			driver=new ChromeDriver();
		}else if(browserType.equalsIgnoreCase(BrowserType.IE)){
			driver=new  InternetExplorerDriver();
		}
		return driver;
	}
}




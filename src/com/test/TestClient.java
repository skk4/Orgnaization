package com.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.school.page.SchoolLogin;

public class TestClient {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		DesiredCapabilities ffDesiredcap = DesiredCapabilities.firefox();
		ffDesiredcap.setBrowserName(BrowserType.FIREFOX);
		ffDesiredcap.setVersion("46.0.1");
		ffDesiredcap.setPlatform(Platform.WIN10);
		
		DesiredCapabilities chrome=DesiredCapabilities.chrome();
		chrome.setBrowserName(BrowserType.CHROME);
		chrome.setVersion("49.0.2623.110");
		chrome.setPlatform(Platform.WIN10);
		
		DesiredCapabilities ie=DesiredCapabilities.internetExplorer();
		ie.setBrowserName(BrowserType.IE);
		ie.setVersion("11.0.10240.17022");
		ie.setPlatform(Platform.WIN10);
		
		
		WebDriver wd=null;
		WebDriver wd1=null;
		try {
			wd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), ie);
			wd1=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chrome);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		wd.get("http://school.gray.yoya.com/");
		wd1.get("http://school.gray.yoya.com/");
		wd.manage().window().maximize();
		wd1.manage().window().maximize();
		SchoolLogin login=new SchoolLogin(wd);
		login.inputUsername("t-daida-00001");
		login.inputPassword("123456");
		login.clickloginButton();
		
		
		SchoolLogin login1=new SchoolLogin(wd1);
		login1.inputUsername("t-daida-00001");
		login1.inputPassword("123456");
		login1.clickloginButton();
		
		
		
	}

}

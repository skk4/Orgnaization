package com.school.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteWebDriverGenerator {

	
	public static WebDriver generateRemoteWebDriver(){
		
		DesiredCapabilities desiredcap = DesiredCapabilities.firefox();

		desiredcap.setBrowserName(BrowserType.FIREFOX);
		desiredcap.setVersion("46");
		desiredcap.setPlatform(Platform.WIN10);
		WebDriver driver=null;
		
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredcap);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return driver;
	}
	
}

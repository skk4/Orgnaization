package com.school.util;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WebDriverNode {
	
	@Test(alwaysRun=true)
	@Parameters({"node_URL","browser_type","version","host_url"})
	public void testFirefox(String node_URL,String browser_type,String version,String host_url){
		long id = Thread.currentThread().getId();
        System.out.println("Thread id is: " + id);
		System.out.println("testFirefox.....");
	}
	
	@Test(alwaysRun=true)
	@Parameters({"node_URL","browser_type","version","host_url"})
	public void testChrome(String node_URL,String browser_type,String version,String host_url){
		long id = Thread.currentThread().getId();
        System.out.println("Thread id is: " + id);
		System.out.println("testChrome.....");
	}
	
	
}

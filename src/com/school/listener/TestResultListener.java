package com.school.listener;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.school.util.ScreenShot;



public class TestResultListener extends TestListenerAdapter  {

	
	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		super.onTestFailure(arg0);
		Object o=arg0.getInstance();
		Class c=o.getClass();
		WebDriver driver=null;
		
		try {
			Field  f=c.getDeclaredField("driver");
			f.setAccessible(true);
			driver=(WebDriver)f.get(o);
		} catch (NoSuchFieldException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//通过调用方法返回WebDriver
/*		try {
			Method m=c.getMethod("getDriver");
			driver=(WebDriver)m.invoke(o);
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		ScreenShot shot=new ScreenShot(driver);
		shot.takeScreenshot(arg0,shot.getDriver());
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		super.onTestSkipped(arg0);
		this.onTestFailure(arg0);
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		super.onTestSuccess(arg0);
	}
}

package com.school.page;

import org.openqa.selenium.WebElement;

public class Dialog {
	private String dialogTitle;
	private WebElement close;
	private WebElement cancel;
	private WebElement confirm;
	
	public Dialog(String dialogTitle,WebElement close,WebElement cancel,WebElement confirm){
		this.dialogTitle=dialogTitle;
		
	}

}

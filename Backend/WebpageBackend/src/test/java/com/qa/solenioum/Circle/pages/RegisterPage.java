package com.qa.solenioum.Circle.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {
	
	@FindBy(id = "rusername")
	private WebElement username;
	
	@FindBy(id = "rpassword")
	private WebElement password;
	
	@FindBy(id = "remail")
	private WebElement email;
	
	@FindBy(id = "submit")
	private WebElement submit;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	private WebElement status;
	
	public RegisterPage() {}
	
	public void RegisterUser(String username, String password, String email) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.email.sendKeys(email);
		this.submit.click();
	}
	
	public String getStatus() {
		return this.status.getText();
	}	

}

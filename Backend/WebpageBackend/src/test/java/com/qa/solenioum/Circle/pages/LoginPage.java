package com.qa.solenioum.Circle.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	public RegisterPage registerpage;

	@FindBy(id = "lusername")
	private WebElement username;
	
	@FindBy(id = "lpassword")
	private WebElement password;
	
	@FindBy(id = "submit")
	private WebElement submit;
	
	@FindBy(xpath = "/html/body/div/fieldset/form/a")
	private WebElement navRegisterPage;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	private WebElement status;
	
	public LoginPage() {}
	
	public void navRegisterPage() {
		navRegisterPage.click();
	}
	
	public void loginUser(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		
		this.submit.click();
	}
	
	public String getStatus() {
		return this.status.getText();
	}	
}

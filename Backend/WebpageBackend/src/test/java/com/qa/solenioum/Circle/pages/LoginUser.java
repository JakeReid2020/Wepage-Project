package com.qa.solenioum.Circle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginUser {
	
	public static String URL = "http://thedemosite.co.uk/addauser.php";
	
	@FindBy(xpath = "/html/body/div/fieldset/form/input[1]")
	private WebElement usernameInput;
	
	@FindBy(xpath = "/html/body/div/fieldset/form/input[2]")
	private WebElement passwordInput;
	
	@FindBy(xpath = "/html/body/div/fieldset/form/button[1]")
	private WebElement submit;
	
	public LoginUser(WebDriver driver) {
				
	}
	
	public void adduser(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submit.click();
	}

}

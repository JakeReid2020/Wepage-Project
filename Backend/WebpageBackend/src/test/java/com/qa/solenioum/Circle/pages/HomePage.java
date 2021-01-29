package com.qa.solenioum.Circle.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public static final String URL = "file:///C:/Users/jaker/Desktop/Wepage-Project/Homepage/Index.html";
	
	@FindBy(xpath = "/html/body/nav/div/div/ul/li[3]/a")
	private WebElement navLoginPage;
	
	public LoginPage loginPage;
	public LoginUser loginUser;
	public RegisterPage registerpage;
	
	public HomePage(WebDriver driver) {
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
	}
	
	public void navloginPage() {
		navLoginPage.click();
	}
}

/*
@FindBy(xpath = "/html/body/nav/div/div/ul/li[3]/a")
@FindBy(xpath="/html/body/div/fieldset/form/a")
private WebElement navRegisterPage;

public RegisterPage registerPage;

public void navRegisterPage() {
	navRegisterPage.click();
}
*/
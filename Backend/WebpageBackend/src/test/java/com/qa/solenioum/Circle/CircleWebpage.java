package com.qa.solenioum.Circle;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.solenioum.Circle.pages.HomePage;

public class CircleWebpage {

	private static WebDriver driver;
	private static Logger LOGGER = Logger.getGlobal();
	
	@BeforeAll
	public static void init() {
		LOGGER.setLevel(Level.ALL);
    	driver.get("file:///C:/Users/jaker/Desktop/Wepage-Project/Homepage/Index.html");
        // timeouts
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        
		System.setProperty("webdriver.chrome.driver","C:\\Users\\jaker\\Desktop\\Wepage-Project\\Backend\\WebpageBackend\\src\\test\\resources\\drivers\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void Login() throws InterruptedException {
		driver.get("file:///C:/Users/jaker/Desktop/Wepage-Project/Homepage/Index.html");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		 LOGGER.info("Creating a new user...\n");	 
		 
         website.navloginPage();
         website.loginPage.loginUser("root", "root");
    	
		// end wait
		Thread.sleep(30000);
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}

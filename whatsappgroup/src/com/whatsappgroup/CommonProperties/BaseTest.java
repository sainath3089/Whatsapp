package com.whatsappgroup.CommonProperties;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest 
{
	public static String sBrowser = "";	

	public static String sTestSiteURL ;
	public static String sUsername = "";
	public static String sPassword = "";
	public static String sGrpName = "";
	public static WebDriver driver;
	
	public static void readPropertiesFileNSetLocalVars()
	{
		Properties p = new Properties();
		FileReader reader;
		try
		{
			reader = new FileReader("app.properties");

			p.load(reader);
			
			sTestSiteURL = p.getProperty("test_url");
			sGrpName = p.getProperty("groupname");
			reader.close();

			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@BeforeClass
	public static WebDriver browserLaunch( )
	

 {
		try{
		    
		    readPropertiesFileNSetLocalVars();
		    
		    System.out.println("Inside browser launch");
		    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");      
			driver = new ChromeDriver();  
			
			System.out.println("Test Url" + sTestSiteURL);
			driver.get(sTestSiteURL);
			driver.manage().window().maximize();
			
			
		}
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
			
		}
		
		return driver;

	}
	
	

	public static void logIn() {
	    
	    try
	    {
	    // TODO Auto-generated method stub
	    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	   String chatlist_search  = ".jN-F5.copyable-text.selectable-text";
	   WebElement myDynamicElement = (new WebDriverWait(driver, 40))
		   .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(chatlist_search)));
	    }
	    
	    catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	 @AfterClass public void CloseBrowser()

	  {

	    driver.quit(); 
	    }

	
}

					  



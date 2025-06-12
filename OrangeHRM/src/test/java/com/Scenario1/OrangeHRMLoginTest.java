package com.Scenario1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class OrangeHRMLoginTest { 
	public WebDriver driver;
 	ExtentReports extent;
    ExtentTest test;
	@BeforeTest
    public void setupExtentReport() {
        extent = ExtentReportManager.createInstance();
    }
    @AfterTest
    public void flushReport() {
        extent.flush();
    }
  	   @Test(dataProvider ="Excel",dataProviderClass = ExcelReader.class)
  	  public void testdatadriven(String username,String password) throws InterruptedException {
  		 
  		test = extent.createTest("Login Test with - Username: " + username + ", Password: " + password);
  		  
  		  driver = new ChromeDriver();
  		  driver.manage().window().maximize();
  		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  		  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
  		  
  		  driver.findElement(By.name("username")).sendKeys(username);
  		  driver.findElement(By.name("password")).sendKeys(password);
  		  driver.findElement(By.xpath("//button[@type='submit']")).click();
  		Thread.sleep(2000);
  		
  		captureScreenshot(username + "_" + password + ".png");
  		    
  		  	Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),"Login Fail");
  			  System.out.println("Login Successfull");
  			//  driver.quit();
  }
  	public void captureScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + fileName);
        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot captured: " + fileName);
    }

    public void logout() throws InterruptedException {
        driver.findElement(By.className("oxd-userdropdown-tab")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        System.out.println("Logged out.");
    }

}

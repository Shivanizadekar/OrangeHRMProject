package com.TestCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.listeners.ExtentManager;
import com.Pages.CartPage;
import com.Pages.HomePage;
import com.Pages.IndexPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass {
    public WebDriver driver;
    public HomePage hp;
    public IndexPage ip;
    public CartPage cp;
    public String un;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");

        extent = ExtentManager.getInstance();
        un = "shivani20" + System.currentTimeMillis();

        hp = new HomePage(driver);
        ip = new IndexPage(driver);
        cp = new CartPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}

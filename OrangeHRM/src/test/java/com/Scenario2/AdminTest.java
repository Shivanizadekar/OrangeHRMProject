package com.Scenario2;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import Pages.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class AdminTest {
    WebDriver driver;
    LoginPage loginPage;
    AdminPage adminPage;

    public static ExtentReports extent;
    public ExtentTest test;

    @BeforeTest
    public void setupReportAndBrowser() {
        // Setup ExtentReports
        ExtentSparkReporter spark = new ExtentSparkReporter("target/AdminTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent = ExtentReportManager.createInstance();

        // Launch Browser
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @BeforeClass
    public void loginAndSetupPages() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        adminPage = new AdminPage(driver);
    }

    @Test(priority = 1)
    public void verifyAdminMenu() {
        test = extent.createTest("Verify Admin Menu Option");
        test.info("Navigating to Admin module from menu");
        adminPage.clickAdminFromMenu();
        test.pass("Admin menu clicked successfully");
    }

    @Test(priority = 2)
    public void verifySearchWithUsername() {
        test = extent.createTest("Verify Search By Username");
        String result = adminPage.searchEmployeeByUsername("Admin");
        test.info("Searched by username, result: " + result);
        Assert.assertTrue(result.contains("Record") || result.contains("Found"), "Search failed");
        test.pass("Search by username successful");
    }

    @Test(priority = 3)
    public void verifySearchWithUserRole() {
        test = extent.createTest("Verify Search By User Role");
        String result = adminPage.searchEmployeeByUserRole("Admin");
        test.info("Searched by role, result: " + result);
        Assert.assertTrue(result.contains("Record") || result.contains("Found"), "Search failed");
        test.pass("Search by user role successful");
    }

    @Test(priority = 4)
    public void verifySearchWithUserStatus() {
        test = extent.createTest("Verify Search By User Status");
        String result = adminPage.searchEmployeeByStatus("Enabled");
        test.info("Searched by status, result: " + result);
        Assert.assertTrue(result.contains("Record") || result.contains("Found"), "Search failed");
        test.pass("Search by user status successful");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}

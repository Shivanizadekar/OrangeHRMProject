package com.Pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Generic.Utility;

public class HomePage {
    private WebDriver driver;
    String screenshot;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "signin2")
    WebElement signInBtn;

    @FindBy(id = "sign-username")
    WebElement signUpUsername;

    @FindBy(id = "sign-password")
    WebElement signUpPassword;

    @FindBy(xpath = "//button[normalize-space()='Sign up']")
    WebElement signUpSubmitBtn;

    @FindBy(id = "login2")
    WebElement loginLink;

    @FindBy(id = "loginusername")
    WebElement loginUsername;

    @FindBy(id = "loginpassword")
    WebElement loginPassword;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    WebElement loginBtn;

    @FindBy(id = "nameofuser")
    WebElement loggedInUserLabel;

    @FindBy(id = "cartur")
    WebElement cartLink;

    public void doSignUp(String username, String password) throws InterruptedException {
        signInBtn.click();
        Thread.sleep(1000);
        signUpUsername.sendKeys(username);
        signUpPassword.sendKeys(password);
       screenshot = Utility.getScreenshot(driver, "UserRegistration");
        signUpSubmitBtn.click();

        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        System.out.println("SignUp alert: " + alert.getText());
        alert.accept();
    }

    public IndexPage doLogin(String username, String password) throws InterruptedException {
        loginLink.click();
        Thread.sleep(1000);
        loginUsername.sendKeys(username);
        loginPassword.sendKeys(password);
        loginBtn.click();
        Thread.sleep(2000);
        System.out.println("Logged in as: " + loggedInUserLabel.getText());
        return new IndexPage(driver);
    }

    public void clickOnCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("signInModal")));
        cartLink.click();
    }
}

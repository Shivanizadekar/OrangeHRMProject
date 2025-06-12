package com.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Generic.Utility;
import com.aventstack.extentreports.Status;

public class CompleteTest extends BaseClass {

    @Test(priority = 1)
    public void userRegistration() throws InterruptedException {
        test = extent.createTest("User Registration");
        hp.doSignUp(un, "test123");
      //  String screenshot = Utility.getScreenshot(driver, "UserRegistration");
        test.log(Status.PASS, "User registered successfully: " + un);
      //  test.addScreenCaptureFromPath(screenshot);
    }

    @Test(priority = 2)
    public void userLogin() throws InterruptedException {
        test = extent.createTest("User Login");
        ip = hp.doLogin(un, "test123");
        String screenshot = Utility.getScreenshot(driver, "UserLogin");
        test.addScreenCaptureFromPath(screenshot);
    }

    @Test(priority = 3)
    public void addProductToCart() throws InterruptedException {
        test = extent.createTest("Add Product to Cart");
        ip.addProductToCart("Phones", "Samsung galaxy s7");
        String screenshot = Utility.getScreenshot(driver, "AddProductToCart");
        test.log(Status.PASS, "Product added to cart");
        test.addScreenCaptureFromPath(screenshot);
    }

    @Test(priority = 4)
    public void checkoutProcess() throws InterruptedException {
        test = extent.createTest("Checkout Process");
        cp = ip.goToCartPage();
        String prodName = cp.getProductName();
        Assert.assertEquals(prodName, "Samsung galaxy s7", "Product in cart mismatch");
        cp.placeOrder("Shivani", "India", "Pune", "1234-5678-9012-3456", "12", "2025");
        String screenshot = Utility.getScreenshot(driver, "CheckoutComplete");
        test.log(Status.PASS, "Checkout completed successfully");
        test.addScreenCaptureFromPath(screenshot);
    }
}

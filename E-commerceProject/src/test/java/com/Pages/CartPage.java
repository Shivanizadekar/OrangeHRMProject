package com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Generic.Utility;

public class CartPage {
    WebDriver driver;
    String screenshot;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody[@id='tbodyid']/tr/td[2]")
    WebElement productName;

    @FindBy(xpath = "//tbody[@id='tbodyid']/tr/td[3]")
    WebElement productPrice;

    @FindBy(xpath = "//button[text()='Place Order']")
    WebElement placeOrderBtn;

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(id = "country")
    WebElement inputCountry;

    @FindBy(id = "city")
    WebElement inputCity;

    @FindBy(id = "card")
    WebElement inputCard;

    @FindBy(id = "month")
    WebElement inputMonth;

    @FindBy(id = "year")
    WebElement inputYear;

    @FindBy(xpath = "//button[text()='Purchase']")
    WebElement purchaseBtn;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]")
    WebElement confirmationMsg;

    @FindBy(xpath = "//button[text()='OK']")
    WebElement okBtn;

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void placeOrder(String name, String country, String city, String card, String month, String year) throws InterruptedException {
        placeOrderBtn.click();

        inputName.sendKeys(name);
        inputCountry.sendKeys(country);
        inputCity.sendKeys(city);
        inputCard.sendKeys(card);
        inputMonth.sendKeys(month);
        inputYear.sendKeys(year);

        purchaseBtn.click();
        screenshot = Utility.getScreenshot(driver, "CheckoutComplete");
        Thread.sleep(2000);
        System.out.println("Order confirmation message: " + confirmationMsg.getText());
        
        okBtn.click();
    }
}

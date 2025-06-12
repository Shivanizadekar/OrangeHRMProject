package com.Pages;

import java.util.List;

import org.openqa.selenium.Alert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class IndexPage {
    WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='list-group']//a")
    List<WebElement> allCategory;

    @FindBy(xpath = "//div[@id='tbodyid']//h4//a")
    List<WebElement> allOptions;

    @FindBy(linkText = "Add to cart")
    WebElement addToCartBtn;

    @FindBy(id = "cartur")
    WebElement cartBtn;

    public void addProductToCart(String category, String productName) throws InterruptedException {
        for (WebElement cat : allCategory) {
            if (cat.getText().equalsIgnoreCase(category)) {
                cat.click();
                break;
            }
        }

        Thread.sleep(2000); // wait for products to load
        for (WebElement prod : allOptions) {
            if (prod.getText().equalsIgnoreCase(productName)) {
                prod.click();
                break;
            }
        }

        Thread.sleep(2000);
        addToCartBtn.click();

        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Text: " + alert.getText());
        alert.accept();
    }

    public CartPage goToCartPage() {
        cartBtn.click();
        return new CartPage(driver);
    }
}

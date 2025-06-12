package Pages;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // Locators
    private By menu = By.xpath("//ul[@class='oxd-main-menu']//li//span");
    private By uname = By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private By userrole = By.xpath("(//i[contains(@class,'oxd-select-text--arrow')])[1]");
    private By status = By.xpath("(//i[contains(@class,'oxd-select-text--arrow')])[2]");
    private By searchbtn = By.xpath("//button[@type='submit']");
    private By recordmsg = By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//span[contains(@class,'oxd-text')]");
    private By userROptions = By.xpath("//div[@role='listbox']//div[@role='option']");
    private By statustext = By.xpath("//div[@role='option']//span");
    // Methods
    public void clickAdminFromMenu() {
        List<WebElement> list = driver.findElements(menu);
        for (WebElement i : list) {
            if (i.getText().contains("Admin")) {
                i.click();
                break;
            }
        }
    }
    public String searchEmployeeByUsername(String name) {
        driver.findElement(uname).sendKeys(name);
        driver.findElement(searchbtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordmsg));
        String result = driver.findElement(recordmsg).getText();
        driver.findElement(uname).clear();
        return result;
    }
    public String searchEmployeeByUserRole(String role) {
        driver.findElement(userrole).click();
        List<WebElement> list = driver.findElements(userROptions);
        for (WebElement i : list) {
            if (i.getText().equalsIgnoreCase(role)) {
                i.click();
                break;
            }
        }
        driver.findElement(searchbtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordmsg));
        return driver.findElement(recordmsg).getText();
    }

    public String searchEmployeeByStatus(String stat) {
        driver.findElement(status).click();
        List<WebElement> list = driver.findElements(statustext);
        for (WebElement i : list) {
            if (i.getText().equalsIgnoreCase(stat)) {
                i.click();
                break;
            }
        }
        driver.findElement(searchbtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordmsg));
        return driver.findElement(recordmsg).getText();
    }
}

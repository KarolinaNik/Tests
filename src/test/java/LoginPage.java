import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


/**
 * Created by Alexandra on 17/03/2017.
 */
public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver currentDriver) {
        this.driver = currentDriver;
        wait = new WebDriverWait(driver, 30);
    }

    public void openLoginPage(String url, String user, String pass) {
        driver.navigate().to(url);
        wait.until(presenceOfElementLocated(By.id("UserName"))).sendKeys(user);
        wait.until(presenceOfElementLocated(By.id("Password"))).clear();
        driver.findElement(By.id("Password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();

    }

}

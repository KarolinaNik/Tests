import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


/**
 * Created by Alexandra on 17/03/2017.
 */
public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    private String browser = "Chrome"; //"Chrome", "Mozilla"
    private String home_url = "https://iportal-integration.azurewebsites.net/";
    private String user = "alexandra.ilianova@imparta.com";
    private String password = "Qwerty1234";

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, 120);

    }

    //old
    public void openLoginPage(String url, String user, String pass) {

        driver.navigate().to(url);
        wait.until(presenceOfElementLocated(By.id("UserName"))).sendKeys(user);
        wait.until(presenceOfElementLocated(By.id("Password"))).clear();
        driver.findElement(By.id("Password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();
    }
    //old

    //new
    public LoginPage login() {
        driver.navigate().to(home_url);
        wait.until(presenceOfElementLocated(By.id("UserName"))).sendKeys(user);
        wait.until(presenceOfElementLocated(By.id("Password"))).clear();
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();
        return this;
    }
    //new




}

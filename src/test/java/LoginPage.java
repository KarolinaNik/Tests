import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


/**
 * Created by Alexandra on 17/03/2017.
 */
public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver currentDriver) {
        this.driver = currentDriver;
        wait = new WebDriverWait(driver, 60);
    }

    public void openLoginPage(String url, String user, String pass) {
        driver.navigate().to(url);
        wait.until(presenceOfElementLocated(By.id("UserName"))).sendKeys(user);
        wait.until(presenceOfElementLocated(By.id("Password"))).clear();
        driver.findElement(By.id("Password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();

    }

}

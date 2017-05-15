import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Alexandra on 21/04/2017.
 */
public class tmp {

    public static void main(String[] args) {

        WebDriver driver;
        WebDriverWait wait;
        System.setProperty("webdriver.gecko.driver", "C:\\Tools\\selenium drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
        driver.navigate().to("http://www.google.co.uk");
        wait.until(presenceOfElementLocated(By.id("lst-ib"))).sendKeys("selenium");


        driver.quit();
        driver = null;
        System.out.println("------------- End of scenario. -------------");
    }
}

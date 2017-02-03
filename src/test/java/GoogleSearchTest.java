import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Alexandra on 03/02/2017.
 */
public class GoogleSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);

    }

    @Test
    public void GoogleSearchTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs("webdriver - Google Search"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

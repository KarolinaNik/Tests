import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Alexandra on 03/02/2017.
 */
public class QuickAlyticsTest {

    private WebDriver driver;
    private WebDriverWait wait;
    String LoginUrl = "https://www.quickalytics.com/dev5/";

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void Login() {
        driver.navigate().to(LoginUrl);
        wait.until(titleIs("QuickAlytics login"));
        driver.findElement(By.name("loginUsername")).sendKeys("ailyanova");
        driver.findElement(By.name("loginPassword")).sendKeys("123123");
        driver.findElement(By.cssSelector("td.x-btn-mc")).click();

        wait.until(presenceOfElementLocated(By.id("cmbClientsDisplay"))); //drop-down with clients
        driver.findElement(By.id("cmbClientsDisplay")).clear(); //clear default value from drop-down
        driver.findElement(By.id("cmbClientsDisplay")).sendKeys("Test - AI"); //login to Test-AI
        driver.findElement(By.cssSelector("div#div-info")).click(); //click on a page to close drop-down
        wait.until(presenceOfElementLocated(By.id("ext-gen196")));
        driver.findElement(By.id("ext-gen196")).click(); //'Select' button

    }

    @After
    public void stop() {
        //      driver.quit();
        driver = null;
    }

}

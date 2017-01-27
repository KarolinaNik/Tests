import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Alexandra on 27/01/2017.
 */


public class Login {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void Login() {
        driver.navigate().to("https://iportal-integration.azurewebsites.net/ng/Login");
        wait.until(titleIs("Log in"));
        driver.findElement(By.id("UserName")).sendKeys("alexandra.ilianova@imparta.com");
        driver.findElement(By.id("Password")).sendKeys("AZsxdc1234");
        driver.findElement(By.cssSelector("button.btn.btn-success.btn-block")).click();  //class name = "btn btn-success btn-block", Webdriver problem with spaces inside class names
        driver.findElement(By.cssSelector("span.hidden-xs.usersFullname"));
        //Assert.assertEquals(element.getAttribute(attributeName), expectedAttributeValue);

        //driver.findElement(By.linkText("Admin")).click();
        //driver.findElement(By.cssSelector("span.").click();
    }


    /*   @Test
    //Search in google
          public void myFirstTest() {
           driver.navigate().to("http://www.google.com");
           driver.findElement(By.name("q")).sendKeys("webdriver");
           driver.findElement(By.name("btnG")).click();
           wait.until(titleIs("webdriver - Google Search"));
       }
   */
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
